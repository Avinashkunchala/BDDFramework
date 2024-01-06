Feature: Commenting on posts
  As a user
  I want to comment on posts
  So that I can engage in discussions

  Scenario Outline: User comments on a post
    Given the user is authenticated
    And a post with ID "<postId>" exists
    When the user submits a comment with postId "<postId>", name "<name>", email "<email>", and body "<commentBody>"
    Then the comment should be successfully added
    And the response should include the comment details with name "<name>", email "<email>", and body "<commentBody>"

    Examples:
      | postId | name         | email               | commentBody           |
      | 1      | John Doe     | john@example.com    | Great post!           |
      | 2      | Alice Smith  | alice@example.com   | Interesting perspective|

  Scenario: User views comments for a specific post
    Given a post with ID "1" exists
    When the user retrieves comments for the post with ID "1"
    Then the comments for the post should be successfully retrieved
