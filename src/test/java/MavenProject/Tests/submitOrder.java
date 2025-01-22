package MavenProject.Tests;

import MavenProject.pageObjects.*;
import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrder extends BaseTest{

    @Test(dataProvider = "getData", groups = "purchase")
    public void submitorder(HashMap<String,String> input) throws InterruptedException, IOException {
        landingPage.goTo();
        ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("pass"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.cartPage();

        boolean match = cartPage.prodNameInCart(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.clickCheckout();

        checkOutPage.setInputCountry("India");
        ConfirmationPage confirmationPage = checkOutPage.clickSubmit();

        String thankYouText = "thankyou for the order";
        boolean confirmed = confirmationPage.verifyOrderConfirmation().toLowerCase().contains(thankYouText);
        Assert.assertTrue(confirmed);
        confirmationPage.printText(confirmationPage.getOrderId());
    }

    @Test(dependsOnMethods = {"submitorder"}, dataProvider = "getData")
    public void OrderHistory(HashMap<String,String> input) {
        ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("pass"));
        OrderPage orderPage = productCatalog.OrdersPage();
        Assert.assertTrue(orderPage.verifyOrders(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String >> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)} };
    }
}
