@RemoveArticle
Feature: Delete article

  Background: Login as user with valid credentials
    Given user opens application page
    And user clicks to signInLink
    And login as a user
      | username | email                | password |
      | bcxtim   | bcxtim@bcxtim.bcxtim | 13123974 |

  @T9
  Scenario: Delete created article from container block
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created
    When delete article from container block
    Then ensure that user has not any articles created
    When navigate to created article link
    Then ensure that user is on LoggedInPage


  @T10
  Scenario: Delete created article from article actions block
    When user clicks to newArticleLink
    Then ensure that user is on NewArticlePage
    When user creates a new article with following data:
      | title | about | article | tag |
      | title | about | article | tag |
    Then ensure that user is on ArticlePage
    And ensure that article has been created
    When delete article from article action block
    Then ensure that user has not any articles created
    When navigate to created article link
    Then ensure that user is on LoggedInPage

  @T11
  @RemovePreviousArticleFromAnotherUser
  Scenario: Delete not user article
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
    And user couldn't remove article
