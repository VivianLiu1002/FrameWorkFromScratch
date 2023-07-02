package com.test.saucedemo.stepdefinitions;

import Utils.CongfigReader;
import Utils.DriverHelper;
import com.test.saucedemo.pages.CartPage;
import com.test.saucedemo.pages.CheckOutPage;
import com.test.saucedemo.pages.LoginPage;
import com.test.saucedemo.pages.MainPage;
import io.cucumber.java.en.*;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class OrderStepDef {
    WebDriver driver= DriverHelper.getDriver();
    LoginPage loginPage=new LoginPage(driver);
    MainPage mainPage=new MainPage(driver);
    CartPage cartPage=new CartPage(driver);
    CheckOutPage checkOutPage=new CheckOutPage(driver);
    @Given("User provides username and password to login successfully")
    public void userProvidesUsernameAndPasswordToLoginSuccessfully() {
       loginPage.login(CongfigReader.readProperty("QA_username"),
               CongfigReader.readProperty("QA_password"));
    }
    @When("User chooses the {string}, click add to cart button and validates it is added")
    public void userChoosesTheClickAddToCartButtonAndValidatesItIsAdded(String productName) {
        mainPage.chooseProduct(productName);
        mainPage.addProductToCart();
    }

    @When("User clicks cart icon and checkout button")
    public void userClicksCartIconAndCheckoutButton() {
       mainPage.clickCartIconButton(driver);
       cartPage.clickCheckOutButton();
    }
    @When("User provides {string},{string},{string} to checkout page and clicks continue button")
    public void userProvidesToCheckoutPageAndClicksContinueButton(String firstname, String lastname, String zipCode) {
      checkOutPage.sendUserInfo(firstname, lastname,zipCode);
    }
    @Then("User validates the {string},{string},{string},{string} with {string}% tax rate")
    public void user_validates_the_with_tax_rate(String expectedProductName,String itemTotal, String tax, String percent) {

    }

    @Then("User clicks finish button and validate {string} for purchase")
    public void userClicksFinishButtonAndValidateForPurchase(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
