package com.bblog.tests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Component
@Getter
public class ArticlePage extends AbstractPage {
    public ArticlePage(WebDriver webDriver) {
        super(webDriver);
    }

    private static Logger logger = LoggerFactory.getLogger(ArticlePage.class);

    private final String url = "article";

    @FindAll(@FindBy(xpath = "//button[contains(text(), 'Delete Article')]"))
    private List<WebElement> deleteButtons;

    @FindAll(@FindBy(xpath = "//a[@class='btn btn-sm btn-outline-secondary']"))
    private List<WebElement> editButtons;

    @FindBy(xpath = "//div[@class='article-actions']//button[contains(text(), 'Delete Article')]")
    private WebElement deleteButtonFromActions;

    @FindBy(xpath = "//div[@class='container']//button[contains(text(), 'Delete Article')]")
    private WebElement deleteButtonFromContainer;

    @FindBy(xpath = "//div[@class='container']//a[@class='btn btn-sm btn-outline-secondary']")
    private WebElement editArticleFromContainer;

    @FindBy(xpath = "//div[@class='article-actions']//a[@class='btn btn-sm btn-outline-secondary']")
    private WebElement editArticleFromActions;

    @FindBy(xpath = "//h1")
    private WebElement articleTitle;

    @FindBy(xpath = "//div[@class='row article-content']")
    private WebElement articleContent;

    public void deleteArticle(String url) {
        logger.info("User start to delete article");
        webDriver.get(url);
        Optional<WebElement> firstDeleteElementButton = deleteButtons.stream().findFirst();
        firstDeleteElementButton.ifPresent(WebElement::click);
    }


    public void deleteArticleFromContainer(String url) {
        logger.info("User start to delete article");
        webDriver.get(url);
        Optional<WebElement> firstDeleteElementButton = deleteButtons.stream().findFirst();
        firstDeleteElementButton.ifPresent(WebElement::click);
    }

    public void deleteArticleFromArticleActions(String url) {
        logger.info("User start to delete article");
        webDriver.get(url);
        Optional<WebElement> firstDeleteElementButton = deleteButtons.stream().findFirst();
        firstDeleteElementButton.ifPresent(WebElement::click);
    }

    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = ArticlePage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
