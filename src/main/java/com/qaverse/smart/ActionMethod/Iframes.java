package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;

public class Iframes implements MyActions {

private WaitManager getWait() {
    return ActionFactory.getAction(WaitManager.class);
}


	private final WebDriver driver;

	public Iframes(WebDriver driver) {
		this.driver = driver;
	}

	private void switchToFrame(String... frameNames) {
		String framename = null;
		try {
			driver.switchTo().defaultContent();
			for (String frame : frameNames) {
				framename = frame;
				getWait().waitForFrameAndSwitch(driver,frame, 10);
			}
		} catch (NoSuchFrameException e) {
			LoggerManager.warn("No such frame found: " + framename + " " + e);
		} catch (Exception e) {
			LoggerManager.warn("Error switching to iframe: " + framename + " " + e);
		}
	}

	public void switchToDivFrame() {
		switchToFrame("divframe");
	}
}
