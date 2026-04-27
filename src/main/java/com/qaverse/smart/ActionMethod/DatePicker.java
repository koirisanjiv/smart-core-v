package com.qaverse.smart.ActionMethod;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.ThreadLocal.ThreadLocalProperties;
import com.qaverse.smart.Wait.WaitManager;

public class DatePicker implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void selectDateFromCalendar(WebDriver driver,
	                                   String labelName,
	                                   By calendarInputLocator,
	                                   By monthDropdownLocator,
	                                   By yearDropdownLocator,
	                                   By nextArrowLocator,
	                                   By previousArrowLocator,
	                                   By dayGridLocator,
	                                   LocalDate expectedDate) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "DATE_NOT_SELECTED";

	    String expectedDay = String.valueOf(expectedDate.getDayOfMonth());
	    String expectedMonthText = expectedDate.getMonth()
	            .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	    String expectedMonthShort = expectedDate.getMonth()
	            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	    String expectedMonthNumber = String.valueOf(expectedDate.getMonthValue());
	    String expectedMonthNumber2 = String.format("%02d", expectedDate.getMonthValue());
	    String expectedYear = String.valueOf(expectedDate.getYear());

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            // ✅ already selected skip
	            WebElement input = getWait().waitForVisible(driver,calendarInputLocator,5);

	            String existingValue = input.getAttribute("value");
	            if (existingValue != null &&
	                    existingValue.contains(expectedYear) &&
	                    existingValue.contains(expectedDay)) {
	            	LoggerManager.info("✅ Date already selected [{}] = {}",
	                        labelName, expectedDate);
	                return;
	            }

	            // open picker
	            input.click();

	            // ===== year select =====
	            Select yearSelect = new Select(getWait().waitForVisible(driver,yearDropdownLocator,5));

	            if (!yearSelect.getFirstSelectedOption().getText()
	                    .trim().equals(expectedYear)) {
	                yearSelect.selectByVisibleText(expectedYear);
	            }

	            // ===== month select =====
	            Select monthSelect = new Select(getWait().waitForVisible(driver,monthDropdownLocator,5))
;
	            String currentMonth =
	                    monthSelect.getFirstSelectedOption().getText().trim();

	            if (!isMonthMatched(currentMonth,
	                    expectedMonthText,
	                    expectedMonthShort,
	                    expectedMonthNumber,
	                    expectedMonthNumber2)) {

	                selectMonthFlexibly(monthSelect,
	                        expectedMonthText,
	                        expectedMonthShort,
	                        expectedMonthNumber,
	                        expectedMonthNumber2);
	            }

	            // ===== select day from grid =====
	            List<WebElement> days = getWait().waitForVisibleAll(driver,dayGridLocator,5);

	            boolean daySelected = false;

	            for (WebElement day : days) {
	                String text = day.getText().trim();

	                if (text.equals(expectedDay)) {
	                    js.executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            day
	                    );
	                    day.click();
	                    daySelected = true;
	                    break;
	                }
	            }

	            if (!daySelected) {
	                throw new NoSuchElementException(
	                        "Day not found in grid: " + expectedDay);
	            }

	            LoggerManager.info("✅ Date selected [{}] -> {}",
	                    labelName, expectedDate);

	            return;

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_CALENDAR_DOM";
	            lastException = e;

	        } catch (NoSuchElementException e) {
	            rootCause = "DATE_GRID_OR_DROPDOWN_OPTION_NOT_FOUND";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "DATE_CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "DATE_PICKER_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_DATE_PICKER_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Date picker failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastDatePickerRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed date picker [" + labelName +
	                    "] = " + expectedDate +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}




	private boolean isMonthMatched(String current,
	                               String full,
	                               String shortName,
	                               String num,
	                               String num2) {
	    return current.equalsIgnoreCase(full)
	            || current.equalsIgnoreCase(shortName)
	            || current.equals(num)
	            || current.equals(num2);
	}

	private void selectMonthFlexibly(Select monthSelect,
	                                 String full,
	                                 String shortName,
	                                 String num,
	                                 String num2) {
	    List<WebElement> options = monthSelect.getOptions();

	    for (WebElement option : options) {
	        String text = option.getText().trim();

	        if (text.equalsIgnoreCase(full)
	                || text.equalsIgnoreCase(shortName)
	                || text.equals(num)
	                || text.equals(num2)) {
	            monthSelect.selectByVisibleText(text);
	            return;
	        }
	    }

	    throw new NoSuchElementException("Matching month option not found");
	}


	public void selectMonthYearFromPicker(WebDriver driver,
	                                      String labelName,
	                                      By inputLocator,
	                                      By monthDropdownLocator,
	                                      By yearDropdownLocator,
	                                      By nextArrowLocator,
	                                      By previousArrowLocator,
	                                      YearMonth expectedMonthYear) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Exception lastException = null;
	    String rootCause = "MONTH_YEAR_NOT_SELECTED";

	    String expectedMonthFull = expectedMonthYear.getMonth()
	            .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	    String expectedMonthShort = expectedMonthYear.getMonth()
	            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	    String expectedYear = String.valueOf(expectedMonthYear.getYear());

	    for (int attempt = 1; attempt <= 5; attempt++) {
	        try {
	            WebElement input = getWait().waitForVisible(driver,inputLocator,5);

	            // ✅ already selected check
	            String existingValue = input.getAttribute("value");
	            if (existingValue != null &&
	                    existingValue.toLowerCase().contains(expectedYear.toLowerCase()) &&
	                    (existingValue.toLowerCase().contains(expectedMonthFull.toLowerCase())
	                     || existingValue.toLowerCase().contains(expectedMonthShort.toLowerCase()))) {

	            	LoggerManager.info("✅ Month-Year already selected [{}] -> {}",
	                        labelName, expectedMonthYear);
	                return;
	            }

	            // open picker
	            input.click();

	            // ===== YEAR =====
	            Select yearSelect = new Select(getWait().waitForVisible(driver,yearDropdownLocator,5));

	            String currentYear =
	                    yearSelect.getFirstSelectedOption().getText().trim();

	            if (!currentYear.equals(expectedYear)) {
	                yearSelect.selectByVisibleText(expectedYear);
	            }

	            // ===== MONTH =====
	            Select monthSelect = new Select(getWait().waitForVisible(driver,monthDropdownLocator,5));

	            String currentMonth =
	                    monthSelect.getFirstSelectedOption().getText().trim();

	            if (!isMonthMatched(currentMonth,
	                    expectedMonthFull,
	                    expectedMonthShort)) {

	                selectMonthFlexibly(monthSelect,
	                        expectedMonthFull,
	                        expectedMonthShort);
	            }

	            LoggerManager.info("✅ Month-Year selected [{}] -> {}",
	                    labelName, expectedMonthYear);

	            return;

	        } catch (StaleElementReferenceException e) {
	            rootCause = "STALE_MONTH_YEAR_PICKER_DOM";
	            lastException = e;

	        } catch (NoSuchElementException e) {
	            rootCause = "MONTH_OR_YEAR_OPTION_NOT_FOUND";
	            lastException = e;

	        } catch (ElementClickInterceptedException e) {
	            rootCause = "MONTH_YEAR_CLICK_INTERCEPTED";
	            lastException = e;

	        } catch (TimeoutException e) {
	            rootCause = "MONTH_YEAR_PICKER_TIMEOUT";
	            lastException = e;

	        } catch (Exception e) {
	            rootCause = "UNKNOWN_MONTH_YEAR_PICKER_FAILURE";
	            lastException = e;
	        }

	        LoggerManager.warn("⚠️ Month-Year picker failed attempt {} [{}] → {}",
	                attempt, labelName, rootCause);

	        ThreadLocalProperties.setProperty(
	                "lastMonthYearPickerRootCause",
	                rootCause
	        );

	        WaitManager.hardWait(1);
	    }

	    throw new RuntimeException(
	            "❌ Failed month-year picker [" + labelName +
	                    "] = " + expectedMonthYear +
	                    " | Root Cause: " + rootCause,
	            lastException
	    );
	}





	private boolean isMonthMatched(String current,
	                               String full,
	                               String shortName) {
	    return current.equalsIgnoreCase(full)
	            || current.equalsIgnoreCase(shortName);
	}

	private void selectMonthFlexibly(Select monthSelect,
	                                 String full,
	                                 String shortName) {

	    for (WebElement option : monthSelect.getOptions()) {
	        String text = option.getText().trim();

	        if (text.equalsIgnoreCase(full)
	                || text.equalsIgnoreCase(shortName)) {
	            monthSelect.selectByVisibleText(text);
	            return;
	        }
	    }

	    throw new NoSuchElementException(
	            "Matching month option not found");
	}
}
