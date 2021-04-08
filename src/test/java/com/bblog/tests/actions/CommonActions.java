package com.bblog.tests.actions;

import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.pages.AbstractPage;
import com.bblog.tests.pages.SettingsPage;
import com.bblog.tests.utils.PageEnum;
import com.bblog.tests.utils.PageUtils;
import com.bblog.tests.utils.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.bblog.tests.assertions.AssertATF.assertThat;
import static com.bblog.tests.utils.DataKeys.*;
import static com.bblog.tests.utils.WebDriverWaiter.waitForUrlContains;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@Component
public class CommonActions {

    @Autowired
    private PageUtils pageUtils;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private SettingsPage settingsPage;

    @Autowired
    private ScenarioContext scenarioContext;

    @Value("${application.url}")
    private String applicationUrl;


    private static Logger logger = LoggerFactory.getLogger(CommonActions.class);

    //TODO move this to Enum
    private final String fieldUrl = "url";
    private final String articleLink = "article";

    public void openApplicationPage() {
        webDriver.navigate().to(applicationUrl);
        AbstractPage abstractPage = pageUtils.GetInstance(PageEnum.MainPage.name());
        scenarioContext.setContext(PAGE, abstractPage);
    }

    public void userNavigatesToPage(String page) throws ATFException {
        AbstractPage abstractPage = pageUtils.GetInstance(page);
        String url = (String) abstractPage.getField(fieldUrl);
        webDriver.navigate().to(applicationUrl + url);
        waitForPageLoaded(page);
        scenarioContext.setContext(PAGE, abstractPage);
        scenarioContext.setContext(CURRENT_URL, webDriver.getCurrentUrl());
    }

    public void clickElementFromPage(String element) {
        AbstractPage abstractPage = (AbstractPage) scenarioContext.getContext(PAGE);
        WebElement webElement = (WebElement) abstractPage.getField(element);
        logger.info(format("User clicks to element {%s}", webElement.getText()));
        webElement.click();
    }

    public void fillDataForElementFromPage(String data, String element) {
        AbstractPage abstractPage = (AbstractPage) scenarioContext.getContext(PAGE);
        WebElement webElement = (WebElement) abstractPage.getField(element);
        logger.info(format("User fills data {%s} to element {%s}", data, webElement.getText()));
        webElement.sendKeys(data);
    }

    public void waitForPageLoaded(String page) throws ATFException {
        AbstractPage abstractPage = pageUtils.GetInstance(page);
        String string = (String) abstractPage.getField(fieldUrl);

        waitForUrlContains(string, webDriver);

        String currentUrl = abstractPage.getWebDriver().getCurrentUrl();

        assertThat(format("User is on expected page %s with url %s and string {%s}", abstractPage.getClass().getName(), currentUrl, string),
                currentUrl, containsString(string));

        scenarioContext.setContext(PAGE, abstractPage);
        scenarioContext.setContext(CURRENT_URL, currentUrl);

        //TODO reimplement this later
        if (currentUrl.contains(articleLink)) {
            scenarioContext.setContext(CREATED_ARTICLE_URL, currentUrl);
        }

    }

    public void logout() throws ATFException {
        userNavigatesToPage(PageEnum.SettingsPage.name());
        waitForPageLoaded(PageEnum.SettingsPage.name());
        settingsPage.logout();
        waitForPageLoaded(PageEnum.SignInPage.name());
    }

    public synchronized void userIsWaitingSeconds(int seconds) {
        synchronized (webDriver) {
            try {
                webDriver.wait(seconds * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void elementIsNotDisplayed(WebElement element) {
        assertThat(format("Element {%s} is not displayed", element), element.isDisplayed(), is(false));
    }

}
