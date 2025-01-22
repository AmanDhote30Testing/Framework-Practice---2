package MavenProject.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement inputCountry;

    @FindBy(css = ".ta-item")
    List<WebElement> countryList;

    @FindBy(css = ".btnn")
    WebElement submitButton;

    public void setInputCountry(String countryName) throws InterruptedException {
        inputCountry.sendKeys(countryName);
        webdriverWait(20000);
        List<WebElement> country = countryList;
        for(int i =0; i<= country.size(); i++){
            String selectCountry = country.get(i).getText();
            if(selectCountry.equalsIgnoreCase(countryName)){
                country.get(i).click();
                break;
            }
        }
    }

    public ConfirmationPage clickSubmit(){
        submitButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
