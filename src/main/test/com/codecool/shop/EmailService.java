package com.codecool.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailService extends HomePage{
    final String address = "cicamica6667";
    final String pw = "MicaCica7";
    @FindBy(xpath = "//input[@name='user']") WebElement email;
    @FindBy(xpath = "//input[@name='passwd']") WebElement password;
    @FindBy(xpath = "//button[.='Belépés']") WebElement next;

    public EmailService() {
        super();
    }

    public void login() {
        driver.get("https://www.citromail.hu");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='ELFOGADOM']"))).click();
        email.sendKeys(address);
        password.sendKeys(pw);
        next.click();
    }

    public void checkLatestMail() {
        login();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='formInline'][@title='Programmer Shop <programmingshop2021@gmail.com>'][1]"))));
        WebElement latestMail = driver.findElement(By.xpath("//div[@class='mail-list-row-container read ']//div[@class='drag unr mail-list-row '][1]"));
        wait.until(ExpectedConditions.elementToBeClickable(latestMail));
        latestMail.click();
    }


}
