import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 08/09/2017.
 */
public class NewCarsPage {

    WebDriver driver;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/ul/li[5]/div")
    WebElement coupeButton;

    public NewCarsPage(WebDriver driver){
        this.driver = driver;
        //Initialise web elements for a certain driver
        PageFactory.initElements(driver, this);
    }

    public WebElement getCoupeButton() {
        return coupeButton;
    }
}
