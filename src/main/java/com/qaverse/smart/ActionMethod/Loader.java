package com.qaverse.smart.ActionMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class Loader implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void waitForLoaderToDisappear(WebDriver driver,
	                                     String labelName,
	                                     By loaderLocator) {

	    Exception lastException = null;
	    String rootCause = "LOADER_NOT_DISAPPEARED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            List<WebElement> loaders = driver.findElements(loaderLocator);

	            // ✅ no loader present
	            if (loaders == null || loaders.isEmpty()) {
	            	LoggerManager.info("✅ No loader present [{}]", labelName);
	                return;
	            }

	            boolean allInvisible = getWait().wait(driver,60).until(driverRef -> {
	                List<WebElement> currentLoaders =
	                        driverRef.findElements(loaderLocator);

	                if (currentLoaders.isEmpty()) {
	                    return true;
	                }

	                for (WebElement loader : currentLoaders) {
	                    try {
	                        if (loader.isDisplayed()) {
	                            return false;
	                        }
	                    } catch (StaleElementReferenceException ignored) {
	                        // stale means DOM refreshed = usually safe
	                    }
	                }
	                return true;
	            });

	            if (allInvisible) {
	            	LoggerManager.info("✅ Loader disappeared [{}]", labelName);
	                return;
	            }

	        } catch (StaleElementReferenceException e) {
	            rootCause = "LOADER_DOM_REFRESHED";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "LOADER_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_LOADER_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Loader wait failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastLoaderRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Genuine loader issue after retries [" +
	                    labelName + "] | Root Cause: " + rootCause,
	            lastException
	    );
	}

}
