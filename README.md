# Selenium Framework


Selenium framework under development

- running concurrently by classes and methods in a thread-safe way

- there is a pipeline which runs tests on different branches

  - and if the tests fail then push to main is not permitted

  - These selenium tests in a pipeline make a lot more sense when their role is to test some applications, so later on i will be using this repository of UI test framework in a multi pipeline layout to trigger and run tests for other applications (for instance spring-boot-issuetracker)

- have been extended and will be extended with many things

  - reading data from db and different resources,(json, xml, csv, etc)
    - and based on the input running different tests
  - using different data source - db, json, excel, etc
  - serializing / deserializing
  - reporting to db, json, xml, differnt reporting frameworks
  - creating list of objects of pages and then dinamically, randomly selecting a numebr of them and proceeding to - for instance - select a product, compare the attributes of the product in the basket, checkout page, all of the related pages, etc (in this way profiling one product -with obviously options to take into account the differences - all the other products can also be tested)
  - pausing the pipeline- with polling a google excel sheet
  - and many more


