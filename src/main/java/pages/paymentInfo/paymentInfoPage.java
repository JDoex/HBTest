package pages.paymentInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class paymentInfoPage extends BasePage {
    public paymentInfoPage(WebDriver driver) {
        super(driver);
    }

    By transfer = By.xpath(".//span[text()='Havale']");
    By nextButton = By.xpath(".//span[text()='Devam Et']");
    By next = By.xpath(".//button[text()='Devam Et']");
    By selectBank = By.cssSelector(".money-order-banks > .list-item:nth-child(1)");
    By transferAfter = By.cssSelector((".iban-eft"));

    public void waitPage() {
        waitClickable(transfer);
    }

    public void clickTransfer() {
        click(transfer);
    }

    public void selectBank() {
        click(selectBank);
    }

    public void clickNextButton() {
        click(nextButton);
    }

    public void clickNext() {
        click(next);
    }

    public void clickTransferAfter() {
        click(transferAfter);
    }

}
