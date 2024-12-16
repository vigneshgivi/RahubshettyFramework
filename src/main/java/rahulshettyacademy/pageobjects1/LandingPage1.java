package rahulshettyacademy.pageobjects1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

//import rahulshettyacademy.AbstractComponent;

public class LandingPage1 extends AbstractComponent{
		WebDriver driver;
		public LandingPage1(WebDriver driver) {
			
			
			super(driver);
			this.driver = driver;
			
			PageFactory.initElements(driver, this);

		}
		
		@FindBy(id = "userEmail")
		WebElement email;
      @FindBy(id = "userPassword")
			WebElement passwordEle;
	        @FindBy(id = "login")
			WebElement submit;
	        
	        @FindBy(css="[class*='flyInOut']")
	    	WebElement errorMessage;
	        
	        
	        
	        
	        
	        
	        public ProductCatalogue1 loginapplication(String username, String password) {
	        	email.sendKeys(username);
	        	passwordEle.sendKeys(password);
	        	submit.click();
	        	ProductCatalogue1 productCatalogue = new ProductCatalogue1(driver);
return productCatalogue;
	        }
	        
	        public String getErrorMessage()
	    	{
	    		waitForWebElementToAppear(errorMessage);
	    		return errorMessage.getText();
	    	}
	        
	        
	        
	        
	        
	        public void Goto() {
	        	driver.get("https://rahulshettyacademy.com/client");
	        }
}

