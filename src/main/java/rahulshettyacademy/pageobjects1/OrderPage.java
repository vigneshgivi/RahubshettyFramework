//System.getProperty(System.getProperty("user.dir") - intha line automatic ah project path ah eduthukum 




package rahulshettyacademy.pageobjects1;

import java.util.List;
//new line is added for CICD
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


public class OrderPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
//new line is added
	}

	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}


}
