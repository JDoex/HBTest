package base;

import helpers.js.JSWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.common.commonPage;

import pages.login.loginPage;
import selenium.driverSelect;

import java.util.concurrent.TimeUnit;

//@Listeners({TestMethodCapture.class, TestListener.class})

public class BaseTests {
    protected EventFiringWebDriver driver;
    protected pages.common.commonPage commonPage;
    protected loginPage loginPage;
    private static String baseUrl = System.getProperty("baseUrl");
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        killDrivers();
        driverSelect driverSelect = new driverSelect();
        driverSelect.setUpDriver();
    }

    @AfterSuite
    public void afterSuite() {
//        killChromeDrivers();
    }

    @BeforeClass
    public void setUp(ITestContext testContext) throws Exception {
        //        String methodName = TestMethodCapture.getTestMethod().getMethodName();
        String name = this.getClass().getName();

        driverSelect driverSelect = new driverSelect();
        driver = driverSelect.initDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
        JSWaiter.setDriver(driver);
        driver.get(baseUrl);

    }

    @AfterClass
    public void tearDown(ITestContext testContext) throws Exception {

        driver.close();
        driver.quit();
        MDC.remove("fileName");
    }

    @BeforeMethod
    public void beforeEachMethod() {
        commonPage = new commonPage(driver);
        loginPage = new loginPage(driver);

    }


    private void killDrivers() {
        String os = System.getProperty("os.name").toLowerCase();
        String cmd = "";
        try {
            if (os.contains("windows")) {
                cmd = "taskkill /F /IM chromedriver.exe /T";
                cmd = "taskkill /F /IM geckodriver.exe /T";
            } else if (os.contains("linux")) {
                cmd = "pkill chrome";
            }
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            process.destroy();
            process.waitFor();
        } catch (Exception e) {

        }
    }
}
