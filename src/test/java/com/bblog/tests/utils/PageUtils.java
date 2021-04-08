package com.bblog.tests.utils;

import com.bblog.tests.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageUtils {

    @Autowired
    WebDriver webDriver;

    private static final String _prefix = "com.bblog.tests.pages.";

    public  AbstractPage GetInstance (String page) {
        try {
            Class<?> cls = Class.forName(_prefix + page);
            return (AbstractPage) cls.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
