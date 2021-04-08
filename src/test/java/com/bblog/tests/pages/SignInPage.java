package com.bblog.tests.pages;

import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.User;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static com.bblog.tests.utils.WebDriverWaiter.waitUntilElementToContainValue;
import static java.lang.String.format;

@Component
@Getter
public class SignInPage extends AbstractPage {
    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    private static Logger logger = LoggerFactory.getLogger(SignInPage.class);

    private final String url = "login";

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public void loginAsUser(User user) throws ATFException {
        usernameField.sendKeys(user.getEmail());
        logger.info(format("Username is set to {%s}", user.getEmail()));
        passwordField.sendKeys(user.getPassword());
        logger.info(format("Password is set to {%s}", user.getPassword()));
        waitUntilElementToContainValue(this.getWebDriver(), usernameField, user.getEmail());
        waitUntilElementToContainValue(this.getWebDriver(), passwordField, user.getPassword());
        System.setProperty("username", user.getUsername());
        logger.info(format("User clicks {%s}", submitButton.getText()));
    }

    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = SignInPage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
