Feature: Managing posts
  As a user
  I want to make and view posts
  So that I can share and read content

  Scenario Outline: User creates a new post
    Given the user is authenticated
    When the user submits a new post with title "<title>" and body "<body>"
    Then the post should be successfully created
    And the response should include the post details with title "<title>" and body "<body>"

    Examples:
      | title        | body                  |
      | My First Post| This is my first post!|
      | Hello World  | Hello, this is a post!|

  Scenario Outline: User views a post
    Given a post with title "<title>" exists
    When the user retrieves the post with the given title
    Then the post details are successfully retrieved
    And the response includes the title "<title>" and body "<body>"

    Examples:
      | title        | body                  |
      | My First Post| This is my first post!|
      | Hello World  | Hello, this is a post!|
