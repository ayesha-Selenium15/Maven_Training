package com.tests;

import com.pages.HomePages;
import com.util.DriverInit;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SampleTest extends DriverInit {
    public HomePages homePages;

    @BeforeClass
    public void initializeDriver() throws FileNotFoundException {
        DriverInit.driverInit("Chrome");
    }

    @BeforeMethod(alwaysRun = true)
    public void init_PageObjects() throws Exception {
        homePages = new HomePages(driver);
    }

    @Test(groups = {"sanity","smoke"})
    public void capture_screenshot(){
        String price = homePages.checkPriceValidation();
        Assert.assertEquals(price,"3000");
    }

    @Test (groups = {"smoke"})
    public void capture_screenshot1(){
        String price = homePages.checkPriceValidation();
        Assert.assertEquals(price,"200");
    }

    @Test
    public void capture_screenshot2(){
        String price = homePages.checkPriceValidation();
        Assert.assertEquals(price,"3000");
    }

    @Test
    public void capture_screenshot3(){
        String price = homePages.checkPriceValidation();
        Assert.assertEquals(price,"30");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus()){
            TakesScreenshot ts = (TakesScreenshot) driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(System.getProperty("user.dir") + "\\screenshots\\Test.PNG"));
            //Allure.addAttachment("Test", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
}