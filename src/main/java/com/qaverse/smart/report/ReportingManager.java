package com.qaverse.smart.report;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;

public class ReportingManager {

    public static void logStep(String stepName) {
        ExtentManager.getTest().log(Status.INFO, stepName);
        Allure.step(stepName);
    }

    public static void logPass(String stepName) {
        ExtentManager.getTest().log(Status.PASS, stepName);
        Allure.step(stepName + " ✅");
    }

    public static void logFail(WebDriver driver, String stepName, Throwable e) {

        ExtentManager.getTest().log(Status.FAIL, stepName + " ❌ " + e.getMessage());
        Allure.step(stepName + " ❌ " + e.getMessage());

        attachScreenshot(driver, stepName);
    }

    public static void attachScreenshot(WebDriver driver, String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            // Allure
            Allure.addAttachment(stepName, new ByteArrayInputStream(screenshot));

            // Extent
            String base64 = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);

            ExtentManager.getTest()
                    .addScreenCaptureFromBase64String(base64, stepName);

        } catch (Exception ignored) {}
    }
}