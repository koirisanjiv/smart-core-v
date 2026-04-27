package com.qaverse.smart.FieldActionHandler;


public class HandlerLoader {

    public static void init() {
		FieldActionRegistry.register(new InputHandler());
		FieldActionRegistry.register(new ClickHandler());
		FieldActionRegistry.register(new CheckboxHandler());
	}
}