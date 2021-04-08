package com.bblog.tests.config;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TakesScreenshots {

    @Autowired
    private BrowserConfig browserConfig;

    public void takesScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) browserConfig.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException webDriverException) {
            System.err.println(webDriverException.getMessage());
        } catch (ClassCastException | IllegalAccessException | InstantiationException | ClassNotFoundException classCastException) {
            classCastException.printStackTrace();
        }
    }

}
