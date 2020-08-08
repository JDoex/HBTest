package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import utils.random.matcher.Matcher;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class commonPage extends BasePage {

    By loginButton = By.cssSelector(".OldMyAccount-2BwAt:nth-child(1)");
    By login = By.id("login");
    By books = By.xpath(".//span[text()='Kitap, Müzik, Film, Hobi']");
    By literature =  By.xpath(".//a[text()='Edebiyat']");
    By chartItemCount = By.id("cartItemCount");
    By logo = By.id("logo");
    By myAccount = By.xpath(".//span[text()='Hesabım']");
    By myOrders = By.cssSelector(".OldMyAccount-1H-JV li:nth-child(1) > a");


    public commonPage(WebDriver driver) {
        super(driver);
    }

    public void hoverLoginButton(){
        hoverOver(loginButton);
    }

    public void clickLogin(){
        click(login);
    }

    public void waitPage (){waitVisibility(chartItemCount);}

    public void verifyString(String text1, String text2){

        Matcher.assertThat("Text ", text1, is(text2));
    }

    public void verifyStringContains(String text1, String text2){
        Matcher.assertThat("Text ", text1, containsString(text2));
    }

    public void navigateBooks(){
        click(books);
        click(literature);
    }

    public int getChartItemCount() {
        waitVisibility(chartItemCount);
        int itemCount = Integer.parseInt(getText(chartItemCount));
        return itemCount;
    }

    public void clickMyAccount(){
        click(myAccount);
    }

    public void clickMyOrders(){
        waitVisibility(myOrders);
        driver.findElement(myOrders).click();
    }

}
