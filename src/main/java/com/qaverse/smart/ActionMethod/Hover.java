package com.qaverse.smart.ActionMethod;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class Hover implements MyActions  {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void hoverMenuLevels(WebDriver driver,
	                            String labelName,
	                            ArrayList<By> menuLocators) {

	    if (menuLocators == null || menuLocators.isEmpty()) {
	        throw new IllegalArgumentException(
	                "❌ menuLocators cannot be null or empty");
	    }

	    if (menuLocators.size() > 3) {
	        throw new IllegalArgumentException(
	                "❌ Supports max 3 levels only");
	    }

	    Actions actions = new Actions(driver);

	    Exception lastException = null;
	    String rootCause = "HOVER_FAILED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            actions = new Actions(driver);

	            for (int level = 0; level < menuLocators.size(); level++) {
	                By locator = menuLocators.get(level);

	                WebElement menu = getWait().waitForVisible(driver,locator,5);

	                actions.moveToElement(menu)
	                        .pause(Duration.ofMillis(500));

	                LoggerManager.info("➡️ Hover level {} success [{}]",
	                        level + 1, labelName);
	            }

	            actions.perform();

	            LoggerManager.info("✅ Hover completed [{}] with {} level(s)",
	                    labelName, menuLocators.size());

	            return;

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_HOVER_MENU_DOM";
	            lastException = e;

	        } catch (MoveTargetOutOfBoundsException e) {
	            rootCause = "HOVER_TARGET_OUT_OF_VIEW";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "HOVER_MENU_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_HOVER_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Hover failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastHoverRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Genuine hover failure after retries [" +
	                    labelName + "] | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
