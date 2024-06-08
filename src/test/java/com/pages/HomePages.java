package com.pages;

import com.util.DriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePages extends DriverInit {

    @FindBy(xpath = "//*[@id=\"HTML1\"]/div[1]/table/tbody/tr")
    List<WebElement> tableRow;

    @FindBy(xpath = "//*[@id=\"HTML1\"]/div[1]/table/tbody/tr/td")
    List<WebElement> tableColumns;

    @FindBy(xpath = "//td[text()='Master In Selenium']")
    WebElement bookName;

    @FindBy(xpath = "//td[text()='Master In Selenium']/../td[4]")
    WebElement bookPrice;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "phone")
    WebElement phoneField;

    public HomePages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String checkPriceValidation(){
        String price = null;
        int rowCount = tableRow.size();
        System.out.println(rowCount);
        int colCount = tableColumns.size();
        System.out.println(colCount);
        String expected = "Master In Selenium";
        String actual = bookName.getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,expected);
        if( expected.equals(actual)){
            price = bookPrice.getText();
            System.out.println(price);
        }
        return price;
    }
}
