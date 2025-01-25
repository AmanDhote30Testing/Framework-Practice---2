package TestComponents;

import MavenProject.pageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//GlobalData.properties");

        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            //properties Class
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }else if(browserName.equalsIgnoreCase("firefox")){
            //Firefox
        }else if(browserName.equalsIgnoreCase("edge")){
            //edge
            System.setProperty("webdriver.edge.driver","edge.exe");
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {

        String jsonContent;
        jsonContent = FileUtils.readFileToString(new File(FilePath));

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

    }
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ss =  (TakesScreenshot) driver;
        File source = ss.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//ScreenShots//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//ScreenShots//" + testCaseName + ".png";
    }

    @AfterMethod(alwaysRun = true)
    public void teardDown(){
        driver.quit();
    }
}
