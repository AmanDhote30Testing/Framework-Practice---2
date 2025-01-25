package MavenProject.Tests;

import MavenProject.pageObjects.CartPage;
import MavenProject.pageObjects.CheckOutPage;
import MavenProject.pageObjects.ConfirmationPage;
import MavenProject.pageObjects.ProductCatalog;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest{


    @Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {
        String productName = "IPHONE 13 PRO";
        landingPage.goTo();
        ProductCatalog productCatalog = landingPage.login("amandhote@gogo.con", "Tech@12356");
        Assert.assertEquals("Incorrect email or password.",landingPage.errorMsg());
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {
        String productName = "IPHONE 13 PRO";
        landingPage.goTo();
        ProductCatalog productCatalog = landingPage.login("amandhote@gogo.com", "Tech@123");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.cartPage();

        boolean match = cartPage.prodNameInCart(productName);
        Assert.assertFalse(match);
    }
}
