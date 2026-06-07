package com.qaverse.smart.core.failure;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

public final class FailureClassifier {

    private FailureClassifier() {
    }

    public static FailureType classify(
            Throwable throwable) {

        if (throwable instanceof StaleElementReferenceException) {
            return FailureType.STALE_ELEMENT;
        }

        if (throwable instanceof NoSuchElementException) {
            return FailureType.ELEMENT_NOT_FOUND;
        }

        if (throwable instanceof ElementNotInteractableException) {
            return FailureType.ELEMENT_NOT_INTERACTABLE;
        }

        if (throwable instanceof ElementClickInterceptedException) {
            return FailureType.CLICK_INTERCEPTED;
        }

        if (throwable instanceof TimeoutException) {
            return FailureType.TIMEOUT;
        }

        return FailureType.UNKNOWN;
    }
}