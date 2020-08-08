package helpers.js;

import lombok.Builder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

@Builder
public class JSWaiter {

    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;

    //Get the driver
    public static void setDriver(WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    }

    private void ajaxComplete() {
        jsExec.executeScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    private void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) jsWaitDriver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngularLoad() {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }

    private void waitUntilJSReady() {
        try {
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWaitDriver)
                    .executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitUntilJQueryReady() {
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);
            waitForJQueryLoad();
            poll(20);
        }
    }

    public void waitUntilAngularReady() {
        long start = System.nanoTime();
        try {
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);

                    waitForAngularLoad();

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
        long finish = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(finish - start);
    }

    public void waitUntilAngular7Ready() {
        try {
            Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(20);
                    waitForAngular7Load();
//                    waitForAngular5Load();
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngular5Load() {
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript);
    }

    private void angularLoads(String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitAllRequest() {

        long start = System.nanoTime();
        try {
            waitUntilJSReady();
            ajaxComplete();
            waitUntilJQueryReady();
            waitUntilAngular7Ready();
        } catch (Exception e) {

        }
        long finish = System.nanoTime();
        long seconds = TimeUnit.NANOSECONDS.toSeconds(finish - start);

    }

    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public void waitForElementAreComplete(By by, int expected) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = jsWaitDriver.findElements(by).size();
            return loadingElements >= expected;
        };
        jsWait.until(angularLoad);
    }

    /**
     * Waits for the elements animation to be completed
     *
     * @param css
     */
    public void waitForAnimationToComplete(String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = jsWaitDriver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        jsWait.until(angularLoad);
    }

    public void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForAngular7Load() {
        String angularReadyScript = "return window.getAllAngularTestabilities()[0]._ngZone.hasPendingMicrotasks == false && window.getAllAngularTestabilities()[0]._ngZone.hasPendingMacrotasks == true && window.getAllAngularTestabilities()[0]._ngZone._nesting == 0 && window.getAllAngularTestabilities()[0]._ngZone.isStable == true";
        angular7Loads(angularReadyScript);
    }

    private void angular7Loads(String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
}