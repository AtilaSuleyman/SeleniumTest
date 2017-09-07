import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 07/09/2017.
 */
public class Advanced {

    WebDriver driver;
    private static ExtentReportManager reportManager;
    Wait<WebDriver> wait;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report", "Basic Report");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new FluentWait<WebDriver>(driver).withTimeout(30, SECONDS).pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @After
    public void tearDown() {
        //Closes the webpage and processes
        //driver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }

    @Test
    public void signInUser() throws IOException {
        List<String> row = new ArrayList<>();
        ExtentTest extentTest = reportManager.setUpTest();
//      extentTest.log(Status.WARNING, "Used to report an issue that may cause problems within a system");
//      driver.navigate().to("https://thedemosite.co.uk");
        //driver.navigate().to("http://automationpractice.com/index.php");

        //SpreadSheet Stuff
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/ShoppingPage.xlsx");

        int[] numberOfRows = {0};
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "sheet1");

            row = sheetReader.readRow(rowNo, "sheet1");
            switch (row.get(0)) {
                case ("chrome"):
                    driver = new ChromeDriver();
                    break;
                case ("fox"):
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }

        driver.navigate().to("http://automationpractice.com/index.php");


        ShoppingPage pom = new ShoppingPage(driver);
        WebElement signInButton = pom.getSignInButton();
        signInButton.click();
        WebElement emailAddress = pom.getEmailAddressInput();
        WebElement password = pom.getPasswordInput();
        WebElement submitButton = pom.getSubmitLogin();
        emailAddress.sendKeys(row.get(1));
        password.sendKeys(row.get(2).substring(1));
        submitButton.click();

        try {
            assertTrue(driver.findElement(By.className("header_user_info")).getText().equalsIgnoreCase("Eden Hazard"));
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (IOException e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.fail(details);
            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
            Assert.fail(details);
        }
    }

    @Test
    public void addToBasket() throws IOException {

        List<String> row = new ArrayList<>();
        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        ShoppingPage pom = new ShoppingPage(driver);

        WebElement imageLink = pom.getClothesImage();
        imageLink.click();
        WebElement addToBasket = pom.getAddToBasket();
        addToBasket.click();
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver){
                return driver.findElement(By.xpath("//*[@id=\"layer_cart\"]")).isDisplayed();
            }
        });
        WebElement proceedToBasket = pom.getProceedToBasket();
        proceedToBasket.click();

        try {
            assertTrue(driver.findElement(By.id("cart_summary")).isDisplayed());
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (IOException e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.fail(details);
            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
            Assert.fail(details);
        }
    }

    @Test
    public void goToSeleniumHomePage() throws IOException {

        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        ShoppingPage pom = new ShoppingPage(driver);

        WebElement imageLink = pom.getButton();
        imageLink.click();

        try {
            assertTrue(driver.getTitle().equals("Selenium Framework | Selenium, Cucumber, Ruby, Java et al."));
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (IOException e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.fail(details);
            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
        }
    }

    @Test
    public void goContactUsPage() throws IOException{

        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        ShoppingPage pom = new ShoppingPage(driver);

        WebElement contactUs = pom.getContactUs();
        contactUs.click();

        try {
            assertTrue(driver.getTitle().equalsIgnoreCase("Contact us - My Store"));
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (IOException e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.fail(details);
            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
        }
    }

    @Test
    public void sendContactUsMessage() throws IOException{

        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        ShoppingPage pom = new ShoppingPage(driver);

        WebElement contactUs = pom.getContactUs();
        contactUs.click();
        WebElement selectionBox = pom.getSelectionBox();
        selectionBox.click();
        selectionBox.sendKeys("Customer service");
        selectionBox.click();

//        try {
//            assertTrue(driver.getTitle().equalsIgnoreCase("Contact us - My Store"));
//            String imagePath = ScreenShot.take(driver, "image");
//            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
//                    " person to understand what the test is doing");
//            extentTest.pass("Passed");
//        } catch (IOException e) {
//            String details = "Training.Example Failing test: " + e.getMessage();
//            String imagePath = ScreenShot.take(driver, "image");
//            extentTest.addScreenCaptureFromPath(imagePath);
//            extentTest.fail(details);
//            extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
//            extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
//        }
    }
}
