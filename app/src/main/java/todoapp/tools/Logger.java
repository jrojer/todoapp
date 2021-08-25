package todoapp;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

/*
    A class that wraps log4j logger.
*/

public class Logger {
    private static org.apache.log4j.Logger logger = createLogger();

    private static org.apache.log4j.Logger createLogger() {
        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("log.txt");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        // configures the root logger
        org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

        // creates a custom logger and log messages
        return org.apache.log4j.Logger.getLogger(Logger.class);
    }

    public static void info(String str) {
        logger.info(str);
    }

    public static void debug(String str) {
        logger.debug(str);
    }

    public static void warn(String str) {
        logger.warn(str);
    }
}