package com.qaverse.smart.core.engine.runtime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qaverse.smart.core.action.ActionMessages;
import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class SeleniumEngine
        implements AutomationEngine {
	
	private WebElement getElement(
	        ActionRequest request) {

	    if (!(request.getTarget()
	            instanceof By)) {

	        throw new IllegalArgumentException(
	                ActionMessages.INVALID_TARGET
	        );
	    }

	    return request.getDriver()
	                  .findElement(
	                          (By) request.getTarget()
	                  );
	}

	@Override
	public ActionResult click(
	        ActionRequest request) {

	    long start =
	            System.currentTimeMillis();

	    WebElement element =
	            getElement(request);

	    element.click();

	    return ActionResult.success(
	            ActionType.CLICK,
	            ActionMessages.CLICK_SUCCESS,
	            System.currentTimeMillis()
	                    - start
	    );
	}
	
	@Override
	public ActionResult input(
	        ActionRequest request) {

	    long start =
	            System.currentTimeMillis();

	    WebElement element =
	            getElement(request);

	    element.clear();

	    if (request.getValue() != null) {

	        element.sendKeys(
	                String.valueOf(
	                        request.getValue()
	                )
	        );
	    }

	    return ActionResult.success(
	            ActionType.INPUT,
	            ActionMessages.INPUT_SUCCESS,
	            System.currentTimeMillis()
	                    - start
	    );
	}

	@Override
	public ActionResult checkbox(
	        ActionRequest request) {

	    long start =
	            System.currentTimeMillis();

	    WebElement element =
	            getElement(request);

	    if (!element.isSelected()) {

	        element.click();
	    }

	    return ActionResult.success(
	            ActionType.CHECKBOX,
	            ActionMessages.CHECKBOX_SUCCESS,
	            System.currentTimeMillis()
	                    - start
	    );
	}

	@Override
	public ActionResult radio(
	        ActionRequest request) {

	    long start =
	            System.currentTimeMillis();

	    WebElement element =
	            getElement(request);

	    if (!element.isSelected()) {

	        element.click();
	    }

	    return ActionResult.success(
	            ActionType.RADIO,
	            ActionMessages.RADIO_SUCCESS,
	            System.currentTimeMillis()
	                    - start
	    );
	}

	@Override
	public ActionResult dropdown(
	        ActionRequest request) {

	    long start =
	            System.currentTimeMillis();

	    WebElement element =
	            getElement(request);

	    Select select =
	            new Select(element);

	    select.selectByVisibleText(
	            String.valueOf(
	                    request.getValue()
	            )
	    );

	    return ActionResult.success(
	            ActionType.DROPDOWN,
	            ActionMessages.DROPDOWN_SUCCESS,
	            System.currentTimeMillis()
	                    - start
	    );
	}
}