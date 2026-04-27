package com.qaverse.smart.ActionMethod;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;


public class TabSwitch implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void switchToNewTab(WebDriver driver) {

	    String currentWindow = driver.getWindowHandle();
	    Set<String> allWindows = driver.getWindowHandles();

	    for (String window : allWindows) {
	        if (!window.equals(currentWindow)) {
	            driver.switchTo().window(window);

	            LoggerManager.info("✅ Switched to new tab");
	            return;
	        }
	    }

	    throw new RuntimeException("❌ No new tab found");
	}

	public void closeCurrentTabAndSwitchParent(WebDriver driver) {

	    String parent = driver.getWindowHandles().iterator().next();

	    driver.close();
	    driver.switchTo().window(parent);

	    LoggerManager.info("✅ Closed child tab and switched parent");
	}

	public void switchTabByTitle(WebDriver driver,
	                             String expectedTitle) {

	    for (String handle : driver.getWindowHandles()) {
	        driver.switchTo().window(handle);

	        if (driver.getTitle().trim()
	                .contains(expectedTitle)) {

	        	LoggerManager.info("✅ Switched tab by title: {}",
	                    expectedTitle);
	            return;
	        }
	    }

	    throw new RuntimeException(
	            "❌ Tab not found with title: " + expectedTitle);
	}
}
