import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 07/09/2017.
 */
public class ShoppingPage {

    WebDriver driver;

    //For signing in...
    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"email\"]")
    WebElement emailAddressInput;

    @FindBy(xpath = "//*[@id=\"passwd\"]")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"SubmitLogin\"]")
    WebElement submitLogin;

    //For adding to basket...
    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img")
    WebElement clothesImage;

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    WebElement addToBasket;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    WebElement proceedToBasket;

    @FindBy(xpath = "//*[@id=\"cmsinfo_block\"]/div[1]/ul/li[2]/div/p/a")
    WebElement button;

    @FindBy(xpath = "//*[@id=\"contact-link\"]/a")
    WebElement contactUs;

    @FindBy(xpath = "//*[@id=\"id_contact\"]")
    WebElement selectionBox;

    @FindBy(xpath = "//*[@id=\"email\"]")
    WebElement emailBox;

    public ShoppingPage(WebDriver driver){
        this.driver = driver;
        //Initialise web elements for a certain driver
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getEmailAddressInput() {
        return emailAddressInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSubmitLogin() {
        return submitLogin;
    }

    public WebElement getClothesImage() {
        return clothesImage;
    }

    public WebElement getAddToBasket() {
        return addToBasket;
    }

    public WebElement getProceedToBasket() {
        return proceedToBasket;
    }

    public WebElement getButton() {
        return button;
    }

    public WebElement getContactUs() {
        return contactUs;
    }

    public WebElement getSelectionBox() {
        return selectionBox;
    }

    public WebElement getEmailBox() {
        return emailBox;
    }
}
