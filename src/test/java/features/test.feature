Feature: A new feature

  Scenario: Scenario 1
    When user opens browser
    Then he should see url loaded
#    Then fail this scenario

  Scenario: Scenario 2
    When user opens browser
    Then he should see url loaded
    Then user enters in search "Maneendra"
#    Then fail this scenario

  Scenario: Scenario 3
    When user opens browser
    Then he should see url loaded
    Then fail this scenario

  Scenario: Scenario 4
    When user opens browser
    Then he should see url loaded
