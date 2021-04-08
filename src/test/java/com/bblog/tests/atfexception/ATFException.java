package com.bblog.tests.atfexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATFException extends Exception {

    private static Logger logger = LoggerFactory.getLogger(ATFException.class);

    public ATFException(String s) {
        super(s);
        logger.error(s);
    }
}
