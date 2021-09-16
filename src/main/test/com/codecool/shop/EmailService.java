package com.codecool.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailService extends HomePage{
    final String address = "deeznutsshop666@gmail.com";
    final String pw = "nuclearkernel";
    @FindBy(xpath = "//input[@aria-label='E-mail-cím vagy telefonszám']") WebElement email;
    @FindBy(xpath = "//input[@aria-label='Adja meg jelszavát']") WebElement password;
    @FindBy(xpath = "//span[.='Következő']") WebElement next;

    public EmailService() {
        super();
    }

    public void login() {
        driver.get("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        email.sendKeys(address);
        next.click();
        password.sendKeys(pw);
        next.click();
    }

    public void checkLatestMail() {
        login();
        WebElement latestMail = driver.findElement(By.xpath("//table[@aria-readonly='true']//tr[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(latestMail)).click();
    }


}
