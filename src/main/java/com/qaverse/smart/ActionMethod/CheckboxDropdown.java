package com.qaverse.smart.ActionMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class CheckboxDropdown implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void selectCheckboxByClickSearchMultipleSelectWithoutAllOpetion(WebDriver driver,
	                                              String labelName,
	                                              By dropdownLocator,
	                                              By searchInputLocator,
	                                              By optionContainerLocator,
	                                              List<String> expectedValues) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "MULTI_OPTION_NOT_SELECTED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement dropdown = getWait().waitForClickable(driver, dropdownLocator, 5);

	            // ✅ open dropdown
	            dropdown.click();

	            for (String expectedValue : expectedValues) {

	                WebElement searchInput = getWait().waitForVisible(driver,searchInputLocator,5);

	                searchInput.clear();
	                searchInput.sendKeys(expectedValue);

	                WaitManager.hardWait(1);

	                List<WebElement> options = getWait().waitForVisibleAll(driver,optionContainerLocator,5);

	                boolean found = false;

	                for (WebElement option : options) {
	                    String optionText = option.getText().trim();

	                    if (optionText.equalsIgnoreCase(expectedValue.trim())) {

	                        WebElement checkbox =
	                                option.findElement(By.xpath(".//input[@type='checkbox']"));

	                        boolean alreadySelected = checkbox.isSelected();

	                        if (!alreadySelected) {
	                            js.executeScript(
	                                    "arguments[0].scrollIntoView({block:'center'});",
	                                    option
	                            );
	                            option.click();
	                            LoggerManager.info("✅ Selected [{}] -> {}", labelName, expectedValue);
	                        } else {
	                        	LoggerManager.info("✅ Already selected [{}] -> {}", labelName, expectedValue);
	                        }

	                        found = true;
	                        break;
	                    }
	                }

	                if (!found) {
	                    throw new NoSuchElementException(
	                            "Option not found: " + expectedValue);
	                }
	            }

	            // optional close dropdown
	            dropdown.click();

	            LoggerManager.info("✅ Multi-search dropdown selection completed [{}]",
	                    labelName);

	            return;

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (NoSuchElementException e) {
	            rootCause = "OPTION_NOT_FOUND";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "MULTI_DROPDOWN_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_MULTI_DROPDOWN_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Multi dropdown failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastMultiDropdownRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed multi-search dropdown [" + labelName +
	                    "] values = " + expectedValues +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}

	public void selectCheckboxByClickSearchMultipleSelectWithAllOpetion(WebDriver driver,
	                                            String labelName,
	                                            By dropdownLocator,
	                                            By searchInputLocator,
	                                            By optionContainerLocator,
	                                            String allOptionText,
	                                            List<String> expectedValues) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "MULTI_SYNC_FAILED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement dropdown = getWait().waitForClickable(driver, dropdownLocator, 5);

	            // ✅ STEP 1: open dropdown
	            dropdown.click();

	            // ===== STEP 2: use ALL to clear existing =====
	            WebElement searchInput = getWait().waitForVisible(driver, searchInputLocator, 5);

	            searchInput.clear();
	            searchInput.sendKeys(allOptionText);

	            List<WebElement> options = getWait().waitForVisibleAll(driver, optionContainerLocator, 5);

	            boolean allFound = false;

	            for (WebElement option : options) {
	                String text = option.getText().trim();

	                if (text.equalsIgnoreCase(allOptionText.trim())) {
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            option
	                    );

	                    // click ALL once = select all OR unselect all
	                    option.click();

	                    // click again = guaranteed clear all
	                    option.click();

	                    LoggerManager.info("✅ All previous selections cleared using ALL");
	                    allFound = true;
	                    break;
	                }
	            }

	            if (!allFound) {
	                throw new NoSuchElementException(
	                        "'All' option not found: " + allOptionText);
	            }

	            // ===== STEP 3: now select fresh expected values =====
	            for (String expectedValue : expectedValues) {

	                searchInput = getWait().waitForVisible(driver,searchInputLocator,5);

	                searchInput.clear();
	                searchInput.sendKeys(expectedValue);

	                WaitManager.hardWait(1);

	                options = getWait().waitForVisibleAll(driver,optionContainerLocator,5);

	                boolean matched = false;

	                for (WebElement option : options) {
	                    String optionText = option.getText().trim();

	                    if (optionText.equalsIgnoreCase(expectedValue.trim())) {
	                        js.executeScript(
	                                "arguments[0].scrollIntoView({block:'center'});",
	                                option
	                        );
	                        option.click();

	                        LoggerManager.info("✅ Selected [{}] -> {}",
	                                labelName, expectedValue);

	                        matched = true;
	                        break;
	                    }
	                }

	                if (!matched) {
	                    throw new NoSuchElementException(
	                            "Expected option not found: " + expectedValue);
	                }
	            }

	            // ✅ close dropdown
	            dropdown.click();

	            LoggerManager.info("✅ Multi-select sync completed [{}]",
	                    labelName);

	            return;

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_DOM_REFRESH";
	            lastException = e;

	        } catch (NoSuchElementException e) {
	            rootCause = "ALL_OR_OPTION_NOT_FOUND";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "MULTI_SYNC_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_MULTI_SYNC_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Multi-sync failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastMultiSyncRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed multi-select sync [" + labelName +
	                    "] values = " + expectedValues +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}
}
