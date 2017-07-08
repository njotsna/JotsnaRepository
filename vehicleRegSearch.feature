Feature: Search for Vehicle Details DVLA site
  			In order to verify Make and color of vehicle
  			As a owner of vehicle
  			I want to enter my Vehicle registration

  @VehicleSearch
  Scenario: Search for Vehicle make and color from registration
    Given I navigate to DVLA website
    When I click on start now button
    And I enter my vehicle registration to verify make and color
    Then I close the browser
