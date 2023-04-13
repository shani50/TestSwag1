@Login
Feature: Login scenarios of SRK Consistecey Website
  # LoginNegative1
  @negative
  Scenario: User will try to login with wrong username
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_01 |
    And User click the login button
#    Then User gets alert message
#      |alert_message|

  #LoginNefative2
#  @negative
  Scenario: User will try to login with wrong password
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_02 |
    And User click the login button
#    Then User gets alert message
#      |alert_message|
#
#    # LoginNegative3
#  @negative
  Scenario: User will try to login with wrong username and wrong password
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_03 |
    And User click the login button
#    Then User gets alert message
#      | alert_message|
#
#     #LoginNegative4
#  @negative
  Scenario: User try to login without entering username
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_04 |
    And User click the login button
#    Then User gets alert message
#      | Invalid|
#
#    #LoginNegative5
#  @negative
  Scenario: User try to login without entering password
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_05 |
    And User click the login button
#    Then User gets alert message
#      | Invalid|
#
#     #LoginNegative6
#  @negative
  Scenario: User try to login without entering username and password
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_05 |
    And User click the login button
#    Then User gets alert message
#      | Invalid|
#
#    #LoginNegative7
#  @negative
  Scenario: User try to login by entering password with capital alphabet
    Given User is on the Srkay  home page
    When User enters username and password
      | negative_test_case_06 |
    And User click the login button
#    Then User gets alert message
#      | alert_message|
#
#
#    LoginPositive1
  Scenario: User try to login to the Srk homepage by entering valid username and password
    Given User is on the Srkay  home page
    When User enters username and password
      | positive_test_case_01 |
    And User click the login button
    Then User should successfully login









