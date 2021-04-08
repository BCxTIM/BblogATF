@RemoveArticle
Feature: update Article

  Background: Login as user with valid credentials
    Given user opens application page
    And user clicks to signInLink
    And login as a user
      | username | email                | password |
      | bcxtim   | bcxtim@bcxtim.bcxtim | 13123974 |

  @T5
  Scenario: Update Article from container block
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created
    When navigate to created article link
    And ensure that user is on ArticlePage
    And user clicks to editArticleFromContainer
    Then ensure that user is on EditArticlePage
    When user updates a new article with following data:
      | title  | about  | article  | tag  |
      | title1 | about1 | article1 | tag1 |
    And ensure that user is on ArticlePage
    Then ensure that article has been updated

  @T6
  Scenario: Update Article from article actions block
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created
    When navigate to created article link
    And ensure that user is on ArticlePage
    And user clicks to editArticleFromActions
    Then ensure that user is on EditArticlePage
    When user updates a new article with following data:
      | title  | about  | article  | tag  |
      | title1 | about1 | article1 | tag1 |
    And ensure that user is on ArticlePage
    Then ensure that article has been updated

  @T7
  Scenario: Update Article with empty fields
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created
    When navigate to created article link
    And ensure that user is on ArticlePage
    And user clicks to editArticleFromActions
    Then ensure that user is on EditArticlePage
    When user updates a new article with following data:
      | title | about | article | tag |
      |       |       |         |     |
    Then ensure that user is on EditArticlePage
    And ensure that article has not been updated


    @T8
  @RemovePreviousArticleFromAnotherUser
  Scenario: Update not user article
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created

    When user logout
    And user opens application page
    And ensure that user is on MainPage
    And user clicks to signInLink
    And login as a user
      | username | email                   | password |
      | bcxtim1  | bcxtim1@bcxtim1.bcxtim1 | 13123974 |
    And navigate to created article link
    Then ensure that user is on ArticlePage
    And user couldn't edit article


