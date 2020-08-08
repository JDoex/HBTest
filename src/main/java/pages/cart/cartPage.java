package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class cartPage extends BasePage {

    public cartPage(WebDriver driver) {super(driver);}

    By bookName = By.xpath("//*[@id='form-item-list']/ul/li/div/div[1]/h4/a");
    By price = By.cssSelector(".price:nth-child(2) > span");
    By totalPrice = By.cssSelector(".list-item:nth-child(1) strong");
    By applyCart = By.xpath(".//span[text()='Alışverişi Tamamla']");

    public String getBookName (){
        String book = getText(bookName);
        return book;
    }

    public String getPrice (){
        String bookPrice = getText(price).replace(" TL", "");
        return bookPrice;
    }

    public String getTotalPrice (){
        String total = getText(totalPrice);
        total.replace(" TL", "");
        return total;
    }

    public void clickApplyCart(){
        waitClickable(applyCart);
        click(applyCart);
    }

}
