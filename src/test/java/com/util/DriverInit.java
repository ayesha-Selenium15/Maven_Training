package com.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class DriverInit {

    public static WebDriver driver;

    @BeforeSuite
    @Parameters({"browser"})
    public void killDrivers(String browser) throws IOException {
        if(browser.equalsIgnoreCase("chrome")) {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } else if (browser.equalsIgnoreCase("edge")) {
            Runtime.getRuntime().exec("taskkill /F /IM edgedriver.exe /T");
        } else if(browser.equalsIgnoreCase("firefox")){
            Runtime.getRuntime().exec("taskkill /F /IM firefoxdriver.exe /T");
        }else if(browser.equalsIgnoreCase("safari")){
            Runtime.getRuntime().exec("taskkill /F /IM safaridriver.exe /T");
        }
    }

    public static void driverInit(String browser){
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://testautomationpractice.blogspot.com/");
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get("https://testautomationpractice.blogspot.com/");
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get("https://testautomationpractice.blogspot.com/");
            driver.manage().window().maximize();
        } else {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
            driver.get("https://testautomationpractice.blogspot.com/");
            driver.manage().window().maximize();
        }
    }
}