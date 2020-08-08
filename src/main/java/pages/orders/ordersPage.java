package pages.orders;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class ordersPage extends BasePage {

    public ordersPage(WebDriver driver) {
        super(driver);
    }

    By order = By.cssSelector(".order-number > small");
    By cancelOrder = By.cssSelector(".hb-button--secondary");
    By dropdown = By.cssSelector(".hb-form-selectbox:nth-child(3) > .selectbox-placeholder");
    By option = By.cssSelector("li:nth-child(7)");
    By cancelButton = By.cssSelector(".hb-button--primary:nth-child(1)");
    By confirmCancellation = By.xpath(".//span[text()='Ürün iptal edildi']");
    By closeButton = By.cssSelector(".alert-inner-bar__close-button");




    public void waitPage() {
        waitClickable(order);
    }

    public void waitCancellation(){
        waitVisibility(closeButton);
    }
    public void clickOrder() {
        click(order);
    }

    public void cancelOrder() throws InterruptedException {
        Thread.sleep(1000);
        click(cancelOrder);
    }

    public void selectFromDropDown() {
        click(dropdown);
        click(option);
    }

    public void applyCancellation() {
        jsClick(cancelButton);
    }

    public String getText(){
        String text = getText(confirmCancellation);
        return text;
    }

    public void closeModal(){
        click(closeButton);
    }

}
