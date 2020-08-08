package pages.itemDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class itemDetailsPage extends BasePage {
    public itemDetailsPage(WebDriver driver) { super(driver); }

    By itemName = By.id("product-name");
    By addToCart = By.id("addToCart");
    By priceBeforePoint = By.xpath("//*[@id='offering-price']/span[1]");
    By priceAfterPoint = By.xpath("//*[@id='offering-price']/span[2]");
    By cartConfirmationText = By.cssSelector(".SalesFrontCashProductInfo-3jPka > div");
    By closeModalWindow = By.cssSelector(".SalesFrontCash-3zIbr");
    By bookName = By.id("product-name");

    By shoppingChart = By.id("shoppingCart");


    public void addToChart() {
        waitPageStable();
        click(addToCart);
    }

    public String getConfirmationText(){
        waitVisibility(cartConfirmationText);
        String text = getText(cartConfirmationText);
        return text;
    }

    public void closeModalWindow() {
        waitVisibility(closeModalWindow);
        click(closeModalWindow);
    }
    public String getBook (){
        String book = getText(bookName);
        return book;
    }

    public String getBookPrice(){

        String bookPrice = (getText(priceBeforePoint)+","+getText(priceAfterPoint));
        return bookPrice;
    }

    public void goShoppingChart(){
        waitVisibility(shoppingChart);
        click(shoppingChart);
    }

}

