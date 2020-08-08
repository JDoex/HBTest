package selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.URL;

public class driverSelect {

    public static String browser = System.getProperty("browser");
    public  EventFiringWebDriver driver;
    private static String baseUrl = System.getProperty("baseUrl");
    //Downloads the latest version of the desired driver.
    public static void setUpDriver(){

        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().clearPreferences().clearCache();
            WebDriverManager.chromedriver().version("76.0.3809.126").setup();
        }else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().forceDownload().clearPreferences().clearCache();
            WebDriverManager.firefoxdriver().setup();
        }
    }

    public EventFiringWebDriver initDriver() throws Exception {
        boolean isChrome = browser.equalsIgnoreCase("chrome");
        boolean isFireFox = browser.equalsIgnoreCase("firefox");
        ChromeOptions options = new ChromeOptions();
        FirefoxOptions ffoptions = new FirefoxOptions();

        if (isChrome)
        { driver = new EventFiringWebDriver(new ChromeDriver(options));}
                else if (isFireFox) {
            driver = new EventFiringWebDriver(new FirefoxDriver(ffoptions));
        }

        return driver;
    }
}
