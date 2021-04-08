package com.bblog.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class LoggedInPage extends AbstractPage {
    public LoggedInPage(WebDriver webDriver) {
        super(webDriver);
    }

    private String url = "";

    @FindBy(xpath = "//a[@class='nav-link' and contains(text(),'Home')]")
    private Field homeLink;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/editor']")
    private WebElement newArticleLink;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/settings']")
    private WebElement settingsLink;


    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = LoggedInPage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
