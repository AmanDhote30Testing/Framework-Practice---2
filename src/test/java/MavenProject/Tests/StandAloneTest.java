package MavenProject.Tests;

import MavenProject.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        LandingPage landingPage = new LandingPage(driver);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver,(Duration.ofMinutes(5)));
        String productName = "IPHONE 13 PRO";
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("amandhote@gogo.com");
        driver.findElement(By.id("userPassword")).sendKeys("Tech@123");
        driver.findElement(By.id("login")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().contains("IPHONE 13 PRO")).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.xpath("//li//button[contains(text(),'Cart')]")).click();

        List<WebElement> verifyCart = driver.findElements(By.xpath("//*[@class='cartSection']//h3"));

        boolean match = verifyCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".payment"))));

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");

        List<WebElement> country = driver.findElements(By.cssSelector(".ta-item"));

        for(int i =0; i<= country.size(); i++){
            String countryName = country.get(i).getText();
            if(countryName.equalsIgnoreCase("India")){
                country.get(i).click();
                break;
            }
        }

        driver.findElement(By.cssSelector(".btnn")).click();

        driver.findElement(By.cssSelector("tr[class='ng-star-inserted'] label:nth-child(1)")).getText();




    }
}
