package com.bblog.tests.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Article {
    private String title;
    private String about;
    private String article;
    private String tag;
}
