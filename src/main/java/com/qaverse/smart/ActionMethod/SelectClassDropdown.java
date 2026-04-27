package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;


public class SelectClassDropdown implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void selectDropdownByVisibleText(WebDriver driver,
	                                        String labelName,
	                                        By locator,
	                                        String expectedValue) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "VALUE_NOT_SELECTED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement dropdownElement = getWait().waitForPresence(driver,locator,5);

	            Select select = new Select(dropdownElement);

	            // ✅ already selected
	            String currentSelected =
	                    select.getFirstSelectedOption().getText().trim();

	            if (currentSelected.equalsIgnoreCase(expectedValue.trim())) {
	            	LoggerManager.info("✅ Dropdown already selected [{}] = {}",
	                        labelName, expectedValue);
	                return;
	            }

	            switch (attempt) {

	                case 1:
	                    select.selectByVisibleText(expectedValue);
	                    break;

	                case 2:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            dropdownElement
	                    );
	                    select.selectByVisibleText(expectedValue);
	                    break;

	                case 3:
	                    dropdownElement.click();
	                    select.selectByVisibleText(expectedValue);
	                    break;

	                case 4:
	                    js.executeScript(
	                            "arguments[0].value = arguments[1];" +
	                            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
	                            dropdownElement,
	                            expectedValue
	                    );
	                    break;

	                case 5:
	                    new Actions(driver)
	                            .moveToElement(dropdownElement)
	                            .click()
	                            .perform();

	                    select.selectByVisibleText(expectedValue);
	                    break;
	            }

	            // ✅ final verification
	            String finalSelected =
	                    select.getFirstSelectedOption().getText().trim();

	            if (finalSelected.equalsIgnoreCase(expectedValue.trim())) {
	            	LoggerManager.info("✅ Dropdown value selected on attempt {} for [{}] | Root Cause Fixed: {}",
	                        attempt, labelName, rootCause);
	                return;
	            }

	            throw new RuntimeException("Dropdown value mismatch after selection");

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (NoSuchElementException e) {
	            rootCause = "OPTION_NOT_FOUND";
	            lastException = e;

	        } catch (ElementNotInteractableException e) {
	            rootCause = "DROPDOWN_NOT_INTERACTABLE";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "DROPDOWN_NOT_VISIBLE_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_DROPDOWN_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Dropdown selection failed attempt {} for [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty("lastDropdownRootCause", rootCause);

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed to select dropdown [" + labelName +
	                    "] value = " + expectedValue +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
