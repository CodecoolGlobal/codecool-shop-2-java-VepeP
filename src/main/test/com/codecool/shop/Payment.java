package com.codecool.shop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Payment extends HomePage{
    @FindBy(id = "fname") WebElement name;
    @FindBy(id = "email") WebElement email;
    @FindBy(id = "adr") WebElement address;
    @FindBy(id = "city") WebElement city;
    @FindBy(id = "state") WebElement state;
    @FindBy(id = "zip") WebElement zip;
    @FindBy(id = "cname") WebElement cardHolderName;
    @FindBy(id = "ccnum") WebElement cardNumber;
    @FindBy(id = "expmonth") WebElement expirationMonth;
    @FindBy(id = "expyear") WebElement expirationYear;
    @FindBy(id = "cvv") WebElement cvv;
    @FindBy(id = "checkout") WebElement checkout;
    @FindBy(tagName = "input")List<WebElement> formData;

    public Payment() {
        super();
    }

    public void sendKeys(String[] data) {
        for (int i=0;i<getFormData().size()-2;i++) {
            getFormData().get(i).sendKeys(data[i]);
        }
    }

    public void pay(String[] data) {
        sendKeys(data);
        getCheckout().click();
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getCity() {
        return city;
    }

    public WebElement getState() {
        return state;
    }

    public WebElement getZip() {
        return zip;
    }

    public WebElement getCardHolderName() {
        return cardHolderName;
    }

    public WebElement getCardNumber() {
        return cardNumber;
    }

    public WebElement getExpirationMonth() {
        return expirationMonth;
    }

    public WebElement getExpirationYear() {
        return expirationYear;
    }

    public WebElement getCvv() {
        return cvv;
    }

    public List<WebElement> getFormData() {
        return formData;
    }

    public WebElement getCheckout() {
        return checkout;
    }
}
