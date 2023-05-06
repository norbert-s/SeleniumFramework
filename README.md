# Selenium Framework



Selenium framework

- the architecture itself shows a kind of approach used in bigger projects

  1. the webdriver instance is not exposed to the test classes (those which are are executed because testng recognise them for having @Test annotation)
  2. only certain methods are exposed to these classes (@Test)
  3. this kind of approach is used when a groups of test automation engineers are interacting with the page objects, and are implementing methods, etc
  4. and other test automation engineers use those methods to actually create the tests
  5. obviously this approach is achieved with layering responsibility of classes/interfaces
  6. and can be achieved by taking away those layyers that block the exposure of the driver
  7. depending on the expectations the tests can be created this way or the other

- the webdriver instances -henceforth - the tests are running in Docker

- running concurrently - concurrency is set such that all the methods are being run concurrently using the resources in a thread safe way

- tests are run by using test data from json, and through injecting the test data by dataprovider

- currently Chrome, EDGE, Firefox driver instances are run

- the tests are run in a data driven manner, the whole test oracle - meaning that the data supplied and the expectations supplied come from json files through dataproviders

- the same tests are repeated with different test data -by json files - and the tests run as many times as the supplied data triggers them to

- there is a pipeline which runs tests on the branches they are pushed onto

  - and if the tests fail then push to main is not permitted

  - These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests from the repo of the app) to run tests for other applications (for instance bookstore,etc)

- there are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties




