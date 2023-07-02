package com.test.saucedemo.pages;

import Utils.BrowsersUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a//div")
   private List<WebElement> allProducts;
    @FindBy(xpath = "//button[.='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    public void chooseProduct(String productName){
        for (WebElement product : allProducts){
            if (BrowsersUtils.getText(product).equals(productName)){
                product.click();
                break;
            }
        }
    }
    public void addProductToCart(){
        Assert.assertTrue(BrowsersUtils.getText(cartIcon).isEmpty());
        addToCartButton.click();
        Assert.assertFalse(BrowsersUtils.getText(cartIcon).isEmpty());
    }
    public void clickCartIconButton(WebDriver driver){

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        cartIcon=wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }
}
