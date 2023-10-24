Feature: Vehicles

  Scenario: 001 - Save a vehicle
    Given vehicle with uuid '05bdc208-5efc-4b0e-9e1e-dbcb29443846' with seats 5
    When I submit this information to save a new vehicle
    Then I receive a correct response: vehicle
    And vehicle with uuid '05bdc208-5efc-4b0e-9e1e-dbcb29443846' with seats 5 is created in DB

  Scenario: 002 - Get list of vehicles
    Given a list of vehicle in database
    When I request a list of vehicles
    Then I receive a correct response: vehicle
    And verify a list of vehicles