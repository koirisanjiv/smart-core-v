package com.qaverse.smart.Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {

    private static final Logger reportLogger =
            LogManager.getLogger("reportLogger");

    private static final Logger systemLogger =
            LogManager.getLogger("systemLogger");

    // =========================
    // INFO
    // =========================
    public static void info(String message) {
        reportLogger.info(message);
    }

    public static void info(String message, Object... params) {
        reportLogger.info(message, params);
    }

    // =========================
    // WARN
    // =========================
    public static void warn(String message) {
        reportLogger.warn(message);
    }

    public static void warn(String message, Object... params) {
        reportLogger.warn(message, params);
    }

    public static void warn(String message, Throwable t) {
        reportLogger.warn(message, t);
    }

    // =========================
    // ERROR
    // =========================
    public static void error(String message) {
        reportLogger.error(message);
    }

    public static void error(String message, Object... params) {
        reportLogger.error(message, params);
    }

    public static void error(String message, Throwable t) {
        reportLogger.error(message, t);
    }

    // =========================
    // DEBUG
    // =========================
    public static void debug(String message) {
        reportLogger.debug(message);
    }

    public static void debug(String message, Object... params) {
        reportLogger.debug(message, params);
    }

    // =========================
    // TRACE (very detailed)
    // =========================
    public static void trace(String message) {
        reportLogger.trace(message);
    }

    public static void trace(String message, Object... params) {
        reportLogger.trace(message, params);
    }

    // =========================
    // FATAL
    // =========================
    public static void fatal(String message) {
        reportLogger.fatal(message);
    }

    public static void fatal(String message, Throwable t) {
        reportLogger.fatal(message, t);
    }

    // =========================
    // SYSTEM LOGGER (optional)
    // =========================
    public static void system(String message, Object... params) {
        systemLogger.info(message, params);
    }
}