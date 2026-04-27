
package com.qaverse.smart.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.qaverse.smart.ActionMethod.MyActions;

public class WaitManager implements MyActions {

	//  Base Wait
	public FluentWait<WebDriver> wait(WebDriver driver, int seconds) {
		return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(250))
				.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
	}

	//  Generic Wait (Flexible)
	public <T> T waitUntil(WebDriver driver, Function<WebDriver, T> condition, int timeout) {
		return wait(driver, timeout).until(condition);
	}

	//  Wait for Element Presence
	public WebElement waitForPresence(WebDriver driver, By locator, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

//  Wait for all Element Presence
	public List<WebElement> waitForPresenceAll(WebDriver driver, By locator, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	//  Wait for Visibility
	public WebElement waitForVisible(WebDriver driver, By locator, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

//  Wait for Visibility
	public List<WebElement> waitForVisibleAll(WebDriver driver, By locator, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	//  Wait for Clickable
	public WebElement waitForClickable(WebDriver driver, By locator, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
	}

	//  Wait for Invisibility
	public boolean waitForInvisibility(WebDriver driver, By locator, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Wait for Alert
	public Alert waitForAlert(WebDriver driver, int timeout) {
		return wait(driver, timeout).until(ExpectedConditions.alertIsPresent());
	}

	//  Wait for Frame and Switch
	public void waitForFrameAndSwitch(WebDriver driver, String frame, int timeout) {
		wait(driver, timeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	//  Wait for Title Contains
	public boolean waitForTitleContains(WebDriver driver, String title, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.titleContains(title));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Wait for Title Exact
	public boolean waitForTitleIs(WebDriver driver, String title, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.titleIs(title));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Wait for URL Contains
	public boolean waitForUrlContains(WebDriver driver, String urlPart, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.urlContains(urlPart));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Wait for Page Load (JS Ready State)
	public void waitForPageLoad(WebDriver driver, int timeout) {
		wait(driver, timeout).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	//  Wait for Element Text
	public boolean waitForText(WebDriver driver, By locator, String text, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Wait for Attribute Value
	public boolean waitForAttribute(WebDriver driver, By locator, String attribute, String value, int timeout) {
		try {
			return wait(driver, timeout).until(ExpectedConditions.attributeToBe(locator, attribute, value));
		} catch (TimeoutException e) {
			return false;
		}
	}

	//  Loader Wait (Generic)
	public void waitForLoaderToDisappear(WebDriver driver, By loaderLocator, int timeout) {
		waitForInvisibility(driver, loaderLocator, timeout);
	}

	//  Hard Wait (Fallback only)
	public static void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Hard wait interrupted", e);
		}
	}
}