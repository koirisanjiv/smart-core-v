package com.qaverse.smart.FieldActionHandler;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.ActionMethod.PageActions;
import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields;

public interface FieldActionHandler {

    Map<FieldType, FieldActionHandler> REGISTRY = new HashMap<>();

    FieldType getType();

    void handle(WebDriver driver,
                PL_LocatorContractForFields locator,
                Object value,
                PageActions actions);

    // 🔥 AUTO REGISTER
    static void register(FieldActionHandler handler) {
        REGISTRY.put(handler.getType(), handler);
    }

    static FieldActionHandler get(FieldType type) {
        FieldActionHandler handler = REGISTRY.get(type);

        if (handler == null) {
            throw new RuntimeException("No handler found for: " + type);
        }

        return handler;
    }
}