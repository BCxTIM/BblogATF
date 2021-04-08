package com.bblog.tests.config;

import com.bblog.tests.model.Article;
import com.bblog.tests.model.User;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return ENGLISH;
    }


    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(User.class, (TableEntryTransformer<User>) entry -> new User(entry.get("username"), entry.get("email"), entry.get("password"))));
        registry.defineDataTableType(new DataTableType(Article.class, (TableEntryTransformer<Article>) entry -> new Article(entry.get("title"), entry.get("about"), entry.get("article"), entry.get("tag"))));

    }
}
