package rahulshettyacademy.resources;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtentReportDemo {
    ExtentReports extent;

    @BeforeTest
    public void config() {
        // ExtentReports, ExtentSparkReporter
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Vignesh");
    }

    @Test
    public void initialDemo() {
        ExtentTest test = extent.createTest("initial demo");
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); // Added this line
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        driver.close();

        test.fail("Your test case is fail"); // Log the failure first
        extent.flush(); // Flush the report only after logging everything
    }
}
