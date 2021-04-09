package com.bblog.tests.actions;

import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.Article;
import com.bblog.tests.model.User;
import com.bblog.tests.pages.ArticlePage;
import com.bblog.tests.pages.EditArticlePage;
import com.bblog.tests.pages.NewArticlePage;
import com.bblog.tests.pages.ProfilePage;
import com.bblog.tests.utils.DataKeys;
import com.bblog.tests.utils.PageEnum;
import com.bblog.tests.utils.ScenarioContext;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bblog.tests.assertions.AssertATF.assertThat;
import static com.bblog.tests.utils.DataKeys.*;
import static com.bblog.tests.utils.WebDriverWaiter.waitCollectionElementsByXpath;
import static com.bblog.tests.utils.WebDriverWaiter.waitUntilElementToContainText;
import static java.lang.String.format;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

@Component
public class ArticleActions {

    private static Logger logger = LoggerFactory.getLogger(ArticleActions.class);


    @Autowired
    private NewArticlePage newArticlePage;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private ArticlePage articlePage;

    @Autowired
    private EditArticlePage editArticlePage;

    @Autowired
    private CommonActions commonActions;

    @Autowired
    private ProfilePage profilePage;

    @Autowired
    private ScenarioContext scenarioContext;

    private final String articleLink = "article";

    public void createArticle(Article article) throws ATFException {
        newArticlePage.createArticle(article);
        commonActions.userIsWaitingSeconds(2);
        newArticlePage.getPublishButton().click();
        logger.info(format("User clicks is set to {%s}", newArticlePage.getPublishButton().getText()));
        saveInContextArticle(article);
    }

    public void updateArticle(Article article) throws ATFException {
        editArticlePage.updateArticle(article);
        commonActions.userIsWaitingSeconds(2);
        editArticlePage.getPublishButton().click();
        logger.info(format("User clicks is set to {%s}", editArticlePage.getPublishButton().getText()));
        saveInContextArticle(article);
    }


    public void ensureThatArticleHasBeenCreatedUpdated() throws ATFException {
        Article article = (Article) scenarioContext.getContext(ARTICLE_CONTENT);
        String username = ((User) scenarioContext.getContext(USER)).getUsername();

        commonActions.userIsWaitingSeconds(2);
        assertThat(format("Article title {%s} is {%s}", articlePage.getArticleTitle().getText(),
                article.getTitle()), articlePage.getArticleTitle().getText(), is(article.getTitle()));
        assertThat(format("Article content {%s} is {%s}", articlePage.getArticleContent().getText(),
                article.getArticle()), articlePage.getArticleContent().getText(), is(article.getArticle()));

        commonActions.userNavigatesToPage(PageEnum.ProfilePage.name());
        commonActions.waitForPageLoaded(PageEnum.ProfilePage.name());
        waitUntilElementToContainText(profilePage.getWebDriver(), profilePage.getProfileName(), username);

        assertThat(format("Profile name {%s} is {%s}", profilePage.getProfileName().getText(), username),
                profilePage.getProfileName().getText(), is(username));


        WebElement lastArticleTitle = profilePage.getLastArticleTitle();
        WebElement lastArticleAbout = profilePage.getLastArticleAbout();
        assertThat(format("Article title {%s} is {%s}", lastArticleTitle.getText(), article.getTitle()),
                lastArticleTitle.getText(), is(article.getTitle()));
        assertThat(format("Article about {%s} is {%s}", lastArticleAbout.getText(), article.getAbout()),
                lastArticleAbout.getText(), is(article.getAbout()));


    }

    public void deleteArticleFromContainerBlock() throws ATFException {
        String createdArticleLink = (String) scenarioContext.getContext(DataKeys.CREATED_ARTICLE_URL);
        if (createdArticleLink != null) {
            articlePage.deleteArticleFromContainer(createdArticleLink);
            deleteArticleGeneric();
        }
    }

    public void deleteArticleFromArticleActionsBlock() throws ATFException {
        String createdArticleLink = (String) scenarioContext.getContext(DataKeys.CREATED_ARTICLE_URL);
        if (createdArticleLink != null) {
            articlePage.deleteArticleFromArticleActions(createdArticleLink);
            deleteArticleGeneric();
        }
    }


    public void deleteArticle() throws ATFException {
        String createdArticleLink = (String) scenarioContext.getContext(DataKeys.CREATED_ARTICLE_URL);
        if (createdArticleLink != null) {
            articlePage.deleteArticle(createdArticleLink);
            deleteArticleGeneric();
        }

    }

    public void ensureThatArticleHasNotBeenCreated() {
        String article = (String) scenarioContext.getContext(CREATED_ARTICLE_URL);
        assertThat("Article should not be created, but was",
                article, is(nullValue()));
    }

    public void ensureThatArticleHasNotBeenUpdated() throws ATFException {
        Article article = (Article) scenarioContext.getContext(ARTICLE_CONTENT);
        navigateToCreatedArticleLink();
        commonActions.waitForPageLoaded(PageEnum.ArticlePage.name());
        commonActions.clickElementFromPage("editArticleFromActions");
        commonActions.waitForPageLoaded(PageEnum.EditArticlePage.name());
        assertThat("", editArticlePage.getTitleField().getAttribute("value"), not(article.getTitle()));
        assertThat("", editArticlePage.getAboutField().getAttribute("value"), not(article.getAbout()));
        assertThat("", editArticlePage.getArticleField().getAttribute("value"), not(article.getArticle()));
        assertThat("", editArticlePage.getTagField().getAttribute("value"), not(article.getTag()));
    }

    public void ensureThatUserHasntAnyCreatedArticles() throws ATFException {
        commonActions.userIsWaitingSeconds(2);
        commonActions.userNavigatesToPage(PageEnum.ProfilePage.name());
        commonActions.waitForPageLoaded(PageEnum.ProfilePage.name());
        assertThat("User has not any created articles before",
                profilePage.getListArticles().size(), is(0));

    }

    public void navigateToCreatedArticleLink() throws ATFException {
        commonActions.userIsWaitingSeconds(1);
        String createdArticleLink = (String) scenarioContext.getContext(DataKeys.CREATED_ARTICLE_URL);
        if (createdArticleLink == null) {
            throw new ATFException("Article hasn't been created before");
        }
        webDriver.navigate().to(createdArticleLink);
    }

    public void userIsImpossibleToRemoveArticle() {
        List<WebElement> elements = articlePage.getEditButtons();
        elements.forEach(e -> commonActions.elementIsNotDisplayed(e));

    }

    public void userIsImpossibleToEditArticle() {
        List<WebElement> elements = articlePage.getDeleteButtons();
        elements.forEach(e -> commonActions.elementIsNotDisplayed(e));

    }

    private void deleteArticleGeneric() throws ATFException {
        scenarioContext.removeKey(DataKeys.CREATED_ARTICLE_URL);
        scenarioContext.removeKey(DataKeys.ARTICLE_CONTENT);

        commonActions.userIsWaitingSeconds(2);
        commonActions.userNavigatesToPage(PageEnum.ProfilePage.name());
        commonActions.waitForPageLoaded(PageEnum.ProfilePage.name());

//        //TODO find antother solution
//        commonActions.userNavigatesToPage(PageEnum.ProfilePage.name());
//        commonActions.waitForPageLoaded(PageEnum.ProfilePage.name());

        List<WebElement> listArticles =
                waitCollectionElementsByXpath(0, profilePage.getListArticles(), profilePage.getWebDriver());
        assertThat("Article was successful deleted", listArticles.size(), CoreMatchers.is(0));
    }

    private void saveInContextArticle(Article article) {
        commonActions.userIsWaitingSeconds(2);
        String currentUrl = webDriver.getCurrentUrl();

        if (currentUrl.contains(articleLink)) {
            scenarioContext.setContext(CREATED_ARTICLE_URL, currentUrl);
        }

        scenarioContext.setContext(ARTICLE_CONTENT, article);
    }
}
