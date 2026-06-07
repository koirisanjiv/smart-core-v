package com.qaverse.smart.core.contract;

import org.openqa.selenium.By;

import com.qaverse.smart.core.context.ExecutionContext;

public interface LocatorProvider {

    By resolve(
            Object locator,
            ExecutionContext context
    );

}