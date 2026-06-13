package com.qaverse.smart.core.test;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaverse.smart.core.action.SmartActions;

public class SmartCoreSmokeTest extends BaseTest {

	
	@Test
	public void verifyGoogleSearch() {

		driver.get("https://www.google.com");

		SmartActions.input(driver, By.name("q"), "Smart Core");
		Assert.assertTrue(
		        driver.findElement(
		                By.name("q")
		        ).getAttribute("value")
		         .equals("Smart Core")
		);
	
	}
	
	
	//@Test
	public void verifyFailureFlow() {

	    driver.get(
	            "https://www.google.com"
	    );

	    SmartActions.click(
	            driver,
	            By.id("invalidLocator")
	    );
	}
	
	//@Test
	public void verifyElementNotFoundFailure() {

	    driver.get(
	            "https://www.google.com"
	    );

	    SmartActions.click(
	            driver,
	            By.id("invalidLocator")
	    );
	}
	
	 
    //@Test
    public void verifyLoginFlow() {

        SmartActions.input(
                driver,
                By.id("username"),
                "admin"
        );

        SmartActions.input(
                driver,
                By.id("password"),
                "admin123"
        );

        SmartActions.click(
                driver,
                By.id("loginButton")
        );
    }
}