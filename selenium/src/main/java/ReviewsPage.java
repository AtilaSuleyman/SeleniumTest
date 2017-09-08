import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 08/09/2017.
 */
public class ReviewsPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"reviewTypeOwnerMake\"]")
    WebElement makeSelector;

    @FindBy(xpath = "//*[@id=\"reviewTypeOwnerModel\"]")
    WebElement modelSelector;

    @FindBy(xpath = "//*[@id=\"reviewTypeOwnerBodytype\"]")
    WebElement bodyTypeSelector;

    @FindBy(xpath = "//*[@id=\"findReviews\"]")
    WebElement searchReviewsButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[1]/div[1]/h1")
    WebElement carReviewsName;

    public ReviewsPage(WebDriver driver){
        this.driver = driver;
        //Initialise web elements for a certain driver
        PageFactory.initElements(driver, this);
    }

    public WebElement getMakeSelector() {
        return makeSelector;
    }

    public WebElement getModelSelector() {
        return modelSelector;
    }

    public WebElement getBodyTypeSelector() {
        return bodyTypeSelector;
    }

    public WebElement getSearchReviewsButton() {
        return searchReviewsButton;
    }

    public WebElement getCarReviewsName() {
        return carReviewsName;
    }
}
