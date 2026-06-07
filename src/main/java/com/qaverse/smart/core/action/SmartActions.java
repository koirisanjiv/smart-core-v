package com.qaverse.smart.core.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.engine.SmartExecutionEngine;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class SmartActions {

	private static final SmartExecutionEngine ENGINE = new SmartExecutionEngine();

	private SmartActions() {
	}

	public static ActionResult click(WebDriver driver, By locator) {

		ActionRequest request = ActionRequest.builder().actionType(ActionType.CLICK).driver(driver).target(locator)
				.build();

		return ENGINE.execute(request, new ExecutionContext(driver));
	}

	public static ActionResult input(WebDriver driver, By locator, String value) {

		ActionRequest request = ActionRequest.builder().actionType(ActionType.INPUT).driver(driver).target(locator)
				.value(value).build();

		return ENGINE.execute(request, new ExecutionContext(driver));
	}

	public static ActionResult checkbox(WebDriver driver, By locator, boolean selected) {

		ActionRequest request = ActionRequest.builder().actionType(ActionType.CHECKBOX).driver(driver).target(locator)
				.value(selected).build();

		return ENGINE.execute(request, new ExecutionContext(driver));
	}

	public static ActionResult radio(WebDriver driver, By locator) {

		ActionRequest request = ActionRequest.builder().actionType(ActionType.RADIO).driver(driver).target(locator)
				.build();

		return ENGINE.execute(request, new ExecutionContext(driver));
	}

	public static ActionResult dropdown(WebDriver driver, By locator, String visibleText) {

		ActionRequest request = ActionRequest.builder().actionType(ActionType.DROPDOWN).driver(driver).target(locator)
				.value(visibleText).build();

		return ENGINE.execute(request, new ExecutionContext(driver));
	}
}