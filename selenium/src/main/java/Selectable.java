import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Administrator on 05/09/2017.
 */
public class Selectable {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[1]")
    WebElement firstItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[2]")
    WebElement secondItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[3]")
    WebElement thirdItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[4]")
    WebElement fourthItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[5]")
    WebElement fifthItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[6]")
    WebElement sixthItem;

    @FindBy(xpath = "//*[@id=\"selectable\"]/li[7]")
    WebElement seventhItem;

    @FindBy(id = "selectable")
    WebElement selectable;

    public Selectable(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
    }

    public WebElement getSelectable() {return selectable;}

    public WebElement getFirstItem() {
        return firstItem;
    }

    public WebElement getSecondItem() {
        return secondItem;
    }

    public WebElement getThirdItem() {
        return thirdItem;
    }

    public WebElement getFourthItem() {
        return fourthItem;
    }

    public WebElement getFifthItem() {
        return fifthItem;
    }

    public WebElement getSixthItem() {
        return sixthItem;
    }

    public WebElement getSeventhItem() {
        return seventhItem;
    }

    public List<WebElement> getDescendants(){
        List<WebElement> childs = selectable.findElements(By.className("ui-selectee"));
        return childs;
    }
}
