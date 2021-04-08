package com.bblog.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
//        tags = {"@Run"},
        glue = {"com.bblog.tests.config", "com.bblog.tests.hooks",
                "com.bblog.tests.steps", "com.bblog.tests.actions",
                "com.bblog.tests.utils"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        features = {"src/test/resources/features/"})
@ContextConfiguration
@ComponentScan("classpath:com.bblog.tests")
public class CucumberRunner {
}
