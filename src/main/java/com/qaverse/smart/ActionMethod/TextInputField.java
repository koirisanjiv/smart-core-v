package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class TextInputField implements MyActions {
	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void setDataInTextField(WebDriver driver,
	                               String labelName,
	                               By locator,
	                               String value) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "UNKNOWN";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement element = getWait().waitForPresence(driver,locator,5);

	            switch (attempt) {

	                case 1:
	                	getWait().waitForClickable(driver,locator,5);
	                    element.clear();
	                    element.sendKeys(value);
	                    break;

	                case 2:
	                    element.click();
	                    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	                    element.sendKeys(Keys.DELETE);
	                    element.sendKeys(value);
	                    break;

	                case 3:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            element
	                    );
	                    element.click();
	                    element.clear();
	                    element.sendKeys(value);
	                    break;

	                case 4:
	                    js.executeScript(
	                            "arguments[0].value=arguments[1];" +
	                            "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
	                            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
	                            element, value
	                    );
	                    break;

	                case 5:
	                    new Actions(driver)
	                            .moveToElement(element)
	                            .click()
	                            .sendKeys(value)
	                            .perform();
	                    break;
	            }

	            String actualValue = element.getAttribute("value");

	            if (actualValue != null && actualValue.trim().equals(value.trim())) {
	            	LoggerManager.info("✅ Input success on attempt {} for [{}] | Root Cause Fixed: {}",
	                        attempt, labelName, rootCause);
	                return;
	            }

	            throw new RuntimeException("Value mismatch after input");

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (ElementNotInteractableException e) {
	            rootCause = "FIELD_NOT_INTERACTABLE";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "FIELD_NOT_VISIBLE_TIMEOUT";
	            lastException = e;

	        } catch (InvalidElementStateException e) {
	            rootCause = "FIELD_STATE_INVALID";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_INPUT_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Input failed attempt {} for [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty("lastInputRootCause", rootCause);

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed to set value in [" + labelName +
	                    "] after all retries | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
