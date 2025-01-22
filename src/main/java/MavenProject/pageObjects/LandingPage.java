package MavenProject.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Used Page Factory.
public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory.
    @FindBy(id="userEmail")
    WebElement Email;

    @FindBy(id="userPassword")
    WebElement passWord;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement error;

    public ProductCatalog login(String username, String pass){
        Email.sendKeys(username);
        passWord.sendKeys(pass);
        submit.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String errorMsg(){
        waitForWebElementToAppear(error);
        String errorMSG = error.getText();
        return errorMSG;
    }

}
