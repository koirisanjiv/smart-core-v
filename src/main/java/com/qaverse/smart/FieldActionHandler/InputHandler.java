package com.qaverse.smart.FieldActionHandler;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;

public class InputHandler implements FieldActionHandler {

    static {
        FieldActionHandler.register(new InputHandler()); // ✅ correct
    }

    @Override
    public FieldType getType() {
        return FieldType.INPUT;
    }

    @Override
    public void handle(WebDriver driver,
                       PL_LocatorContractForFields locator,
                       Object value,
                       PageActions actions) {

        if (locator.field() == null) {
            throw new RuntimeException("Field locator is null");
        }

        actions.getInputField()
               .setDataInTextField(driver, "Input", locator.field(), String.valueOf(value));
    }
}