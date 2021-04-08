package com.bblog.tests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Getter
public class SettingsPage extends AbstractPage {
    public SettingsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public final String url = "settings";

    private static Logger logger = LoggerFactory.getLogger(ArticlePage.class);


    @FindBy(xpath = "//button[@class='btn btn-outline-danger']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/settings']")
    private WebElement settingsLink;

    public void logout() {
        logger.info("User clicks logout button");
        logoutButton.click();
    }


    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = SettingsPage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
