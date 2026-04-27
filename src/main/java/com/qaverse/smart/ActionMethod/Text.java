package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Wait.WaitManager;

public class Text implements MyActions {
	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public String getElementText(WebDriver driver, String labelName, By locator) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "UNKNOWN";

	    for (int attempt = 1; attempt <= 4; attempt++) {
	        try {

	            WebElement element = getWait().waitForPresence(driver,locator, 5);

	            String text = null;

	            switch (attempt) {

	                // 1 Normal visible text
	                case 1:
	                    text = element.getText();
	                    if (text != null && !text.trim().isEmpty()) {
	                        rootCause = "VISIBLE_TEXT";
	                        return text.trim();
	                    }
	                    break;

	                // 3 Value attribute (input fields)
	                case 2:
	                    text = element.getAttribute("value");
	                    if (text != null && !text.trim().isEmpty()) {
	                        rootCause = "VALUE_ATTRIBUTE";
	                        return text.trim();
	                    }
	                    break;

	                // 3 Title attribute
	                case 3:
	                    text = element.getAttribute("title");
	                    if (text != null && !text.trim().isEmpty()) {
	                        rootCause = "TITLE_ATTRIBUTE";
	                        return text.trim();
	                    }
	                    break;

	                // 4 JS fallback (hidden text)
	                case 4:
	                    text = (String) js.executeScript(
	                            "return arguments[0].textContent || arguments[0].innerText;",
	                            element
	                    );
	                    if (text != null && !text.trim().isEmpty()) {
	                        rootCause = "JS_TEXT_CONTENT";
	                        return text.trim();
	                    }
	                    break;
	            }

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_ELEMENT";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "ELEMENT_NOT_FOUND_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_EXCEPTION";
	            lastException = e;
	        }

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Unable to fetch text for [" + labelName + "] | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
