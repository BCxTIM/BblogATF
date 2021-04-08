package com.bblog.tests.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static java.lang.String.format;

public class BeforeHook {


    @Autowired
    private WebDriver webDriver;

    @Value("${application.url}")
    private String url;

    private static Logger logger = LoggerFactory.getLogger(AfterHook.class);

    @Before
    public void openApplicationUrl() {
        webDriver.get(url);
        logger.info(format("User opens url {%s}", url));
    }

    @Before
    public void scenarioName(Scenario scenario) {
        logger.info("\n" + scenario.getName());
    }
}
