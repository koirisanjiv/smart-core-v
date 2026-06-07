package com.qaverse.smart.core.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class CheckboxAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.CHECKBOX;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {

        long start =
                System.currentTimeMillis();

        if (!(request.getTarget() instanceof By)) {

            throw new IllegalArgumentException(
                    ActionMessages.INVALID_TARGET
            );
        }

        WebDriver driver =
                request.getDriver();

        WebElement element =
                driver.findElement(
                        (By) request.getTarget()
                );

        boolean requiredState =
                Boolean.parseBoolean(
                        String.valueOf(
                                request.getValue()
                        )
                );

        if (element.isSelected()
                != requiredState) {

            element.click();
        }

        return ActionResult.success(
                ActionType.CHECKBOX,
                ActionMessages.CHECKBOX_UPDATED,
                System.currentTimeMillis()
                        - start
        );
    }
}