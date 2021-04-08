package com.bblog.tests.actions;

import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.User;
import com.bblog.tests.pages.SignInPage;
import com.bblog.tests.utils.DataKeys;
import com.bblog.tests.utils.PageEnum;
import com.bblog.tests.utils.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginActions {

    @Autowired
    private SignInPage signInPage;

    @Autowired
    private CommonActions commonActions;

    @Autowired
    private ScenarioContext scenarioContext;


    public void loginAsUser(User user) throws ATFException {
        commonActions.waitForPageLoaded(PageEnum.SignInPage.name());
        signInPage.loginAsUser(user);
        commonActions.userIsWaitingSeconds(1);
        signInPage.getSubmitButton().click();
        commonActions.waitForPageLoaded(PageEnum.LoggedInPage.name());
        scenarioContext.setContext(DataKeys.USER, user);
    }

}
