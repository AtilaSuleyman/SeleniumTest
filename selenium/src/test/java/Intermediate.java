import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void logMeIn() throws IOException, InterruptedException {


        ExtentTest extentTest = reportManager.setUpTest();
//        extentTest.log(Status.WARNING, "Used to report an issue that may cause problems within a system");
//        driver.navigate().to("https://thedemosite.co.uk");

        //SpreadSheet Stuff
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/credentials.xlsx");

        int[] numberOfRows = {0, 1};
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "sheet1");

            List<String> row = sheetReader.readRow(rowNo, "sheet1");

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

            driver.navigate().to("http://thedemosite.co.uk");
            String imagePath = ScreenShot.take(driver, "image");
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(30, SECONDS)
                    .pollingEvery(5, SECONDS)
                    .ignoring(NoSuchElementException.class);

            System.out.println(row.get(0));
            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(1));
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(2));
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
            //Thread.sleep(1000);
            WebElement foo = wait.until(new Function<WebDriver, WebElement>()
            {
                public WebElement apply(WebDriver driver) {
                    System.out.println("Waiting");
                    return driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
                }
            });
            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(1));
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(2));
            //Thread.sleep(1000);
            WebElement bara = wait.until(new Function<WebDriver, WebElement>()
            {
                public WebElement apply(WebDriver driver) {
                    System.out.println("Waiting");
                    return driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
                }
            });
            WebElement bar = wait.until(new Function<WebDriver, WebElement>()
            {
                public WebElement apply(WebDriver driver) {
                    System.out.println("Waiting");
                    return driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
                }
            });
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
            Thread.sleep(1000);

            try {
                assertEquals(row.get(3), driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
                extentTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                        " person to understand what the test is doing");
                extentTest.pass("Passed");
                driver.quit();

            } catch (AssertionError e) {
                String details = "Training.Example Failing test: " + e.getMessage();
                extentTest.fail(details);
                extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
                extentTest.addScreenCaptureFromPath(imagePath);
                extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
                Assert.fail(details);
                driver.quit();
            }
        }
    }

    @Test
    public void mouseDrag(){
        driver.navigate().to("http://demoqa.com/draggable/");
        WebElement n = driver.findElement(By.id("draggable"));
        Actions builder = new Actions(driver);
        System.out.println(n.getLocation());
        builder.moveByOffset(400,360).clickAndHold().moveByOffset(300,300).release().perform();
        System.out.println(n.getLocation());
        assertEquals(n.getLocation().getX()-300, 264);
        assertEquals(n.getLocation().getY()-300, 339);
    }

    @Test
    public void mouseDragIntoBox(){
        driver.navigate().to("http://demoqa.com/droppable/");
        WebElement whiteBox = driver.findElement(By.id("draggableview"));
        WebElement greyBox = driver.findElement(By.id("droppableview"));
        Actions builder = new Actions(driver);
        builder.dragAndDrop(whiteBox, greyBox).perform();
        assertEquals(greyBox.getText(), "Dropped!");
    }

    @Test
    public void resizeBox(){
        Resizeable pomr = new Resizeable(driver);
        driver.navigate().to("http://demoqa.com/resizable/");
        WebElement line = pomr.getLineToDrag();
        WebElement box = driver.findElement(By.id("resizable"));
        Actions builder = new Actions(driver);
        Dimension pastD = box.getSize();
        builder.dragAndDropBy(line, 300,200).perform();
        assertNotEquals(box.getSize(), pastD);
    }

    @Test
    public void selectBoxes(){
        int cntr = 0;
        Selectable poms = new Selectable(driver);
        driver.navigate().to("http://demoqa.com/selectable/");
        WebElement boxOne = poms.getFirstItem();
        WebElement boxTwo = poms.getSecondItem();
        WebElement boxThree = poms.getThirdItem();
        WebElement boxFour = poms.getFourthItem();
        WebElement boxFifth = poms.getFifthItem();
        WebElement boxSix = poms.getSixthItem();
        WebElement boxSeven = poms.getSeventhItem();
        List<WebElement> childs = poms.getDescendants();
        Actions builder = new Actions(driver);
        System.out.println(childs.size());
        builder.dragAndDrop(boxOne, boxTwo).perform();
        for (WebElement e: childs) {
            if(e.getAttribute("class").equals("ui-widget-content ui-corner-left ui-selectee ui-selected")){
                cntr++;
            }
        }
        assertTrue(cntr == 2);
    }

    @Test
    public void reorderBox() throws InterruptedException {
        Sortable pomSort = new Sortable(driver);
        driver.navigate().to("http://demoqa.com/sortable/");
        WebElement sortBoxOne = pomSort.getSortableBoxOne();
        WebElement sortBoxFour = pomSort.getSortableBoxFour();
        Point p4 = sortBoxFour.getLocation();
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(sortBoxOne,0,100).perform();
        assertEquals(driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[4]")).getText(),"Item 1");
    }

}
