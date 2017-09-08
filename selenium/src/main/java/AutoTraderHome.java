import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 08/09/2017.
 */
public class AutoTraderHome {

    WebDriver driver;

    //for seacrhing for cars
    @FindBy(xpath = "//*[@id=\"postcode\"]")
    WebElement postcodeBox;

    @FindBy(xpath = "//*[@id=\"searchVehiclesMake\"]")
    WebElement searchMakeBox;

    @FindBy(xpath = "//*[@id=\"searchVehiclesModel\"]")
    WebElement carMake;

    @FindBy(xpath = "//*[@id=\"search\"]")
    WebElement searchForCars;

    //for finding new car
    @FindBy(xpath = "//*[@id=\"js-header-nav\"]/ul/li[1]/a")
    WebElement buyNewUsedCarButton;

    @FindBy(xpath = "//*[@id=\"top-nav__buying\"]/li[2]/a")
    WebElement viewNewCars;

    @FindBy(xpath = "//*[@id=\"js-header-nav\"]/ul/li[3]/a")
    WebElement carReviewsButton;

    @FindBy(xpath = "//*[@id=\"top-nav__reviews\"]/li[2]/a")
    WebElement ownerReviewsButton;

    public AutoTraderHome(WebDriver driver){
        this.driver = driver;
        //Initialise web elements for a certain driver
        PageFactory.initElements(driver, this);
    }

    public WebElement getPostcodeBox() {
        return postcodeBox;
    }

    public WebElement getSearchMakeBox() {
        return searchMakeBox;
    }

    public WebElement getCarMake() {
        return carMake;
    }

    public WebElement getSearchForCars() {
        return searchForCars;
    }

    public WebElement getBuyNewUsedCarButton() {
        return buyNewUsedCarButton;
    }

    public WebElement getViewNewCars() {
        return viewNewCars;
    }

    public WebElement getCarReviewsButton() {
        return carReviewsButton;
    }

    public WebElement getOwnerReviewsButton() {
        return ownerReviewsButton;
    }
}
