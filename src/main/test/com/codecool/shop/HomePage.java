package com.codecool.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wait = new WebDriverWait(driver, 10);
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;
    @FindBy(xpath = "//a[@data-id='1'][@class='btn btn-success cartbutton']") WebElement Peti;
    @FindBy(xpath = "//a[@data-id='2'][@class='btn btn-success cartbutton']") WebElement Saz;
    @FindBy(xpath = "//a[@data-id='3'][@class='btn btn-success cartbutton']") WebElement Mate;
    @FindBy(xpath = "//a[@data-id='4'][@class='btn btn-success cartbutton']") WebElement Ichy;
    @FindBy(xpath = "//a[@data-id='5'][@class='btn btn-success cartbutton']") WebElement Balazs;
    @FindBy(xpath = "//a[@data-id='6'][@class='btn btn-success cartbutton']") WebElement Martin;
    @FindBy(xpath = "//a[@data-id='7'][@class='btn btn-success cartbutton']") WebElement Cartman;
    @FindBy(xpath = "//a[@data-id='8'][@class='btn btn-success cartbutton']") WebElement Kenny;
    @FindBy(xpath = "//a[@data-id='9'][@class='btn btn-success cartbutton']") WebElement shirt;
    @FindBy(xpath = "//a[@data-id='10'][@class='btn btn-success cartbutton']") WebElement noIdea;
    @FindBy(id = "viewcartbutton") WebElement cart;
    @FindBy(linkText = "Suppliers") WebElement suppliers;
    @FindBy(linkText = "Categories") WebElement categories;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void putToCart(WebElement... elements) {
        for (WebElement item: elements) {
            //wait.until(ExpectedConditions.visibilityOf(item));
            js.executeScript("arguments[0].scrollIntoView(true);", item);
            js.executeScript("arguments[0].click();", item);
            //item.click();
        }
    }

    public void clickOnCart() {
        wait.until(ExpectedConditions.visibilityOf(cart));
        cart.click();
    }

    public void buyAndCheckout(WebElement... elements) {
        putToCart(elements);
        clickOnCart();
        getCheckoutPage();
    }

    public void getCheckoutPage() {
        driver.get("http://localhost:8888/payment");
    }

    public WebElement getPeti() {
        return Peti;
    }

    public WebElement getSaz() {
        return Saz;
    }

    public WebElement getMate() {
        return Mate;
    }

    public WebElement getIchy() {
        return Ichy;
    }

    public WebElement getBalazs() {
        return Balazs;
    }

    public WebElement getMartin() {
        return Martin;
    }

    public WebElement getCartman() {
        return Cartman;
    }

    public WebElement getKenny() {
        return Kenny;
    }

    public WebElement getShirt() {
        return shirt;
    }

    public WebElement getNoIdea() {
        return noIdea;
    }

    public WebElement getCart() {
        return cart;
    }

    public WebElement getSuppliers() {
        return suppliers;
    }

    public WebElement getCategories() {
        return categories;
    }

    protected void getPage() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost:8888/");
        driver.manage().window().maximize();
    }

    protected void closePage() {
        driver.close();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void pullUpCart() {
        cart.click();
    }

    public String getTotal() {
        WebElement total = driver.findElement(By.xpath("//strong[@id='totalprice']"));
        wait.until(ExpectedConditions.elementToBeClickable(total));
        return total.getText();
    }
}
