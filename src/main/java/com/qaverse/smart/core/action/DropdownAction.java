package com.qaverse.smart.core.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class DropdownAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.DROPDOWN;
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

        Select select =
                new Select(element);

        select.selectByVisibleText(
                String.valueOf(
                        request.getValue()
                )
        );

        return ActionResult.success(
                ActionType.DROPDOWN,
                ActionMessages.DROPDOWN_SELECTED,
                System.currentTimeMillis()
                        - start
        );
    }
}