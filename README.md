# Selenium Framework



Selenium framework

A. the architecture itself shows a kind of approach similar to how it is done in bigger projects

  1. The methods of the test classes -for instance SpiceJetTests- are available through an interface.
     
      This way it can be controlled which methods are available of the entirety of methods.
  
     In some projects a group of test automation engineers are interacting with the page objects,etc and are implementing methods, etc
     And other test automation engineers are working on creating tests -using the methods available.
  
  2. The driver is also exposed to the layer where the particular tests are created, - most of the methods usually used will have had an implementation and therefore no need to overwrite them
  
     This approach is achieved by layering responsibility of classes/interfaces and hiding some of the methods
  
     This can be reversed by taking away those layers 

      Depending on the expectations the tests can be created this way or the other

  3. Also here until now I have used returning the objects -'this' - in the test classes - this way method chaining is possible.
  
      Again it is entirely up to taste. Some engineers like to call every single method on an instance , other like method chaning, others like a mix of these
  
  4. Another thing demonstrated is the fact that the page objects are exposed to the most upper layer.
  
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

7. These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests from the repo of the app) to run tests for other applications (for instance bookstore,etc)

8. there are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties




