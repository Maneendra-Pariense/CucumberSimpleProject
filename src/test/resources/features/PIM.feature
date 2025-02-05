@PIM
Feature: Validation of PIM scenarios
  Background:
      Given user logged in to the application with username "Admin" and password "admin123"
  #Implement the following scenarios
    Scenario: Add Employee
      Given user is on "PIM" Page
        And user clicks on Add Employee tab
        And user should see Add Employee title
      When user enters Employee Full Name as "Maneendra"
        And user enters Middle name as "MiddleName"
        And user enters LastName as "LastName"
        And user enters Employee Id "1111"
        And user clicks on save button
      Then user should see Personal Details screen
    Scenario: Validate newly created user in Employee List Tab
      Given user is on "PIM" Page
      When user clicks on Employee List tab
        And user enters Employee Name as "Maneendra"
        And user clicks on Search button
      Then user should see the record with Id "1111"
