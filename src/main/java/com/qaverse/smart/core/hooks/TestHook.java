package com.qaverse.smart.core.hooks;

import com.qaverse.smart.core.context.TestContext;

public interface TestHook {

    default void beforeSuite(TestContext context) {}
    default void beforeTest(TestContext context) {}
    default void onFailure(TestContext context, Throwable t) {}

    int order();
}