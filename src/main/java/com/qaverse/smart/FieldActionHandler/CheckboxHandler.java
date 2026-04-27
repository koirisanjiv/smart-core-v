package com.qaverse.smart.FieldActionHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Exception.ExceptionManager;
import com.qaverse.smart.Failure.FailureType;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;

public class CheckboxHandler implements FieldActionHandler {

    static {
        FieldActionHandler.register(new CheckboxHandler()); // ✅ FIXED
    }

    @Override
    public FieldType getType() {
        return FieldType.CHECKBOX;
    }

    @Override
    public void handle(WebDriver driver,
                       PL_LocatorContractForFields locator,
                       Object value,
                       PageActions actions) {

    	if (locator.checkbox() != null) {
    	    actions.getClick().clickOnElement(driver, "Checkbox", By.xpath(locator.checkbox()));
    	}
    	else if (locator.checkboxStringFormat() != null) {
    	    String xpath = String.format(locator.checkboxStringFormat(), value);
    	    actions.getClick().clickOnElement(driver, "Dynamic Checkbox", By.xpath(xpath));
    	}
    	else {
    	    throw new ExceptionManager(
    	            FailureType.INVALID_LOCATOR,
    	            "No checkbox locator provided"
    	    );
    	}
    }
}