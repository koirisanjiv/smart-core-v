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

public class RadioButton implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void setRadioButtonState(WebDriver driver,
	                                String labelName,
	                                By locator,
	                                boolean expectedState) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "STATE_MISMATCH";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement radio = getWait().waitForPresence(driver,locator,5);

	            boolean currentState = radio.isSelected();

	            // ✅ already matching expected state
	            if (currentState == expectedState) {
	            	LoggerManager.info("✅ Radio already in expected state [{}] = {}",
	                        labelName, expectedState);
	                return;
	            }

	            // ❌ radio cannot self-unselect
	            if (!expectedState && currentState) {
	                throw new UnsupportedOperationException(
	                        "Radio button cannot be directly unselected. Select another radio in same group."
	                );
	            }

	            switch (attempt) {
	                case 1:
	                	getWait().waitForClickable(driver,locator,5)
	                            .click();
	                    break;

	                case 2:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            radio
	                    );
	                    radio.click();
	                    break;

	                case 3:
	                    new Actions(driver)
	                            .moveToElement(radio)
	                            .pause(Duration.ofMillis(200))
	                            .click()
	                            .perform();
	                    break;

	                case 4:
	                    js.executeScript("arguments[0].click();", radio);
	                    break;

	                case 5:
	                    Point point = radio.getLocation();
	                    new Actions(driver)
	                            .moveByOffset(point.getX(), point.getY())
	                            .click()
	                            .perform();
	                    break;
	            }

	            if (radio.isSelected() == expectedState) {
	            	LoggerManager.info("✅ Radio state set on attempt {} for [{}] | Root Cause Fixed: {}",
	                        attempt, labelName, rootCause);
	                return;
	            }

	            throw new RuntimeException("Radio state mismatch after action");

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (ElementNotInteractableException e) {
	            rootCause = "RADIO_NOT_INTERACTABLE";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "RADIO_NOT_VISIBLE_TIMEOUT";
	            lastException = e;

	        } catch (UnsupportedOperationException e) {
	            rootCause = "DIRECT_UNSELECT_NOT_SUPPORTED";
	            lastException = e;
	            break;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_RADIO_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Radio action failed attempt {} for [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty("lastRadioRootCause", rootCause);

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed to set radio [" + labelName +
	                    "] to state = " + expectedState +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
