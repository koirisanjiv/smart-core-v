package com.qaverse.smart.ActionMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;

public class DragAndDrop implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}


	public void dragAndDrop(WebDriver driver,
	                        String labelName,
	                        By sourceLocator,
	                        By targetLocator) {

	    Exception lastException = null;

	    for (int attempt = 1; attempt <= 3; attempt++) {
	        try {
	            WebElement source = getWait().waitForVisible(driver,sourceLocator,5);

	            WebElement target = getWait().waitForVisible(driver,targetLocator,5);

	            new Actions(driver)
	                    .clickAndHold(source)
	                    .pause(Duration.ofMillis(300))
	                    .moveToElement(target)
	                    .pause(Duration.ofMillis(300))
	                    .release()
	                    .build()
	                    .perform();

	            LoggerManager.info("✅ Drag and drop success: {}", labelName);
	            return;

	        } catch (Exception e) {
	            lastException = e;

	            LoggerManager.warn("⚠️ Drag drop failed attempt {} for {}",
	                    attempt, labelName);

	            WaitManager.hardWait(1);
	        }
	    }

	    throw new RuntimeException(
	            "❌ Genuine drag and drop failed: " + labelName,
	            lastException
	    );
	}

	public void moveSlider(WebDriver driver,
	                       String labelName,
	                       By sliderLocator,
	                       int targetPercent) {


	    try {
	        WebElement slider = getWait().waitForVisible(driver,sliderLocator,5);

	        int width = slider.getSize().getWidth();

	        int xOffset = (width * targetPercent / 100) - (width / 2);

	        new Actions(driver)
	                .clickAndHold(slider)
	                .moveByOffset(xOffset, 0)
	                .release()
	                .perform();

	        LoggerManager.info("✅ Slider moved: {} -> {}%",
	                labelName, targetPercent);

	    } catch (Exception e) {
	        throw new RuntimeException(
	                "❌ Slider movement failed: " + labelName,
	                e
	        );
	    }
	}
}
