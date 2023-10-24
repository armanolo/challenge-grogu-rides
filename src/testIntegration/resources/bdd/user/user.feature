Feature: Vehicles

  Scenario: 001 - Save a user
    Given user with uuid '8984fc9e-acba-4a28-ad29-2203fb7ebb80', dni '13882161M' and name 'Manolo Martin'
    When I submit this information to save a new user
    Then I receive a correct created response: user

  Scenario: 002 - Get users information
    Given the following users exist in DB: user
      | e96be326-08cb-45ea-96ac-8fbe871b3c6f  | 27650297L  | Manolo Martin       |
      | 4c1cb645-79fa-4ebe-a243-7d4794aadeb1  | 40861030N  | Miguel Angel Martin |
    When I request info for the user with id 'e96be326-08cb-45ea-96ac-8fbe871b3c6f'
    Then I receive a correct response: user
    And verify a user with id 'e96be326-08cb-45ea-96ac-8fbe871b3c6f' dni '27650297L' and name 'Manolo Martin'
    When I request info for the user with id '4c1cb645-79fa-4ebe-a243-7d4794aadeb1'
    Then I receive a correct response: user
    And verify a user with id '4c1cb645-79fa-4ebe-a243-7d4794aadeb1' dni '40861030N' and name 'Miguel Angel Martin'

  Scenario: 003 - Get error not user found
    When I request info for the user with id 'fb33a0e7-a0c4-4e3b-ae47-2c7916af888e'
    Then I receive a no found: user

  Scenario: 004 - Get error dni duplicated
    Given the following users exist in DB: user
      | e96be326-08cb-45ea-96ac-8fbe871b3c6f  | 27650297L  | Manolo Martin       |
    Given user with uuid '8984fc9e-acba-4a28-ad29-2203fb7ebb80', dni '27650297L' and name 'Manolito'
    When I submit this information to save a new user
    Then I receive a bad response: user