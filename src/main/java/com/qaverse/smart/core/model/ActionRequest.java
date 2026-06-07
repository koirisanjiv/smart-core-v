package com.qaverse.smart.core.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.action.ActionType;

public final class ActionRequest {

    private final ActionType actionType;

    private final Object target;

    private final Object value;

    private final WebDriver driver;

    private final Map<String, Object> metadata;

    private ActionRequest(Builder builder) {

        this.actionType = builder.actionType;
        this.target = builder.target;
        this.value = builder.value;
        this.driver = builder.driver;

        this.metadata =
                Collections.unmodifiableMap(
                        new HashMap<>(builder.metadata)
                );
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Object getTarget() {
        return target;
    }

    public Object getValue() {
        return value;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private ActionType actionType;

        private Object target;

        private Object value;

        private WebDriver driver;

        private final Map<String, Object> metadata =
                new HashMap<>();

        public Builder actionType(
                ActionType actionType) {

            this.actionType = actionType;
            return this;
        }

        public Builder target(
                Object target) {

            this.target = target;
            return this;
        }

        public Builder value(
                Object value) {

            this.value = value;
            return this;
        }

        public Builder driver(
                WebDriver driver) {

            this.driver = driver;
            return this;
        }

        public Builder metadata(
                String key,
                Object value) {

            this.metadata.put(
                    key,
                    value
            );

            return this;
        }

        public ActionRequest build() {

            return new ActionRequest(this);
        }
    }
}