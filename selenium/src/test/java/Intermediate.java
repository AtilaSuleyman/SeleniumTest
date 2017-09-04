import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 04/09/2017.
 */
public class Intermediate {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report", "Basic Report");
//        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        //Closes the webpage and processes
        driver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }

    @Test
    public void logMeIn() throws IOException {

        ExtentTest extentTest = reportManager.setUpTest();
//        extentTest.log(Status.WARNING, "Used to report an issue that may cause problems within a system");
//        driver.navigate().to("https://thedemosite.co.uk");
        String imagePath = ScreenShot.take(driver, "image");


        driver.navigate().to("https://thedemosite.co.uk");
        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys("ayaz");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys("ayaz");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys("ayaz");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys("ayaz");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();

        try {
            assertEquals("**Successful Login**", driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");

        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
            Assert.fail(details);
        }
    }
}
