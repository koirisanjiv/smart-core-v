package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;


public class ToastMessage implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void validateToastMessage(WebDriver driver,
	                                 By toastLocator,
	                                 String expectedMessage) {

	    Exception lastException = null;

	    for (int attempt = 1; attempt <= 3; attempt++) {
	        try {
	            WebElement toast = getWait().waitForVisible(driver,toastLocator,5);

	            String actualMessage = toast.getText().trim();

	            if (!actualMessage.contains(expectedMessage)) {
	                throw new AssertionError(
	                        "Expected: " + expectedMessage +
	                        " | Actual: " + actualMessage);
	            }

	            LoggerManager.info("✅ Toast validated: {}", actualMessage);
	            return;

	        } catch (Exception e) {
	            lastException = e;

	            LoggerManager.warn("⚠️ Toast validation failed attempt {}",
	                    attempt);

	            WaitManager.hardWait(1);
	        }
	    }

	    throw new RuntimeException(
	            "❌ Toast validation failed for message: " + expectedMessage,
	            lastException
	    );
	}
}
