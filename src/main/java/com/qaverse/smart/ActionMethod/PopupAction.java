package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;

public class PopupAction implements MyActions  {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}


	public void handlePopupAction(WebDriver driver,
	                              String labelName,
	                              By popupLocator,
	                              By actionButtonLocator) {

	    Exception lastException = null;

	    for (int attempt = 1; attempt <= 3; attempt++) {
	        try {
	            WebElement popup = getWait().waitForVisible(driver,popupLocator,5);

	            if (!popup.isDisplayed()) {
	                throw new RuntimeException("Popup not visible");
	            }

	            WebElement actionButton =
	                    popup.findElement(actionButtonLocator);

	            actionButton.click();

	            LoggerManager.info("✅ Popup action success: {}", labelName);
	            return;

	        } catch (Exception e) {
	            lastException = e;

	            LoggerManager.warn("⚠️ Popup action failed attempt {} for {}",
	                    attempt, labelName);

	            WaitManager.hardWait(1);
	        }
	    }

	    throw new RuntimeException(
	            "❌ Genuine popup action failed: " + labelName,
	            lastException
	    );
	}

	public void closePopup(WebDriver driver,
	                       String labelName,
	                       By popupLocator,
	                       By closeButtonLocator) {

	    handlePopupAction(
	            driver,
	            labelName,
	            popupLocator,
	            closeButtonLocator
	    );
	}
}
