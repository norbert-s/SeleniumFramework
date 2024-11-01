# Selenium Framework

It is a Demo Framework in Java /Selenium

The test can be run locally as:

  - mvn test 

In case there is docker installed then the tests are going to be run in a container

Also a pipeline can be run by members of the project https://github.com/norbert-s/SeleniumFramework/actions

with mvn test several command line parameters can be provided

Those parameters all have a default value and are declared in the parameters section in pom.xml file


With "mvn test" one can supply other parameters as follows:

  - "surefire.suiteXmlFiles=", currently is running the tests listed in extracting_tests.xml, this file can be extended or other xmls can be created - following the rules of creating xml runners of testng (for examples see extracting_tests.xml)
  - "-Dwaitfortime=" one can change the default timeout
  - "-Denvironment=" the env can eb changed
  - "-Dretryfailed=" can be changed which determines how many times a failed test will be rerun
  - "-Dheadless=" is true by default but locally sometimes during development the browser is need to be exposed
  - "-Ddebuglevel=" can take "INFO","ERROR","DEBUG","WARNING" arguments
  - "-Dincognitoneeded=" can be true or false
  - "-Dgroups=" can take the arguments declared besides @Test(groups ="theGroupTagYouDeclared"), currently only "smoke" is supplied in the tests
  - "-Dgrid=" can take true or false - later a running version of the grid will be taken over to the main branch and then it can be decided at runtime if the tests needed to be run on a grid or by means of the default

Parallel runs:
  - test can be run in 3 different manners in parallel
  
    A. classes can be run in parallel. It means that if there are several classes listed in a single xml file, then those can be run in parallel
    - for this the suite part of the xml file needs to be defined as follows : 
    - "suite name="test suite" parallel="classes" thread-count="2""
    
    B. tests can be run in parallel
    - "suite name="test suite" parallel="tests" thread-count="2""
    
    C. methods can be run in parallel
    - "suite name="test suite" parallel="methods" thread-count="2""

-----------------------------------

About the tests:

The MediamarktTest class demonstrates assertions done against an initial porducts profile in a generalized way after with the details page, then selecting a product, then checkout, etc.

- from the mediamarkt.json file one can control what kind of product to look for

- then a product profile is created dinamically. 

  all the product details - no matter what they are; whether it be a tv,smartphone, etc. 

   different products have different attributes -for instance a tv does not have a dual sim, a smartphone does not have an hdmi connector, etc.
    Therefor in these cases it would be quite laborious to hardcode the locators of all the different products and then extract them
    Instead which will extract all the features from the particular dom element
  and will attach all of the product details to the particular product object. 
  
  One or more products can be selected for purhase and be checked against the initial product feature values. Also it can be extended to be checked against an initial values list as test oracle.

- Also the locator technique used is cssSelector and if possible it is better to use that instead of xpath, because it is known to be much faster.

The SpiceJetTest class runs a demo test using method chaining on the spicejet website.

- in order to test if parallel running of tests work in the test runner json of "spicetest.json" several variation of suuplied values and values to be asserted are provided
- and then those tests can be run against different browsers
- obviously any kind of tests can be run in parallel, however this class demonstrates that the tests methods(or if the tests are set to run in parallel as "classes", then the classes) use separate "driver" instances from the thread pool.

-----------------------------------

About this demo project in general

The general goal of the project is to highlight and show different flavours of architectural approaches -and options for larger projects

The tests actually being run are rather simple in the sense that they do not run very complex UI tests, the focus is on the architecture

the architecture shows several ways how to extend, use the classes, and how to implement tests.

A. The architecture itself ( someof the approaces used can be applied also in other parts of a framework such as reporting, logging, utility classes handling csv,data,jsonxml files, etc)

  1. The methods of the test classes -for instance SpiceJetTests- are available through an interface -ISpiceJettest interface.

     All the methods that we want available in the test class of parallel needs to be declared -abstract - on this interface.
     
      This way it can be controlled which methods are available of the entirety of methods.
  
     In some projects a group of test automation engineers are interacting with the page objects,etc and are implementing methods, etc And other test automation engineers are working on creating tests -using the methods available.

  
2. The driver itself is not exposed to the most outer layer -where the actual tests are created - most of the methods usually used will have had an implementation -in a generic methods impleemntation class- and therefore the driver instance is not needed in the most outer layer, and the methods are not needed to be overwritten, only be used

      If there is a need to have access to the driver then the getter of the driver can be made protected - instead of package private OR if the access is allowed to only specific tests then it can be achieved through an interface to limit what is available.

      This approach is achieved by layering responsibility of classes/interfaces and hiding some of the methods
      
      This can be reversed by taking away those layers 
    
   Depending on the expectations the tests can be created this way or the other


  3. In case of Alza tests 
     the generic pageObjectMethods - are filtered by using an interface which declares which methods should be avauilable on an instance on IAlzaTest

     the IAlzaTest interface extends the interface which declares which methods should be available from the generic methods

     this way the methods declared on the IpageObjectgenericMethods are not needed to be reimplmeneted. An IAlzatest instance will 'see' those methods

     This way it is highly flexible and in line with SOLID principles

     Also its very easily extendable and maintenable


  5. There is a factory which takes care of creating instances of the registered classes whenever a new class of tests are created, 
        
        the new class/interface(obviously an instance on the class can be created as well) needs to be included in the ITestFactory interface 
        and then a getter to be created in the TestFactory class and then in the actual tests an instance of the particular class can be created 
        and all the methods 'exclusive' to that class of tests will be available to use



  6. Also in case of SpiceJetTests when declaring the methods the objects -'this' - is returned, - this way method chaining is possible.

        In the AlzaTests I did not use this approach. Again to show different flavours -that there are mutilple options how to approach a problem,. 
        and that the question which one is the best can be answered looking at the particular problem. 
        Sometimes method chaning is better, sometimes simply calling the methods on the instance, sometimes a mixture of the two.

  
   7. Another thing demonstrated is the fact that the page objects are exposed to the most upper layer.

      This way in the upper layer the methods defined in more hidden layers can take page objects here and use them - instead of the page obejects being hidden 
        Once again these different variations can be used interchangably and use them based on requirements


B. the webdriver instances -henceforth - the tests are running in Docker
1. on the grid branch the tests can be run in a grid -at this point it works only manually setting up the grid.
  For some reason with docker compose I was not able to set up the grid correctly, so was I not in the pipeline.

2. the dockerized version runs correctly locally and in the pipeline as well in the main branch
3. the tests are running concurrently - concurrency is set such that all the methods are being run concurrently using the resources in a thread safe way

4. currently Chrome, EDGE, Firefox driver instances are run

5. the tests are run in a data driven manner, the whole test oracle - meaning that the data supplied and the expectations supplied come from json files through dataproviders

   - the same tests are repeated with different test data -by json files - and the tests run as many times as the supplied data triggers them to

6. there is a pipeline which runs tests on the branches they are pushed onto

   - and if the tests fail then push to main is not permitted

C. These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests    from the repo of the app) to run tests for other applications (for instance bookstore,etc)

D. There are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties

E. In large projects for logging and report in java its usually best to develop a custom made "framework"-someclasses taking care of reporting and logging- to report to a database and to fullfill all the requirements

   - In this demo the reporting is done currently by surefire report. Which is a basic reporting tool- in terms of the layout, however it gives a quite usable report. 

   - Besides that there is an ITestListener implementation by which the re-running of thests is done as well, as the listener reports failing tests and those can be rerun.

   - There is also an extent report implementation done. Currently it is being used only when testng is run programatically rather than using the main testng.xml. Later it will be improved to include logging events. the control it gives is better than the surefire does, however it needs a whole lot more preparation.
   - Allure report is also an option - in order to set it up it needs lots of decoration of methods - I rather had chosen the extent report and surefire to do the custom setup programatically - for this demo

F. There is also a loggin implementation developmernt in progress using also listeners to listen to events and decorate those events with logging. A basic version may be the usage of WebDriverEventListener which is an interface with basic events logging. It can be further improved by overwriting the base event listener and extend it then to decorate all the methods that may be used. 

   - Later the frameowork is going to contain an improved extent report with collected events logging, reported in the final report - stored in a database. Essentially it will contain all the steps included in a given test with all the necessary methds decorated with  logging with the extent reports's nicer interface.



