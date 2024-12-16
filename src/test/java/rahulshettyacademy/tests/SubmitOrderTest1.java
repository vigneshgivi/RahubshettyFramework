package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
//import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects1.CartPage;
import rahulshettyacademy.pageobjects1.CheckoutPage;
import rahulshettyacademy.pageobjects1.ConfirmationPage;
import rahulshettyacademy.pageobjects1.OrderPage;
import rahulshettyacademy.pageobjects1.ProductCatalogue1;

public class SubmitOrderTest1 extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups = {"Purchase"})
	
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{

		
		// LandingPage1 landingPage= launchApplication();
		ProductCatalogue1 productCatalogue = landingPage.loginapplication(input.get("email"), input.get("password"));

//ProductCatalogue1 productCatalogue = new ProductCatalogue1(driver);
		List<WebElement> products = productCatalogue.getproductlist();

		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartpage = productCatalogue.goToCartPage();
//CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();

		checkoutpage.selectCountry("india");
//checkoutpage.submitOrder();
		
		

		ConfirmationPage confirmationpage = checkoutpage.submitOrder();

		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

//driver.close();
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue1 productCatalogue = landingPage.loginapplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
}
	
//	@DataProvider
//	public Object[][] getData() throws IOException
//	{
////		HashMap<String,String> map = new HashMap<String,String>();
////		map.put("email", "anshika@gmail.com");
////		map.put("password", "Iamking@000");
////		map.put("product", "ZARA COAT 3");
////		
////		HashMap<String,String> map1 = new HashMap<String,String>();
////		map1.put("email", "abcd@emample.com");
////		map1.put("password", "Abcd@1234");
////		map1.put("product", "ADIDAS ORIGINAL");
//		
//		//List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//		//return new Object[][]  {{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"abcd@emample.com", "Abcd@1234", "ADIDAS ORIGINAL"}};
//		//{{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"abcd@emample.com", "Abcd@1234", "ADIDAS ORIGINAL"}}
//	
////		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//		return new Object[][]  {{map}, { map1} };
//		
//	}
	
	
//	public String getScreenshot(String testCaseName) throws IOException {
//	    TakesScreenshot ts = (TakesScreenshot) driver;
//	    File source = ts.getScreenshotAs(OutputType.FILE);
//	    File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
//	    FileUtils.copyFile(source, file);
//	    return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
//	}
//	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
	
//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	
	
	
	
	}
	
	

