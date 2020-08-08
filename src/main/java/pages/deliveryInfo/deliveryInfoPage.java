package pages.deliveryInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class deliveryInfoPage extends BasePage {

    public deliveryInfoPage(WebDriver driver) {super(driver); }

    By nextButton = By.xpath(".//span[text()='Devam Et']");

    public void clickNext() {

        click(nextButton);
    }
    public void waitPage (){waitClickable(nextButton);}


}
