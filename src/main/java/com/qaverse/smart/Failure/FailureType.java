package com.qaverse.smart.Failure;


public enum FailureType {

    // Alerts
    ALERT_NOT_FOUND,

    // Elements
    ELEMENT_NOT_FOUND,
    ELEMENT_NOT_CLICKABLE,
    ELEMENT_CLICK_INTERCEPTED,
    STALE_ELEMENT,

    // Wait
    TIMEOUT,

    // Input
    INVALID_INPUT,
    INPUT_FAILED,

    // Assertion
    ASSERTION_FAILED,

    // System
    DRIVER_ERROR,
    SESSION_EXPIRED,
    RUNTIME_ERROR,
    ELEMENT_NOT_INTERACTABLE,
    INVALID_LOCATOR,
    UNKNOWN
}