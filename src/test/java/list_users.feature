Feature: Retrieving list of users
  As a user
  I want to view a list of users
  So that I can find and interact with other users

  Scenario Outline: User retrieves details of a specific user
    Given the user is authenticated
    When the user requests details for user with ID "<userId>"
    Then the details for the user should be successfully retrieved
    And the response should include user details like name "<name>" and email "<email>"

    Examples:
      | userId | name          | email               |
      | 1      | Leanne Graham | Sincere@april.biz   |
      | 2      | Ervin Howell  | Shanna@melissa.tv   |
