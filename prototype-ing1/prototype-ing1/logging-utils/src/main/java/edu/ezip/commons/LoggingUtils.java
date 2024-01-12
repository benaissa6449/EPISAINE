package edu.ezip.commons;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LoggingUtils {

    private static Map<Level, Method> functions = new LinkedHashMap<>();
    private static void initFunctions () throws NoSuchMethodException {
        functions.put(Level.DEBUG, Logger.class.getMethod("debug", String.class));
        functions.put(Level.INFO, Logger.class.getMethod("info", String.class));
        functions.put(Level.WARN, Logger.class.getMethod("warn", String.class));
        functions.put(Level.ERROR, Logger.class.getMethod("error", String.class));
        functions.put(Level.TRACE, Logger.class.getMethod("trace", String.class));
    }

    public static void logDataMultiLine(final Logger logger, Level level, byte [] data) {
        try {
            initFunctions();
            final String [] bylines = new String(data).split("\n");
            for(final String li : bylines) {
                functions.get(level).invoke(logger, li);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
             e.printStackTrace();
        }
    }
}
