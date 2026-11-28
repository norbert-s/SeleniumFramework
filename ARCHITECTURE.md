# Selenium Framework – architektúra

## Architektúra célja

A projekt célja egy újrafelhasználható Selenium UI-tesztelési keretrendszer. A keretrendszer alapja alkalmazásfüggetlen: böngésző-életciklust, várakozást, naplózást és tesztfuttatói integrációt biztosít, de nem tartalmazhat SpiceJet- vagy más alkalmazásspecifikus működést. A SpiceJet-tesztek jelenlegi példák, és később továbbfejleszthetők.

Ez a dokumentum a jelenlegi felépítést és a tervezett architekturális irányt írja le. A célarchitektúra nem minden eleme van már megvalósítva.

## Jelenlegi felépítés

```text
TestNG-tesztek
    └── DriverBaseClass és tesztlistener
          ├── DriverManager / böngésző-factory-k
          ├── Extent-riport
          └── pageObjectClasses
                ├── általános page-object metódusok
                └── SpiceJet-specifikus teszt/page object
```

- A TestNG kezeli a tesztéletciklust, a konfiguráció egy része a `pom.xml`-ből és a TestNG-suite fájlokból érkezik.
- A `DriverManagerFactory` böngésző- és Docker-beállítás alapján választ drivermanagert.
- A `DriverBaseClass` tesztmetódusonként inicializálja, majd lezárja a drivert. A párhuzamos futáshoz a driver és a teszthez tartozó állapot `ThreadLocal`-t használ.
- A `TestListener` TestNG-eseményekből Extent-riportot és képernyőképet készít.
- A `TestFactory` jelenleg közvetlenül a SpiceJet-teszttípust hozza létre. Ez alkalmazásspecifikus, és jelenlegi egyszerűsége mellett nem feltétlenül igényel külön factoryt vagy interfészt.
- A page objectek jelenleg a `pageObjectClasses` csomagban vannak, több absztrakt osztályból és interfészből álló öröklési láncban.

## Page object réteg – jelenlegi megfigyelések

A jelenlegi öröklési lánc:

```text
PageObjectBasePage
    extends PageObjectBaseMethodsAbstract
        ↑
PageObjectBaseMethods
    extends PageObjectBasePage
        ↑
PageObjectGenericMethodsImpl
    extends PageObjectBaseMethods
        ↑
SpiceJetTest
```

- `PageObjectBasePage` tárolja `WebDriver`-t, de az `extends PageObjectBaseMethodsAbstract` miatt közvetetten a wait-metódusok szerződését is örökli.
- `PageObjectBaseMethodsAbstract` absztrakt wait- és locator-segédmetódusokat deklarál. A konkrét megvalósítás a `PageObjectBaseMethods` osztályban található. Ez a köztes absztrakt réteg jelenleg nem ad önálló működést; felülvizsgálandó, hogy szükséges-e.
- `IPageObjectGenericMethods` a generikus műveletek egy részét deklarálja, de nem mindegyik elérhető művelet része az interfésznek. A generikus `<T>` visszatérési típusok sem adnak erős típusszerződést.
- A SpiceJet-típusokhoz kötött interfészek és factory-k alkalmazásspecifikusak; nem a keretrendszer magjába tartoznak.

**Tervezési irány:** a page object alap legyen egyszerű, a driverhez és szükséges általános szolgáltatásokhoz férjen hozzá. Interfészt vagy absztrakt köztes osztályt csak akkor tartunk meg, ha az ténylegesen cserélhetőséget, tesztelhetőséget vagy hasznos API-szerződést ad. A gyártás közvetlen konstruktorhívással is történhet; alkalmazásspecifikus factory csak valós összetettség esetén indokolt.

## Várakozások – jelenlegi működés

- A `PageObjectBaseMethods` Selenium `FluentWait`-et hoz létre.
- Alapértelmezett pollolás: 50 ms.
- Alapértelmezett timeout: `EnvironmentVariables.getWaitForTime()` értéke; egyes hívások külön timeoutot is megadhatnak.
- `PageObjectGenericMethodsImpl` használ explicit waitet többek között kattinthatóságra, elemjelenlétre, láthatóságra és a dokumentum `readyState == complete` állapotára várva.
- A forráskódban nem találtunk `Thread.sleep` hívást vagy beállított implicit waitet.
- A page-load és script timeoutot külön a `SettingUpTimeouts` állítja be.
- A `getWaitForTime()` jelenlegi `parseInt`-je a null-ellenőrzés előtt fut, ezért hiányzó vagy hibás rendszerproperty esetén kivételt okozhat.

**Tervezési irány:** maradjon közös, explicit `FluentWait`-alap, amelynek timeoutja és pollolása konfigurálható. A várakozás létrehozását és kezelését külön wait/utility szolgáltatás végezze, ne rejtett öröklési szerkezet. A várakozási idő eseménytípusonkénti finomhangolása hasznos cél lehet, de a jelenlegi feladatban nem része a megvalósításnak. Oldalanként eltérő wait-stratégiát csak konkrét igény esetén vezessünk be.

## Célrétegzés

```text
Tesztfuttató-adapter (jelenleg TestNG)
    └── Teszt-életciklus szolgáltatás
          ├── Tesztfutási kontextus létrehozása (runId, testId, annotációs metaadat)
          ├── Naplózási kontextus beállítása (MDC)
          ├── Driver szolgáltatás: böngésző indítása és konfigurálása
          └── Page objectek és alkalmazásspecifikus teszt
                ├── Közös, konfigurálható wait/interakció szolgáltatás
                └── Alkalmazásspecifikus page objectek és tesztadatok

Alkalmazás- és függőségi naplózók
    └── SLF4J API → Log4j2 backend → központi logkimenet/fájl
                                      └── később loggyűjtő → kereshető dashboard

Teszt-életciklus szolgáltatás lezárása:
    ├── Driver/session leállítása
    ├── Tesztfutási kontextus eltávolítása az aktuális szálról
    └── Naplózási kontextus (MDC) törlése

Riport-adapter (jelenleg TestNG listener + Extent)
    └── Teszteredmény és riport kezelése; nem birtokolja a driver életciklust
```

Az egyes szolgáltatásoknak jól körülhatárolt felelősségük legyen. A **teszt-életciklus szolgáltatás** koordinálja a teszthez tartozó setupot és teardown-t: a futási kontextus és naplózási kontextus beállítását, a driver elindítását, majd a teszt végén ezek megbízható lezárását és eltávolítását. A TestNG-adapter csak az eseményeket továbbítja ennek a szolgáltatásnak; ne legyen szétszórva a setup és cleanup a listenerben, base classokban és tesztekben.

A cleanup `finally` jellegű legyen, így egy driver-leállítási hiba se akadályozza meg a többi szálhoz kötött állapot eltávolítását. A tesztfutási kontextus tárolója az aktuális szál állapotát adja a tesztnek és a logolásnak; a szál maga a TestNG thread pool része, nem a szolgáltatás tulajdona, és újra felhasználható. A riport-adapter ettől külön felelősség: olvassa a teszteredményeket, de ne legyen az egyetlen hely, ahol az életciklus takarítása megtörténik.

A függőségek a tesztek felől a keretrendszer általános szolgáltatásai felé mutassanak. A framework magja ne függjön konkrét alkalmazástól; a TestNG- és riportintegráció legyen adapter jellegű, hogy később cserélhető maradjon.

## Naplózás és azonosítók

### Jelenlegi állapot

- A projekt SLF4J API-t használ Log4j2 backenddel; az alkalmazáskód logolása nem a TestNG-riporton keresztül történik.
- A `log4j2.xml` jelenleg konzolra ír, és a mintában szerepelhetnek MDC-azonosítók.
- A TestNG listener és Extent külön teszteredmény-riportot készít. Ez nem helyettesíti, nem gyűjti be és nem tárolja központilag a logger által kibocsátott összes eseményt.
- A TestNG, Selenium és más függőségek saját loggerükkel is írhatnak. A cél a logger-rendszeren keresztül kibocsátott, konfigurált logesemények egységes kimenetre irányítása; ez nem jelenti automatikusan minden folyamatbeli `stdout`/`stderr` tartalom elfogását.
- A framework több helyen használ `INFO` szintű naplózást. A loghívások helyét és tartalmát meg kell őrizni és felül kell vizsgálni: a logolás egységesítése nem célja a hasznos események eltávolítása.

### Tervezett irány

- Az alkalmazáskód SLF4J API-n keresztül logoljon, Log4j2 backenddel. Az alapértelmezett szint `INFO`; részletesebb szintek konfigurálhatók.
- A logolás önálló technikai réteg legyen, ne TestNG-listenerből végzett loggyűjtés. A TestNG/Extent riport a tesztállapotot és teszteredményeket kezeli; a logger appenderei az eseménynaplót.
- A Log4j2 konfiguráció a keretrendszer, a tesztek és a logger-integrációt használó függőségek logjait egy központi kimenetre irányítsa. Első lépésként ez lehet futásonkénti fájl; konzolkimenet fejlesztéshez maradhat konfigurálható.
- A fájlkimenet legyen géppel feldolgozható, lehetőleg strukturált formátumban (eseményenként külön rekord), és tartalmazzon időbélyeget, szintet, loggernevet, szálat, üzenetet, kivételt, valamint `runId`, `testId` és opcionális `testCaseId` mezőket.
- A futás logjainak ne keveredjenek más futásokkal. A `runId` legyen a fájlnév vagy logútvonal része, illetve minden rekordban szerepeljen; a teljes futás fájlját a futás végén zárjuk le.
- Később a fájlokat loggyűjtő továbbíthatja Elasticsearch/OpenSearch-alapú tárolóba és Kibana-szerű dashboardra. A konkrét szállítási és dashboard-megoldás még nyitott döntés; a framework ne függjön közvetlenül ezektől.
- Minden futás kapjon `runId`-t, teszteset-futtatásonként `testId`-t. Az opcionális Jira-azonosító a teszten deklarált annotációból (például `@TestCaseId("ABC-123")`) származzon; ez metaadat, nem Jira-integráció.
- Az azonosítók kerüljenek MDC-be az adott teszt szálán, és a teszt befejezésekor vagy setup-hiba esetén `finally`-ban törlődjenek. A riport és logkimenet ugyanazokat az azonosítókat használhatja, de egyik se a másikból nyerje azokat.

## Későbbre hagyott architekturális döntések

- Az egyes műveletekhez (jelenlét, kattinthatóság, navigáció stb.) tartozó timeoutok és pollolási intervallumok külön konfigurálása.
- A riportoló vagy tesztfuttató lecserélése.
- A SpiceJet-tesztek továbbfejlesztése, illetve más alkalmazás tesztjeinek hozzáadása.
