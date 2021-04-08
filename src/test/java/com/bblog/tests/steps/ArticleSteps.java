package com.bblog.tests.steps;

import com.bblog.tests.actions.ArticleActions;
import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.Article;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleSteps {

    @Autowired
    private ArticleActions articleActions;


    @Given("^user creates a new article with following data:$")
    public void userCreatesArticleWithFollowingData(Article article) throws ATFException {
        articleActions.createArticle(article);
    }

    @Given("^user updates a new article with following data:$")
    public void userUpdatesArticleWithFollowingData(Article article) throws ATFException {
        articleActions.updateArticle(article);
    }

    @Then("^ensure that article has been (created|updated)$")
    public void articleHasBeenCreatedUpdated(String isUpdatedOrCreated) throws ATFException {
        articleActions.ensureThatArticleHasBeenCreatedUpdated();
    }

    @Then("^ensure that article has not been created$")
    public void articleHasNotBeenCreated() {
        articleActions.ensureThatArticleHasNotBeenCreated();
    }

    @Then("^ensure that article has not been updated")
    public void articleHasNotBeenCreatedUpdated() throws ATFException {
        articleActions.ensureThatArticleHasNotBeenUpdated();
    }

    @Then("^ensure that user has not any articles created$")
    public void ensureThatUserHasntAnyCreatedArticlesBefore() throws ATFException {
        articleActions.ensureThatUserHasntAnyCreatedArticles();
    }

    @Given("^delete article$")
    public void deleteArticle() throws ATFException {
        articleActions.deleteArticle();
    }

    @Given("^delete article from container block$")
    public void deleteArticleFromContainerBlock() throws ATFException {
        articleActions.deleteArticleFromContainerBlock();
    }

    @Given("^delete article from article action block$")
    public void deleteArticleFromArticleActionBlock() throws ATFException {
        articleActions.deleteArticleFromArticleActionsBlock();
    }


    @Given("^navigate to created article link$")
    public void navigateToCreatedArticleLink() throws ATFException {
        articleActions.navigateToCreatedArticleLink();
    }

    @Given("^user couldn't remove article$")
    public void userCouldntRemoveArticle() {
        articleActions.userIsImpossibleToRemoveArticle();
    }

    @Given("^user couldn't edit article$")
    public void userCouldntEditArticle() {
        articleActions.userIsImpossibleToEditArticle();
    }
}
