package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry2;
import rahulshettyacademy.pageobjects1.CartPage;
import rahulshettyacademy.pageobjects1.CheckoutPage;
import rahulshettyacademy.pageobjects1.ConfirmationPage;
import rahulshettyacademy.pageobjects1.LandingPage1;
import rahulshettyacademy.pageobjects1.ProductCatalogue1;

public class ErrorValidationsTest1 extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry2.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		// LandingPage1 landingPage= launchApplication();
		landingPage.loginapplication("aadi@gmail.com", "Iamking@0007");//7 is added which is incorrect
//landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
//Incorrect email - intha msg ah eduthu vittuta above step la irunthu
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		// LandingPage1 landingPage= launchApplication();
		ProductCatalogue1 productCatalogue = landingPage.loginapplication("abcd@emample.com", "Abcd@1234");
//
//		abcd@emample.com
//
//
//		Abcd@1234

//ProductCatalogue1 productCatalogue = new ProductCatalogue1(driver);
		List<WebElement> products = productCatalogue.getproductlist();

		productCatalogue.addProductToCart(productName);

		CartPage cartpage = productCatalogue.goToCartPage();
//CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

//driver.close();
	}

}