package pages.base;
import helpers.js.JSUtil;
import helpers.js.JSWaiter;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.driverSelect;
import java.util.List;

public class BasePage {

    private static final int TIMEOUT = 30;
    private static final int POLLING = 500;
    private static String browser = System.getProperty("browser");
    public static WebDriver driver;
    private static String baseUrl = System.getProperty("baseUrl");
    protected WebDriverWait wait;
    protected JSUtil jsUtil;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);

    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    protected List<WebElement> waitVisibility(By elementBy) {
        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    protected void hoverOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected void hoverOver(By elementBy) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(elementBy)).perform();
    }

    protected void click(By elementBy) {
        waitClickable(elementBy);
        driver.findElement(elementBy).click();
    }

    protected void click(WebElement element) {
        waitClickable(element);
        element.click();
    }

    public void jsClick(By element){
        WebElement webElement = driver.findElement(element);

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", webElement);

    }

    protected void checkBox(By elementBy) {
        waitPresence(elementBy);
        driver.findElement(elementBy).sendKeys(Keys.SPACE);
    }



    protected void waitClickable(WebElement element) {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitClickable(By elementBy) {
        wait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    protected void writeText(By elementBy, String text) {
        waitPresence(elementBy);

                driver.findElement(elementBy).click();
                driver.findElement(elementBy).clear();
                driver.findElement(elementBy).sendKeys(text);
    }



    protected List<WebElement> waitPresence(By elementBy) {

        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
    }

    public void waitPageStable(){
        JSWaiter.builder().build().waitAllRequest();
    }

    protected String getText(By elementBy) {
        waitPageStable();
        String text = driver.findElement(elementBy).getText().trim();
        return text;
    }

}
