package com.qaverse.smart.core.test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qaverse.smart.core.listener.ListenerManager;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage()
              .window()
              .maximize();

        driver.manage()
              .timeouts()
              .implicitlyWait(
                      Duration.ofSeconds(10)
              );

        ListenerManager.register(
                "console-listener",
                new ConsoleExecutionListener()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        ListenerManager.clear();

        if (driver != null) {

            driver.quit();
        }
    }
}