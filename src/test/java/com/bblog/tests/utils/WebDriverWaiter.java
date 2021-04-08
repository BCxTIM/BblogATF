package com.bblog.tests.utils;

import com.bblog.tests.atfexception.ATFException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.String.format;

public class WebDriverWaiter {

    private final static int TIMEOUT = 10;

    public static void waitUntilElementToContainValue(final WebDriver webDriver, final WebElement webElement, final String text) throws ATFException {
        try {
            Wait<WebDriver> wait = new FluentWait<>(webDriver)
                    .withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(1));
            ExpectedCondition<Boolean> expectedCondition = arg0 -> webElement.getAttribute("value").contains(text);
            wait.until(expectedCondition);
        } catch (Exception e) {
            throw new ATFException(format("Element {%s}, hasn't contain expected value {%s}", webElement.getAttribute("value"), text));
        }

    }

    public static void waitUntilElementToContainText(final WebDriver webDriver, final WebElement webElement, final String text) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
        wait.until(waitForElementToHaveText(webElement, text));
    }

    public synchronized static void waitForUrlContains(String expectedString, WebDriver webDriver) throws ATFException {
        try {
            Wait<WebDriver> wait = new FluentWait<>(webDriver)
                    .withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(1));
            ExpectedCondition<Boolean> expectedCondition = arg0 -> webDriver.getCurrentUrl().contains(expectedString);
            wait.until(expectedCondition);
        } catch (Exception e) {
            throw new ATFException(format("User is not on page contained {%s}, but on page {%s}", expectedString, webDriver.getCurrentUrl()));
        }
    }

    public synchronized static List<WebElement> waitCollectionElementsByXpath(int size, List<WebElement> elements, WebDriver webDriver) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(webDriver)
                    .withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(1));
            ExpectedCondition<Boolean> expectedCondition = arg0 -> elements.size() == size;
            wait.until(expectedCondition);
        } catch (Exception e) {
        }
        return elements;

    }

    public static ExpectedCondition<Boolean> waitForElementToHaveText(final WebElement element, final String expectedText) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return element.getText().contains(expectedText);
                } catch (Exception e) {
                    return false; // catchall fail case
                }
            }

            public String toString() {
                return "an element to have specific text";
            }
        };
    }
}
