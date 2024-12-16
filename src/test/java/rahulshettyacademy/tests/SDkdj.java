package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class SDkdj {

    public static void main(String[] args) {
        String productName = "ZARA COAT 3";

        // Setup ChromeDriver
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to URL
        driver.get("https://rahulshettyacademy.com/client");

        // Login process
        driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        // Find the product
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);
        assert prod != null;
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        // Wait for the toast message and invisibility of animation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // Go to the cart and verify the product
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        // Proceed to checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();

        // Enter country and confirm the order
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
     // Locate the element
        WebElement element = driver.findElement(By.xpath("//*[contains(@class,'action__submit')]"));
        // Perform mouse hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.click();

        // Confirm the order success message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        // Close the browser
       // driver.close();
    }
}
