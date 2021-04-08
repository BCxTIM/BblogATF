@RemoveArticle
Feature: Create article

  Background: Login as user with valid credentials
    Given user opens application page
    And user clicks to signInLink
    And login as a user
      | username | email                | password |
      | bcxtim   | bcxtim@bcxtim.bcxtim | 13123974 |

  @T3
  Scenario: Create article successful with data
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag  |
      | title | about | article | test |
    Then ensure that user is on ArticlePage
    And ensure that article has been created

  @T4
  Scenario: Article with all empty fields should not be created
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      |       |       |         |     |
    And user is waiting 3 seconds
    Then ensure that user is on NewArticlePage
    And ensure that article has not been created
    And ensure that user has not any articles created


