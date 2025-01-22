package MavenProject.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//Used Page Factory.
public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory.
    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector("mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By catalogName = By.cssSelector("b");
    By notifi = By.cssSelector("#toast-container");


    public List<WebElement> getProductList() throws InterruptedException {
        webdriverWait(20000);
        List<WebElement> productList = products;
        return productList;
    }

    public WebElement getProductsByName(String productName) throws InterruptedException{
    webdriverWait(20);
        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(catalogName).getText().contains(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        getProductsByName(productName).findElement(addToCart).click();
        waitForElementToAppear(notifi);
        Thread.sleep(10000);
        webdriverWait(30000);
    }

}
