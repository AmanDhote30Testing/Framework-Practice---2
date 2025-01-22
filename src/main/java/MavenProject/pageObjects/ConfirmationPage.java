package MavenProject.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement thankYouHeader;

    @FindBy(css="tr[class='ng-star-inserted'] label:nth-child(1)")
    WebElement orderID;


    public String verifyOrderConfirmation() throws InterruptedException {
        webdriverWait(20000);
        String msg = thankYouHeader.getText();
        return msg;
    }

    public String getOrderId(){
        String orderIDText = orderID.getText();
        return orderIDText;
    }
}
