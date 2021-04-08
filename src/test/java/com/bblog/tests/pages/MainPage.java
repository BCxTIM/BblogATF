package com.bblog.tests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Getter
public class MainPage extends AbstractPage {

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    private String url = "";

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/login']")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/register']")
    private WebElement signUpLink;

    @FindBy(xpath = "//a[@class='nav-link' and contains(text(), 'Global Feed')]")
    private WebElement globalFeedLink;


    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = MainPage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

