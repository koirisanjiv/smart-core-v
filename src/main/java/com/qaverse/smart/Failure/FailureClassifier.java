package com.qaverse.smart.Failure;


import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import com.qaverse.smart.Exception.ExceptionManager;

public class FailureClassifier {

    public static FailureType classify(Throwable e) {

        if (e instanceof ExceptionManager) {
            return ((ExceptionManager) e).getType();
        }

        if (e instanceof NoSuchElementException) {
			return FailureType.ELEMENT_NOT_FOUND;
		}
        if (e instanceof ElementNotInteractableException) {
			return FailureType.ELEMENT_NOT_INTERACTABLE;
		}
        if (e instanceof StaleElementReferenceException) {
			return FailureType.STALE_ELEMENT;
		}
        if (e instanceof TimeoutException) {
			return FailureType.TIMEOUT;
		}

        return FailureType.UNKNOWN;
    }
}