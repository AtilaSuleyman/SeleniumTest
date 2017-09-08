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
 * Created by Administrator on 08/09/2017.
 */
public class AutoTraderTests {

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
        //SpreadSheet Stuff
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader.xlsx");
        List<String> row = new ArrayList<>();
        int[] numberOfRows = {0};
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "sheet1");

            row = sheetReader.readRow(rowNo, "sheet1");
            System.out.println(row.get(0));
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
        wait = new FluentWait<WebDriver>(driver).withTimeout(90, SECONDS).pollingEvery(5, SECONDS)
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
    public void checkHomePage() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader.xlsx");
        List<String> row = new ArrayList<>();
        row = sheetReader.readRow(1, "sheet1");
        driver.navigate().to(row.get(1));

        try {
            assertTrue(driver.getTitle().equals("Auto Trader UK | Find New & Used Cars for Sale"));
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.log(Status.INFO, "added emu.");
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
    public void testFindNewCoupes() throws IOException, InterruptedException {
        AutoTraderHome pom = new AutoTraderHome(driver);
        ExtentTest extentTest = reportManager.setUpTest();

        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader.xlsx");
        List<String> row = new ArrayList<>();
        row = sheetReader.readRow(3, "sheet1");
        driver.navigate().to(row.get(1));
        WebElement buyNewUsedButton = pom.getBuyNewUsedCarButton();
        buyNewUsedButton.click();
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"top-nav__buying\"]/li[2]/a")).isDisplayed();
            }
        });
        WebElement viewNewCars = pom.getViewNewCars();
        viewNewCars.click();
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/ul/li[5]/div")).isDisplayed();
            }
        });
        NewCarsPage ncp = new NewCarsPage(driver);
        System.out.println("Im gonna click");
        WebElement coupeButton = ncp.getCoupeButton();
        coupeButton.click();

        try {
            assertEquals(driver.findElement(By.className("selected")), coupeButton);
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.log(Status.INFO, "added emu.");
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
    public void checkM5Reviews() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader.xlsx");
        List<String> row = new ArrayList<>();
        row = sheetReader.readRow(4, "sheet1");
        driver.navigate().to(row.get(1));
        AutoTraderHome pom = new AutoTraderHome(driver);
        WebElement carReviewsButton = pom.getCarReviewsButton();
        WebElement ownerReviewsButton = pom.getOwnerReviewsButton();
        carReviewsButton.click();
        ownerReviewsButton.click();
        ReviewsPage rp = new ReviewsPage(driver);
        WebElement makeSelector = rp.getMakeSelector();
        WebElement modelSelector = rp.getModelSelector();
        WebElement bodyTypeSeletor = rp.getBodyTypeSelector();
        WebElement searchReviews = rp.getSearchReviewsButton();
        makeSelector.click();
        makeSelector.sendKeys(row.get(2));
        makeSelector.click();
        modelSelector.click();
        modelSelector.sendKeys(row.get(3));
        modelSelector.click();
        bodyTypeSeletor.click();
        bodyTypeSeletor.sendKeys(row.get(4));
        bodyTypeSeletor.click();
        searchReviews.click();
        WebElement carReviews = rp.getCarReviewsName();
        assertTrue(carReviews.getText().equals(row.get(5)));

        try {
            assertTrue(carReviews.getText().equals(row.get(5)));
            String imagePath = ScreenShot.take(driver, "image");
            extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.log(Status.INFO, "added emu.");
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
}
