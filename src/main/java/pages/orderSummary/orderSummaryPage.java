package pages.orderSummary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class orderSummaryPage extends BasePage {
    public orderSummaryPage(WebDriver driver) {super(driver);}

    By summaryIcon = By.cssSelector(".summary-icon");
    By bookInfo = By.cssSelector(".col-product-name ");
    By bookPrice= By.cssSelector(".col-total > span");
    By agreeCheckbox = By.name("contractAgreed");
    By apply = By.xpath(".//span[text()='Siparişi Onayla']");

    public void waitPage (){
        waitVisibility(summaryIcon);
    }


    public String getBookName (){
        String book = getText(bookInfo);
        return book;
    }

    public String getPrice (){
        String price = getText(bookPrice).replace(" TL", "");
        return price;
    }

    public void clickagreeCheckBox(){
        checkBox(agreeCheckbox);

    }

    public void clickApplyButton(){
        click(apply);
    }

}
