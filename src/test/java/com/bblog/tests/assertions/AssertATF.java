package com.bblog.tests.assertions;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertATF {
    private static Logger logger = LoggerFactory.getLogger(AssertATF.class);

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        logger.info(reason);
        MatcherAssert.assertThat(reason, actual, matcher);
    }
}
