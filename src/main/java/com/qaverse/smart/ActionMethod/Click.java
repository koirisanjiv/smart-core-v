package com.qaverse.smart.ActionMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;


public class Click implements MyActions {
	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void clickOnElement(WebDriver driver,
	                           String labelName,
	                           By locator) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "UNKNOWN";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement element = getWait().waitForPresence(driver,locator,5);

	            switch (attempt) {

	                case 1:
	                	getWait().waitForClickable(driver,locator,5).click();
	                    break;

	                case 2:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            element
	                    );
	                    element.click();
	                    break;

	                case 3:
	                    new Actions(driver)
	                            .moveToElement(element)
	                            .pause(Duration.ofMillis(200))
	                            .click()
	                            .perform();
	                    break;

	                case 4:
	                    js.executeScript("arguments[0].click();", element);
	                    break;

	                case 5:
	                    Point point = element.getLocation();
	                    new Actions(driver)
	                            .moveByOffset(point.getX(), point.getY())
	                            .click()
	                            .perform();
	                    break;
	            }

	            LoggerManager.info("✅ Click success on attempt {} for [{}] | Root Cause Fixed: {}",
	                    attempt, labelName, rootCause);

	            return;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "CLICK_INTERCEPTED_OVERLAY";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);

	        } catch (TimeoutException e) {
	            rootCause = "ELEMENT_NOT_CLICKABLE_TIMEOUT";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);

	        } catch (MoveTargetOutOfBoundsException e) {
	            rootCause = "ELEMENT_OUTSIDE_VIEWPORT";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);

	        } catch (NoSuchElementException e) {
	            rootCause = "LOCATOR_NOT_FOUND";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_GENUINE_FAILURE";
	            lastException = e;
	            LoggerManager.warn("⚠️ Attempt {} failed [{}] → {}",
	                    attempt, labelName, rootCause);
	        }

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Click failed after all retries for [" + labelName +
	                    "] | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
