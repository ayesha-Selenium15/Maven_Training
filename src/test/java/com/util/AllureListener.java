package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Cleanup if necessary
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture and attach screenshot to Allure report
        if (driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File file = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file,new File(System.getProperty("user.dir") + "\\screenshots\\+ screenshotName + \"_\" + UUID.randomUUID() + \".png"));
                attachScreenshot(String.valueOf(new File(System.getProperty("user.dir") + "\\screenshots\\+ screenshotName + \"_\" + UUID.randomUUID() + \".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshot(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Handle skipped tests if necessary
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Handle partial success if necessary
    }

    @Override
    public void onStart(ITestContext context) {
        // Any setup needed before the suite starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // Cleanup after the suite finishes
        if (driver != null) {
            driver.quit();
        }
    }
}
