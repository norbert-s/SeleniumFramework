# Selenium Framework


Selenium framework

- the webdriver instances -henceforth - the tests are running in Docker

- currently Chrome, EDGE, Firefox instances are created but it can be easily extended

- The test are running concurrently -> by either referencing classes or methods

- concurrency is set such that all the methods are being run concurrently using the resources in a thread safe way

- the test are run in a data driven manner, the whole test oracle - meaning that the data supplied and the expectations supplied come from json files

- the same tests can be run with different test data -by json files

- there is a pipeline which runs tests on different branches

  - and if the tests fail then push to main is not permitted

  - These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout(triggering this repo to run specific tests from the repo of the app) to run tests for other applications (for instance bookstore,etc)

- there are numerous values that can be supplied from the command line which are all set a default value for in pom.xml as properties

  - reading data from db and different resources,(json, xml, csv, etc)
    - and based on the input running different tests
  - reporting to db, json, xml, differnt reporting frameworks
  - creating list of objects of pages and then dinamically, randomly selecting a numebr of them and proceeding to - for instance - select a product, compare the attributes of the product selected, checkout page, all of the related pages, etc (in this way profiling one product -with obviously options to take into account the differences - all the other products can also be tested)
  - pausing the pipeline- with polling a google excel sheet
  - etc


