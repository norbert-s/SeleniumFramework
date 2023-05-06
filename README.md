# Selenium Framework



Selenium framework

- the webdriver instances -henceforth - the tests are running in Docker

- running concurrently - by either referencing classes or methods (methods can be run in parallel or classes can be run in parallel)

- tests are run by using test data from json, and through injecting the test data by dataprovider

- currently Chrome, EDGE, Firefox instances are created but it can be easily extended

- The test are running concurrently -> by either referencing classes or methods

- concurrency is set such that all the methods are being run concurrently using the resources in a thread safe way

- the tests are run in a data driven manner, the whole test oracle - meaning that the data supplied and the expectations supplied come from json files

- the same tests can be run with different test data -by json files - and the test run as many times as the supplied data triggers them to

- there is a pipeline which runs tests on the branches they are pushed onto

  - and if the tests fail then push to main is not permitted

  - These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests from the repo of the app) to run tests for other applications (for instance bookstore,etc)

- there are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties




