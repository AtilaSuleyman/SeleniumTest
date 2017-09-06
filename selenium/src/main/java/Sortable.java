import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 06/09/2017.
 */
public class Sortable {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"sortable\"]/li[1]")
    WebElement sortableBoxOne;

    @FindBy(xpath = "//*[@id=\"sortable\"]/li[7]")
    WebElement sortableBoxFour;

    public Sortable(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
    }

    public WebElement getSortableBoxOne() {
        return sortableBoxOne;
    }

    public WebElement getSortableBoxFour() {
        return sortableBoxFour;
    }
}
