package pages.itemListing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class itemListingPage extends BasePage {

    public itemListingPage(WebDriver driver) {super(driver);}

    By bestSellers = By.xpath(".//a[@sorttag='coksatan']");
    By booksName = By.cssSelector(".search-item:nth-child(2) .product-title span");

    public void clickBestSellers(){
        waitPageStable();
        click(bestSellers);
    }

    public String getBooksName(){
        String text = getText(booksName);
        return text;
    }

    public void pickABook(){
        waitPageStable();
        click(booksName);
    }

}
