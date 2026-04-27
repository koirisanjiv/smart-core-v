package com.qaverse.smart.report;

import java.io.ByteArrayInputStream;

public class AllureSafe {

    private static boolean isAvailable = checkAllure();

    private static boolean checkAllure() {
        try {
            Class.forName("io.qameta.allure.Allure");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void addAttachment(String name, String contentType, ByteArrayInputStream stream, String ext) {
        if (!isAvailable) {
			return;
		}

        try {
            io.qameta.allure.Allure.addAttachment(name, contentType, stream, ext);
        } catch (Throwable ignored) {}
    }
}
