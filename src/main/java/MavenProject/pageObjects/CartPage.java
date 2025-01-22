package MavenProject.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='cartSection']//h3")
    List<WebElement> verifyCart;

    @FindBy(css = ".totalRow button")
    WebElement checkOut;

    By paymentPage = By.cssSelector(".payment");

    public boolean prodNameInCart(String productName){
        boolean match = verifyCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage clickCheckout(){
        checkOut.click();
        waitForElementToAppear(paymentPage);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }
}
