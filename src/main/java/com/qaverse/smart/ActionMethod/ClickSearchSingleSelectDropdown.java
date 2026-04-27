package com.qaverse.smart.ActionMethod;

import java.time.Duration;
import java.util.List;

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

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class ClickSearchSingleSelectDropdown implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}


	public void selectSingleSearchDropdown(WebDriver driver,
	                                       String labelName,
	                                       By dropdownLocator,
	                                       By searchInputLocator,
	                                       By optionListLocator,
	                                       String expectedValue) {

	     JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "OPTION_NOT_SELECTED";

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            // ✅ STEP 1: first check current selected text BEFORE CLICK
	            WebElement dropdown = getWait().waitForVisible(driver,dropdownLocator,5);

	            String currentSelected = dropdown.getText().trim();

	            if (currentSelected.equalsIgnoreCase(expectedValue.trim())) {
	            	LoggerManager.info("✅ [{}] already selected = {}",
	                        labelName, expectedValue);
	                return;
	            }

	            // ===== STEP 2: open dropdown only if needed =====
	            switch (attempt) {
	                case 1:
	                    dropdown.click();
	                    break;

	                case 2:
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            dropdown
	                    );
	                    dropdown.click();
	                    break;

	                case 3:
	                    new Actions(driver)
	                            .moveToElement(dropdown)
	                            .pause(Duration.ofMillis(200))
	                            .click()
	                            .perform();
	                    break;

	                case 4:
	                    js.executeScript("arguments[0].click();", dropdown);
	                    break;

	                case 5:
	                    Point point = dropdown.getLocation();
	                    new Actions(driver)
	                            .moveByOffset(point.getX(), point.getY())
	                            .click()
	                            .perform();
	                    break;
	            }

	            // ===== STEP 3: search =====
	            WebElement searchInput = getWait().waitForVisible(driver,searchInputLocator,5);

	            searchInput.clear();
	            searchInput.sendKeys(expectedValue);

	            // ===== STEP 4: select matched option =====
	            List<WebElement> options = getWait().waitForVisibleAll(driver,optionListLocator,5);

	            boolean selected = false;

	            for (WebElement option : options) {
	                String optionText = option.getText().trim();

	                if (optionText.equalsIgnoreCase(expectedValue.trim())) {
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            option
	                    );
	                    option.click();
	                    selected = true;
	                    break;
	                }
	            }

	            if (!selected) {
	                throw new NoSuchElementException(
	                        "Option not found: " + expectedValue);
	            }

	            // ✅ STEP 5: final verification
	            String finalSelected = getWait().waitForVisible(driver,dropdownLocator,5)
	                    .getText().trim();

	            if (finalSelected.equalsIgnoreCase(expectedValue.trim())) {
	            	LoggerManager.info("✅ Search dropdown selected on attempt {} for [{}]",
	                        attempt, labelName);
	                return;
	            }

	            throw new RuntimeException("Final selected value mismatch");

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
	            rootCause = "DROPDOWN_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_SEARCH_DROPDOWN_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Dropdown failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastSearchDropdownRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed searchable dropdown [" + labelName +
	                    "] = " + expectedValue +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}
	}
