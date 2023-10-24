Feature: Vehicles

  Scenario: 001 - Create a rent vehicle
    Given the following users exist in DB: rent
      | e96be326-08cb-45ea-96ac-8fbe871b3c6f  | 27650297L  | Manolo Martin       |
      | 4c1cb645-79fa-4ebe-a243-7d4794aadeb1  | 40861030N  | Miguel Angel Martin |
    And the following vehicles exist in DB: rent
      | 566c8781-cfd2-499e-853f-93046cb5d876  | 5 |
      | 5b44e203-4473-4e50-93a0-4a9511766ce8  | 2 |
      | 46da14bf-ad5c-4aa5-b044-afe02f62050b  | 3 |
    When I submit to rent user id 'e96be326-08cb-45ea-96ac-8fbe871b3c6f' during 2 hours with 2 seats
    Then I receive a correct response: rent
    And verify that vehicle rented has id '5b44e203-4473-4e50-93a0-4a9511766ce8' by user id 'e96be326-08cb-45ea-96ac-8fbe871b3c6f'

  Scenario: 002 - Get  list of rented vehicles
    Given a list of rent in database
    When request a list of rents
    Then I receive a correct response: rent
    And verify the list of rents

  Scenario: 003 - Drop off a rented vehicle
    Given a list of rent in database
    When I submit to drop off for the rent vehicle with id '0f60fcc9-f104-4dce-93c4-ea2648fb3571'
    Then I receive a correct response: rent
    And verify that rented vehicle with id '0f60fcc9-f104-4dce-93c4-ea2648fb3571' has been dropped off

  Scenario: 004 - Try renting a rented card but there is no vehicle available
    Given a rent in database
    When I submit to rent user id '32281f54-6424-4047-8410-884ab69d5eeb' during 2 hours with 5 seats
    Then I receive a correct accepted response but not done: rent
    And verify that 1 vehicles has been rented

  Scenario: 005 - Try renting a vehicle with less room that driver needs
    Given the following users exist in DB: rent
      | e96be326-08cb-45ea-96ac-8fbe871b3c6f  | 27650297L  | Manolo Martin       |
    And the following vehicles exist in DB: rent
      | 5b44e203-4473-4e50-93a0-4a9511766ce8  | 2 |
    When I submit to rent user id 'e96be326-08cb-45ea-96ac-8fbe871b3c6f' during 2 hours with 5 seats
    Then I receive a correct accepted response but not done: rent
    And verify that 0 vehicles has been rented