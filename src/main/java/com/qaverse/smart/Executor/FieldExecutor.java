package com.qaverse.smart.Executor;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Failure.FailureSeverity;
import com.qaverse.smart.FieldActionHandler.FieldActionHandler;
import com.qaverse.smart.FieldActionHandler.FieldActionRegistry;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;
import com.qaverse.smart.Retry.RetryExecutor;

public class FieldExecutor {

//	public void execute(WebDriver driver, PageActions2 actions, FieldType type, PL_LocatorContractForFields locator,
//			String value, String stepName) {
//
//		// ✅ Safety checks
//		if (driver == null || actions == null) {
//			throw new IllegalArgumentException("Driver or Actions cannot be null");
//		}
//
//		if (locator == null) {
//			throw new IllegalArgumentException("Locator cannot be null");
//		}
//
//		FieldActionHandler handler = FieldActionRegistry.getHandler(type);
//
//		if (handler == null) {
//			throw new RuntimeException("No handler found for type: " + type);
//		}
//
//		RetryExecutor.execute(driver, stepName, FailureSeverity.MEDIUM, // can be dynamic later
//				() -> {
//					handler.handle(driver, locator, value, actions);
//					return null;
//				});
//	}
//
//	// ================= Convenience Methods =================
//
//	public void input(WebDriver driver, PageActions2 actions, PL_LocatorContractForFields locator, String value,
//			String stepName) {
//
//		execute(driver, actions, FieldType.INPUT, locator, value, stepName);
//	}
//
//	public void click(WebDriver driver, PageActions2 actions, PL_LocatorContractForFields locator, String stepName) {
//
//		execute(driver, actions, FieldType.BUTTON, locator, null, stepName);
//	}
//
//	public void checkbox(WebDriver driver, PageActions2 actions, PL_LocatorContractForFields locator, String value,
//			String stepName) {
//
//		execute(driver, actions, FieldType.CHECKBOX, locator, value, stepName);
//	}
//
//	public void dropdown(WebDriver driver, PageActions2 actions, PL_LocatorContractForFields locator, String value,
//			String stepName) {
//
//		execute(driver, actions, FieldType.DROPDOWN, locator, value, stepName);
//	}



	    public void execute(WebDriver driver,
	                        PageActions actions,
	                        FieldType type,
	                        PL_LocatorContractForFields locator,
	                        String value,
	                        String stepName) {

	        FieldActionHandler handler = FieldActionRegistry.getHandler(type);

	        RetryExecutor.execute(
	                driver,
	                stepName,
	                FailureSeverity.MEDIUM,
	                () -> {
	                    handler.handle(driver, locator, value, actions);
	                    return null;
	                }
	        );
	    }

	    // convenience methods
	    public void input(WebDriver driver, PageActions actions,
	                      PL_LocatorContractForFields locator,
	                      String value, String stepName) {

	        execute(driver, actions, FieldType.INPUT, locator, value, stepName);
	    }

	    public void click(WebDriver driver, PageActions actions,
	                      PL_LocatorContractForFields locator,
	                      String stepName) {

	        execute(driver, actions, FieldType.BUTTON, locator, null, stepName);
	    }

	    public void checkbox(WebDriver driver, PageActions actions,
	                         PL_LocatorContractForFields locator,
	                         String value, String stepName) {

	        execute(driver, actions, FieldType.CHECKBOX, locator, value, stepName);
	    }

}