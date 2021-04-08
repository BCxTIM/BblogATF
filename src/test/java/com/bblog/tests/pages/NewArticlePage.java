package com.bblog.tests.pages;

import com.bblog.tests.atfexception.ATFException;
import com.bblog.tests.model.Article;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static com.bblog.tests.utils.WebDriverWaiter.waitUntilElementToContainValue;
import static java.lang.String.format;

@Component
@Getter
public class NewArticlePage extends AbstractPage {
    public NewArticlePage(WebDriver webDriver) {
        super(webDriver);
    }

    private static Logger logger = LoggerFactory.getLogger(NewArticlePage.class);

    private final String url = "editor";

    @FindBy(xpath = "//input[@placeholder='Article Title']")
    private WebElement titleField;

    @FindBy(xpath = "//input[@placeholder=\"What's this article about?\"]")
    private WebElement aboutField;

    @FindBy(xpath = "//textarea[@placeholder='Write your article (in markdown)']")
    private WebElement articleField;

    @FindBy(xpath = "//input[@placeholder='Enter Tags']")
    private WebElement tagField;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement publishButton;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/settings']")
    private WebElement settingsLink;

    public void createArticle(Article article) throws ATFException {
        logger.info("User started to create article");
        titleField.sendKeys(article.getTitle());
        logger.info(format("Title Field  is set to {%s}", article.getTitle()));
        aboutField.sendKeys(article.getAbout());
        logger.info(format("About Field is set to {%s}", article.getAbout()));
        articleField.sendKeys(article.getArticle());
        logger.info(format("Article is set to {%s}", article.getArticle()));
        tagField.sendKeys(article.getTag());
        logger.info(format("Tags is set to {%s}", article.getTag()));

        waitUntilElementToContainValue(this.getWebDriver(), titleField, article.getTitle());
        waitUntilElementToContainValue(this.getWebDriver(), aboutField, article.getAbout());
        waitUntilElementToContainValue(this.getWebDriver(), articleField, article.getArticle());
        waitUntilElementToContainValue(this.getWebDriver(), tagField, article.getTag());
    }

    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = NewArticlePage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
