package com.qaverse.smart.ActionMethod;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.Logging.LoggerManager;
import com.qaverse.smart.Wait.WaitManager;


public class FileUpload implements MyActions {

	private WaitManager getWait() {
	    return ActionFactory.getAction(WaitManager.class);
	}

	public void uploadFile(WebDriver driver,
	                       String labelName,
	                       By fileInputLocator,
	                       String filePath) {

	    Exception lastException = null;

	    for (int attempt = 1; attempt <= 3; attempt++) {
	        try {
	            WebElement input = getWait().waitForPresence(driver,fileInputLocator,5);

	            File file = new File(filePath);

	            if (!file.exists()) {
	                throw new FileNotFoundException(
	                        "Upload file not found: " + filePath);
	            }

	            input.sendKeys(file.getAbsolutePath());

	            LoggerManager.info("✅ File uploaded successfully: {}", labelName);
	            return;

	        } catch (Exception e) {
	            lastException = e;

	            LoggerManager.warn("⚠️ File upload failed attempt {} for {}",
	                    attempt, labelName);

	            WaitManager.hardWait(1);
	        }
	    }

	    throw new RuntimeException(
	            "❌ Genuine file upload issue for: " + labelName,
	            lastException
	    );
	}

}
