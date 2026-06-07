package com.qaverse.smart.core.contract;

@FunctionalInterface
public interface Condition<T> {

    T evaluate();

}