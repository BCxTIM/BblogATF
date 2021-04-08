package com.bblog.tests.steps;

import com.bblog.tests.actions.LoginActions;
import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.User;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSteps {

    @Autowired
    private LoginActions loginActions;


    @Given("^login as a user$")
    public void loginAsUser(User user) throws ATFException {
        loginActions.loginAsUser(user);
    }
}
