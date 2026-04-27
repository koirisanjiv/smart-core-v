package com.qaverse.smart.FieldActionHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;

public class ClickHandler implements FieldActionHandler {

    static {
        FieldActionHandler.register(new ClickHandler()); // 🔥 auto register
    }

    @Override
    public FieldType getType() {
        return FieldType.BUTTON;
    }

    @Override
    public void handle(WebDriver driver,
                       PL_LocatorContractForFields locator,
                       Object value,
                       PageActions actions) {

        if (locator.button() != null) {
            actions.getClick().clickOnElement(driver, "Click", locator.button());
        } else if (locator.buttonStringFormat() != null) {
            String xpath = String.format(locator.buttonStringFormat(), value);
            actions.getClick().clickOnElement(driver, "Dynamic Click", By.xpath(xpath));
        } else {
            throw new RuntimeException("Invalid button locator");
        }
    }
}