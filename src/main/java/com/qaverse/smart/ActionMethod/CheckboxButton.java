package com.qaverse.smart.ActionMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class CheckboxButton  implements MyActions {
	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void setCheckboxState(WebDriver driver,
	                             String labelName,
	                             By locator,
	                             boolean expectedState) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "STATE_MISMATCH";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement checkbox = getWait().waitForPresence(driver, locator, 5);

	            boolean currentState = checkbox.isSelected();

	            // ✅ already in desired state
	            if (currentState == expectedState) {
	            	LoggerManager.info("✅ Checkbox already in expected state [{}] = {}",
	                        labelName, expectedState);
	                return;
	            }

	            switch (attempt) {
	                case 1:
	                	getWait().waitForClickable(driver,locator,5).click();
	                    break;

	                case 2:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            checkbox
	                    );
	                    checkbox.click();
	                    break;

	                case 3:
	                    new Actions(driver)
	                            .moveToElement(checkbox)
	                            .pause(Duration.ofMillis(200))
	                            .click()
	                            .perform();
	                    break;

	                case 4:
	                    js.executeScript("arguments[0].click();", checkbox);
	                    break;

	                case 5:
	                    Point point = checkbox.getLocation();
	                    new Actions(driver)
	                            .moveByOffset(point.getX(), point.getY())
	                            .click()
	                            .perform();
	                    break;
	            }

	            // ✅ final verification
	            if (checkbox.isSelected() == expectedState) {
	            	LoggerManager.info("✅ Checkbox state set on attempt {} for [{}] | Root Cause Fixed: {}",
	                        attempt, labelName, rootCause);
	                return;
	            }

	            throw new RuntimeException("Checkbox state mismatch after action");

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (ElementNotInteractableException e) {
	            rootCause = "CHECKBOX_NOT_INTERACTABLE";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "CHECKBOX_NOT_VISIBLE_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_CHECKBOX_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Checkbox action failed attempt {} for [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty("lastCheckboxRootCause", rootCause);

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed to set checkbox [" + labelName +
	                    "] to state = " + expectedState +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
