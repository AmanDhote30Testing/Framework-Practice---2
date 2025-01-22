package AbstractComponents;

import MavenProject.pageObjects.CartPage;
import MavenProject.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    @FindBy(css="[routerlink*='cart']")
    WebElement cart;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement order;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy){
        WebDriverWait webDriverWait = new WebDriverWait(driver,(Duration.ofMinutes(5)));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement ele){
        WebDriverWait webDriverWait = new WebDriverWait(driver,(Duration.ofMinutes(5)));
        webDriverWait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement element){
        WebDriverWait webDriverWait = new WebDriverWait(driver,(Duration.ofMinutes(5)));
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void webdriverWait(int time) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(time));
    }

    public CartPage cartPage() throws InterruptedException {
        webdriverWait(20000);
        cart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage OrdersPage(){
        order.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }



    public void printText(String text){
        System.out.println(text);;
    }
}
