package com.test.saucedemo.pages;

import Utils.BrowsersUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;

public class CheckOutPage {
    public CheckOutPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "#first-name")
    private WebElement firstname;
    @FindBy(css = "#last-name")
    private WebElement lastname;
    @FindBy(css = "#postal-code")
    private WebElement zipCode;
    @FindBy(css = "#continue")
    private WebElement continueButton;
    @FindBy(css = ".inventory_item_name")
    WebElement nameOfProduct;
    @FindBy(css = ".summary_subtotal_label")
    WebElement itemTotal;
    @FindBy(css = ".summary_tax_label")
    WebElement tax;
    @FindBy(xpath = "//div[contains(@class,'summary_total_label')]")
    WebElement totalPrice;
    @FindBy(css = "#finish")
    WebElement finishButton;
    @FindBy(css = ".complete-header")
    WebElement header;


    public void sendUserInfo(String firstname, String lastname,String zipCode){
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.zipCode.sendKeys(zipCode);
        continueButton.click();
    }
    public void validateItemOrderInformation(String expectedProductName, String expectedItemTotal,
                                             String expectedTax, String expectedTotalPrice, String taxRate){

        double itemTotalConvertion=Double.parseDouble(BrowsersUtils.getText(itemTotal).
                substring(BrowsersUtils.getText(itemTotal).indexOf("$")+1));

        double taxConvertion=Double.parseDouble(BrowsersUtils.getText(tax).
                substring(BrowsersUtils.getText(tax).indexOf("$")+1));

        double totalPriceConvertion=Double.parseDouble(BrowsersUtils.getText(totalPrice).
                substring(BrowsersUtils.getText(totalPrice).indexOf("$")+1));

        Assert.assertEquals(expectedProductName,BrowsersUtils.getText(nameOfProduct));
        Assert.assertEquals(expectedItemTotal,String.valueOf(itemTotalConvertion));

        double taxCalculation=(itemTotalConvertion*(Double.parseDouble(taxRate)*0.01));
        System.out.println(taxCalculation);//2.40
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedSumPriceTax = df.format(taxCalculation);//item total
        Assert.assertEquals(expectedTax,formattedSumPriceTax);
        Assert.assertEquals(taxCalculation,taxConvertion,0.01);
        Assert.assertEquals(expectedTotalPrice,String.valueOf(totalPriceConvertion));
    }


}
