package com.bblog.tests.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class BrowserConfig {

    private WebDriver webDriver;

    @Value("${browser}")
    private String browserName;

    @Value("${application.url}")
    private String url;


    @Bean(name = "webDriver", destroyMethod = "quit")
    public WebDriver getWebDriver() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        DriverManagerType driverType = DriverManagerType.valueOf(browserName.toUpperCase());
        WebDriverManager.getInstance(driverType).setup();
        Class<?> browserClass = Class.forName(driverType.browserClass());
        webDriver = (WebDriver) browserClass.newInstance();

        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        webDriver.get(url);

        return webDriver;

    }

}
