package com.bblog.tests.steps;

import com.bblog.tests.actions.CommonActions;
import com.bblog.tests.atfexception.ATFException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {

    @Autowired
    private CommonActions commonActions;

    @Given("^user opens application page$")
    public void userOpensApplicationPage() {
        commonActions.openApplicationPage();
    }


    @Given("^user clicks to (.*)$")
    public void userClicksToElementFromPage(String element) {
        commonActions.clickElementFromPage(element);
    }

    @Given("^user fills (.*) to (.*)$")
    public void userFillsDataToElement(String data, String element) {
        commonActions.fillDataForElementFromPage(data, element);
    }

    @Then("^ensure that user is on (.*)$")
    public void ensureThatUserIsOnPage(String page) throws ATFException {
        commonActions.waitForPageLoaded(page);
    }

    @Given("^user logout$")
    public void userLogout() throws ATFException {
        commonActions.logout();
    }

    @Given("^user is waiting (\\d+) seconds$")
    public void userWaits(int seconds) {
        commonActions.userIsWaitingSeconds(seconds);
    }


}
