import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 05/09/2017.
 */
public class Resizeable {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"resizable\"]/div[3]")
    WebElement lineToDrag;

    public Resizeable(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
    }

    public WebElement getLineToDrag() {
        return lineToDrag;
    }

}
