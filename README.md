# Selenium Framework




A. The architecture itself shows approaches usually used in large projects

  1. The methods of the test classes -for instance SpiceJetTests- are available through an interface -ISpiceJettest interface.

     All the methods that we want available in the test class of parallel needs to be declared -abstract - on this interface.
     
      This way it can be controlled which methods are available of the entirety of methods.
  
     In some projects a group of test automation engineers are interacting with the page objects,etc and are implementing methods, etc And other test automation engineers are working on creating tests -using the methods available.

  
  2. The driver itself is not exposed to the most outer layer -where the actual tests are created - most of the methods usually used will have had an implementation and therefore no need to overwrite them

  
     This approach is achieved by layering responsibility of classes/interfaces and hiding some of the methods
  
     This can be reversed by taking away those layers 

      Depending on the expectations the tests can be created this way or the other

  3. In case of Alza tests 
     the generic pageObjectMethods - are filtered by using an interface which declares which methods should be avauilable on an instance on IAlzaTest
     the IalZaTest interface extends the interface which declares which methods should be available from the generic methods
     this way the methods declared on the IpageObjectgenericMethods are not needed to be reimplmeneted. An IAlzatest instance will 'see' those methods
     This way it is highly flexible and in line with SOLID principles
     Also its very easily extendable and maintenable
  4. In case of Alza tests the generic pageObjectMethods - are filtered by using an interface which declares which methods should be available on an instance on IAlzaTest the IAlzaTest interface extends the interface which declares which methods should be available from the generic methods this way the methods declared on the IpageObjectgenericMethods are not needed to be reimplmeneted. An IAlzatest instance will 'see' those methods This way it is highly flexible and in line with SOLID principles Also its very easily extendable and maintenable


  5. There is a factory which takes care of creating instances of the registered classes whenever a new class of tests are created, the new class/interface(obviously an instance on the class can be created as well) needs to be included in the ITestFactory interface and then a getter to be created in the TestFactory class and then in the actual tests an instance of the particular class can be created and all the methods 'exclusive' to that class of tests will be available to use
  6. Also in case of SpiceJetTests when declaring the methods the objects -'this' - is returned, - this way method chaining is possible.
In the AlzaTests I did not use this approach. Again to show different flavours -that there are mutilple options how to approach a problem,. and that the question which one is the best can be answered looking at the particular problem. Sometimes method chaning is better, sometimes simply calling the methods on the instance, sometimes a mixture of the two.

  
   7. Another thing demonstrated is the fact that the page objects are exposed to the most upper layer.

      This way in the upper layer the methods defined in more hidden layers can take page objects here and use them - instead of the page obejects being hidden Once again these different variations can be used interchangably and use them based on requirements


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

7. These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests from the repo of the app) to run tests for other applications (for instance bookstore,etc)

8. there are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties
