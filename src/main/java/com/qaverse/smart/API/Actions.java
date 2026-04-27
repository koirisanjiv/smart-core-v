package com.qaverse.smart.API;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Executor.FieldExecutor;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;

public class Actions {

    private static final FieldExecutor executor = new FieldExecutor();

    public static void input(WebDriver driver,
                             PageActions actions,
                             PL_LocatorContractForFields locator,
                             String value,
                             String step) {

        executor.execute(driver, actions, FieldType.INPUT, locator, value, step);
    }

    public static void click(WebDriver driver,
                             PageActions actions,
                             PL_LocatorContractForFields locator,
                             String step) {

        executor.execute(driver, actions, FieldType.BUTTON, locator, null, step);
    }

    public static void checkbox(WebDriver driver,
                                PageActions actions,
                                PL_LocatorContractForFields locator,
                                String value,
                                String step) {

        executor.execute(driver, actions, FieldType.CHECKBOX, locator, value, step);
    }
}