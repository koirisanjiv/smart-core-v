package com.qaverse.smart.core.action;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class ClickAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.CLICK;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {
    	
        long start =
                System.currentTimeMillis();
    
        if (!(request.getTarget()
                instanceof By)) {

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

        element.click();

        return ActionResult.success(
                ActionType.CLICK,
                ActionMessages.CLICK_SUCCESS,
                System.currentTimeMillis()
                        - start
        );
    }
}