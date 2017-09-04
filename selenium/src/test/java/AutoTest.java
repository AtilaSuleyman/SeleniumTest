import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Administrator on 04/09/2017.
 */
public class AutoTest {

    WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        //Closes the webpage and processes
        driver.quit();
    }

    @Test
    public void testOne(){
        driver.navigate().to("http://www.qa.com");
    }
}
