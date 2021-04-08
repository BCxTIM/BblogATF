package com.bblog.tests.hooks;

import com.bblog.tests.actions.ArticleActions;
import com.bblog.tests.actions.CommonActions;
import com.bblog.tests.actions.LoginActions;
import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.config.TakesScreenshots;
import com.bblog.tests.model.User;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.bblog.tests.utils.PageEnum.*;

public class AfterHook {

    @Autowired
    private TakesScreenshots takesScreenshots;

    @Autowired
    private ArticleActions articleActions;

    @Autowired
    private CommonActions commonActions;

    @Autowired
    private LoginActions loginActions;


    private static Logger logger = LoggerFactory.getLogger(AfterHook.class);


    @After
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            takesScreenshots.takesScreenshot(scenario);
        }

    }

    @After(value = "@RemovePreviousArticleFromAnotherUser")
    public void removeFromAnotherUserArticle() throws ATFException {
        User user = new User("bcxtim", "bcxtim@bcxtim.bcxtim", "13123974");
        commonActions.logout();
        commonActions.openApplicationPage();
        commonActions.waitForPageLoaded(MainPage.name());
        commonActions.clickElementFromPage("signInLink");
        commonActions.waitForPageLoaded(SignInPage.name());
        loginActions.loginAsUser(user);
        articleActions.navigateToCreatedArticleLink();
        commonActions.waitForPageLoaded(ArticlePage.name());
        articleActions.deleteArticle();

    }

    @After(order = 0)
    public void logout() throws ATFException {
        commonActions.logout();
    }

    @After(value = "@RemoveArticle", order = 1)
    public void removeArticle() throws ATFException {
        articleActions.deleteArticle();
    }

}
