#@Admin @hero

Feature: Admin page validations

  Background:
    Given user logged in to the application with username "Admin" and password "admin123"

  @villaian
  Scenario: Validate search System Users should show proper results
    Given user is on "Admin" Page
    When user enters Username as "Admin"
    And user selects UserRole as "Admin"
#      And user selects Employee Name as "Vladislav Ivanovich"
    And user selects Status as "Enabled"
    And user clicks on Search button
    Then user should see the record with following details
      | Username | User Role | Employee Name | Status  |
      | Admin    | Admin     | how how       | Enabled |

