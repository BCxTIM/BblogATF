package com.bblog.tests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.bblog.tests.assertions.AssertATF.assertThat;
import static org.hamcrest.core.Is.is;

@Component
@Getter
public class ProfilePage extends AbstractPage {
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@class='app-article-preview' and contains(text(), 'No articles are here... yet.')]")
    private WebElement noArticlesLabel;

    @FindAll(@FindBy(xpath = "//div[@class='article-preview']"))
    private List<WebElement> listArticles;

    @FindAll(@FindBy(xpath = "//h1"))
    private List<WebElement> articleTitles;

    @FindBy(xpath = "//h4")
    private WebElement profileName;

    @FindAll(@FindBy(xpath = "//a[@class='preview-link']/p"))
    private List<WebElement> articlesAbout;

    @FindBy(xpath = "//a[@class='nav-link' and @routerlink='/settings']")
    private WebElement settingsLink;

    private final String url = "profile/" + System.getProperty("username");

    public WebElement getLastArticleTitle() {
        Optional<WebElement> first = articleTitles.stream().findFirst();
        assertThat("Article title is present", first.isPresent(), is(true));
        return first.get();
    }

    public WebElement getLastArticleAbout() {
        Optional<WebElement> first = articlesAbout.stream().findFirst();
        assertThat("Article about description is present", first.isPresent(), is(true));
        return first.get();
    }

    @Override
    public Object getField(String fieldName) {
        Field field = null;
        try {
            field = ProfilePage.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
