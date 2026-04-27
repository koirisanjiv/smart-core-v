package com.qaverse.smart.ActionMethod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.Failure.FailureSeverity;
import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Retry.RetryExecutor;
import com.qaverse.smart.Wait.WaitManager;

public class AlertManager implements MyActions  {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void acceptAlert(WebDriver driver) {

	    RetryExecutor.execute(
	            driver,
	            "Accept Alert",
	            FailureSeverity.HIGH,
	            () -> {
	                Alert alert = getWait().waitForAlert(driver, 5);
	                alert.accept();
	                return true;
	            }
	    );

	    LoggerManager.info("Alert accepted successfully");
	}

	public void dismissAlert(WebDriver driver) {
	    try {
	    	Alert alert =  getWait().waitForAlert(driver, 5);
	        String text = "";
	        try {
	            text = alert.getText();
	        } catch (Exception ignored) {}

	        LoggerManager.info("✅ Alert found: {}", text);

	        alert.dismiss();

	        LoggerManager.info("✅ Alert dismissed");

	    } catch (TimeoutException e) {
	        throw new RuntimeException("❌ Alert not present within timeout", e);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected alert failure: " + e.getClass().getSimpleName(), e);
	    }
	}



	public void validateAndAcceptAlert(WebDriver driver,
	                                   String expectedText) {
	    try {
	    	Alert alert =  getWait().waitForAlert(driver, 5);

	        String actualText = alert.getText().trim();

	        if (!actualText.contains(expectedText)) {
	            throw new AssertionError(
	                    "Expected: " + expectedText +
	                    " | Actual: " + actualText);
	        }

	        alert.accept();

	        LoggerManager.info("✅ Alert validated and accepted");

	    } catch (TimeoutException e) {
	        throw new RuntimeException("❌ Alert not present within timeout", e);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected alert failure: " + e.getClass().getSimpleName(), e);
	    }
	}




	public void enterPromptAlert(WebDriver driver,
	                             String value) {
	    try {
	    	Alert alert =  getWait().waitForAlert(driver, 5);

	        alert.sendKeys(value);
	        alert.accept();

	        LoggerManager.info("✅ Prompt alert handled");

	    }catch (TimeoutException e) {
	        throw new RuntimeException("❌ Alert not present within timeout", e);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected alert failure: " + e.getClass().getSimpleName(), e);
	    }
	}


	public boolean isAlertPresent(WebDriver driver, int timeout) {
	    try {
	    	 getWait().waitForAlert(driver, 5);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
}
