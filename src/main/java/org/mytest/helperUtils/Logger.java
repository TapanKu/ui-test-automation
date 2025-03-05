package org.mytest.helperUtils;


import org.slf4j.LoggerFactory;

public class Logger {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}