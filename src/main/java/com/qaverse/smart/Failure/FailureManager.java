package com.qaverse.smart.Failure;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.report.ReportingManager;
public class FailureManager {

	private static final String SCREENSHOT_DIR = "screenshots/";

	public static void fail(WebDriver driver, String stepName, FailureType type, FailureSeverity severity,
			String message, Throwable e) {

		ReportingManager.logFail(driver, stepName, e);

		LoggerManager.error("STEP FAILED: " + stepName);
		LoggerManager.error("TYPE: " + type);
		LoggerManager.error("SEVERITY: " + severity);

		if (severity == FailureSeverity.HIGH) {
			throw new AssertionError(message, e);
		}
	}

	private static String captureScreenshot(WebDriver driver, String step) {
		try {
			new File(SCREENSHOT_DIR).mkdirs();

			String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String file = SCREENSHOT_DIR + step.replaceAll("[^a-zA-Z0-9]", "_") + "_" + time + ".png";

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(src.toPath(), new File(file).toPath());

			return file;

		} catch (Exception ex) {
			return "SCREENSHOT_FAILED";
		}
	}
}