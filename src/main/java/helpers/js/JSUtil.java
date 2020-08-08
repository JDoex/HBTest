package helpers.js;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class JSUtil implements JSExecuter {
    private WebDriver driver;
    private JavascriptExecutor jExecutor;

    public JSUtil(WebDriver driver) {
        this.jExecutor = (JavascriptExecutor) driver;
        this.driver = driver;
    }

    public <T> T executeScript(Class<T> clazz, String script, Object... args) {
        //        StepLogger.info("JavaScript executed: " + script);
        return clazz.cast(jExecutor.executeScript(script, args));
    }

    @Override
    public Long getPageHeight() {
        return executeScript(Long.class, "return $(document).height()");
    }

    @Override
    public String randomGenerate(int digit) {
        return executeScript(String.class, "var text = ''; var possible = 'abcdefghijklmnopqrstuvwxyz0123456789'; for (var i = 0; i < arguments[0]; i++) text += possible.charAt(Math.floor(Math.random() * possible.length)); return text;", digit);
    }

    @Override
    public Number randomNumberGenerate(int digit) {
        return executeScript(Number.class, "return Math.floor(Math.random() * Math.floor(Math.pow(10, arguments[0])))", digit);
    }

    @Override
    public String randomStringGenerate(int digit) {
        return executeScript(String.class, "return Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, arguments[0]);", digit);
    }

    @Override
    public JSExecuter openNewTab(String links) {
        return executeScript(JSExecuter.class, "window.open(" + links + ",'_blank');");
    }

    @Override
    public JSExecuter doubleClick(WebElement element) {
        return executeScript(JSExecuter.class, "for(var i=0; i < 2; i++) {arguments[0].click();}", element);
    }

    @Override
    public JSExecuter navigateToUrl(String links) {
        return executeScript(JSExecuter.class, "window.location.href = '" + links + "';");
    }

    @Override
    public JSExecuter clickElement(WebElement element) {
        return executeScript(JSExecuter.class, "arguments[0].click();", element);
    }

    @Override
    public JSExecuter click(By by) {
        try {
            return executeScript(JSExecuter.class, "arguments[0].click();", ((WebDriver) jExecutor).findElement(by));
        } catch (StaleElementReferenceException e) {
            //            click(by);
        }
        return null;
    }

    //    @Override
    //    public JSExecuter click(By by){
    //        try {
    //            driver.findElement(by).click();
    //        }catch (Exception e){
    //
    //        }
    //        return null;
    //    }

    @Override
    public String getText(WebElement element) {
        return executeScript(String.class, "return arguments[0].textContent;", element);
    }

    @Override
    public String getText(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return executeScript(String.class, "return arguments[0].textContent;", driver.findElement(by));
    }

    @Override
    public String getValue(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return executeScript(String.class, "return arguments[0].value;", driver.findElement(by));
    }

    @Override
    public JSExecuter selectByIndex(WebElement element, Integer index) {
        return executeScript(JSExecuter.class, "arguments[0].selectedIndex = arguments[1]", element, index);
    }

    @Override
    public JSExecuter selectByValue(WebElement element, String value) {
        return executeScript(JSExecuter.class, "arguments[0].value = arguments[1]", element, value);
    }

    @Override
    public JSExecuter sendKeys(WebElement element, String value) {
        return executeScript(JSExecuter.class, "arguments[0].value = arguments[1]", element, value);
    }

    @Override
    public Boolean isDisplayed(WebElement element) {
        return executeScript(Boolean.class, "function isVisible(elem) {\n" +
                "    if (!(elem instanceof Element)) throw Error('DomUtil: elem is not an element.');\n" +
                "    const style = getComputedStyle(elem);\n" +
                "    if (style.display === 'none') return false;\n" +
                "    if (style.visibility !== 'visible') return false;\n" +
                "    if (style.opacity < 0.1) return false;\n" +
                "    if (elem.offsetWidth + elem.offsetHeight + elem.getBoundingClientRect().height +\n" +
                "        elem.getBoundingClientRect().width === 0) {\n" +
                "        return false;\n" +
                "    }\n" +
                "    const elemCenter   = {\n" +
                "        x: elem.getBoundingClientRect().left + elem.offsetWidth / 2,\n" +
                "        y: elem.getBoundingClientRect().top + elem.offsetHeight / 2\n" +
                "    };\n" +
                "    if (elemCenter.x < 0) return false;\n" +
                "    if (elemCenter.x > (document.documentElement.clientWidth || window.innerWidth)) return false;\n" +
                "    if (elemCenter.y < 0) return false;\n" +
                "    if (elemCenter.y > (document.documentElement.clientHeight || window.innerHeight)) return false;\n" +
                "    let pointContainer = document.elementFromPoint(elemCenter.x, elemCenter.y);\n" +
                "    do {\n" +
                "        if (pointContainer === elem) return true;\n" +
                "    } while (pointContainer = pointContainer.parentNode);\n" +
                "    return false;\n" +
                "}\n" +
                "return isVisible(arguments[0])", element);
    }

    @Override
    public JSExecuter clear(WebElement element) {
        return executeScript(JSExecuter.class, "arguments[0].value = ''", element);
    }

    @Override
    public String getSelectedOptionText(WebElement element) {
        return executeScript(String.class, "function getSelectedText(el) {\n" +
                "if (el.selectedIndex == -1) return null;\n" +
                "return el.options[el.selectedIndex].text;}\n" +
                "return getSelectedText(arguments[0]);", element);
    }

    public String getSelectedOptionText(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return executeScript(String.class, "function getSelectedText(el) {\n" +
                "if (el.selectedIndex == -1) return null;\n" +
                "return el.options[el.selectedIndex].text;}\n" +
                "return getSelectedText(arguments[0]);", driver.findElement(by));
    }

    @Override
    public Long getSelectedOptionIndex(WebElement element) {
        return executeScript(Long.class, "return arguments[0].selectedIndex", element);
    }

    @Override
    public String getSelectedOptionValue(WebElement element) {
        return executeScript(String.class, "return arguments[0].value", element);
    }

    @Override
    public JSExecuter scrollToElement(WebElement element) {
        return executeScript(JSExecuter.class, "arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", element);
    }

    @Override
    public Boolean isAttributePresent(WebElement element, String attribute) {
        return executeScript(Boolean.class, "var value = arguments[0].getAttribute(arguments[1]); if(value !== null) {return true} else return false;", element, attribute);
    }

    @Override
    public String getAttribute(WebElement element, String attribute) {
        return executeScript(String.class, "return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }

    @Override
    public Boolean checkBoxChecked(WebElement element) {
        return executeScript(Boolean.class, "return arguments[0].checked;", element);
    }

    @Override
    public JSExecuter allCheckBoxChecked() {
        return executeScript(JSExecuter.class, "var checkboxes = document.querySelectorAll(\"input[type='checkbox']\"); for(var i = 0; i < checkboxes.length; i++) {checkboxes[i].checked = true;}");
    }

    @Override
    public JSExecuter pageZoom(Integer percentValue) {
        return executeScript(JSExecuter.class, "document.body.style.zoom = arguments[0]", percentValue);
    }

    @Override
    public JSExecuter pageRefresh() {
        return executeScript(JSExecuter.class, "location.reload()");
    }

    @Override
    public String getCurrentUrl() {
        return executeScript(String.class, "return location.href");
    }

    @Override
    public String getLocationPathName() {
        return executeScript(String.class, "return location.pathname");
    }

    @Override
    public JSExecuter dragAndDrop(WebElement elementFrom, WebElement elementTo) {
        return executeScript(JSExecuter.class, "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", elementFrom, elementTo);
    }

    @Override
    public JSExecuter hoverOver(WebElement element) {
        return executeScript(JSExecuter.class, "arguments[0].hover();", element);
    }

    @Override
    public JSExecuter containsText(WebElement element, String text) {
        return executeScript(JSExecuter.class, "arguments[0].textContent.includes('[1]');", element, text);
    }

    @Override
    public Boolean isRadioButtonChecked(WebElement element) {
        return executeScript(Boolean.class, "return arguments[0].checked;", element);
    }

    @Override
    public Boolean isRadioButtonChecked(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return executeScript(Boolean.class, "return arguments[0].checked;", driver.findElement(by));
    }

    @Override
    public Boolean isHoverOver(By by) {
        return executeScript(Boolean.class, "return arguments[0].querySelector(':hover')", driver.findElement(by));
    }

    public Object getItemFromLocalStorage(String key){
        return executeScript(Object.class, "return window.localStorage.getItem(arguments[0]);", key);
    }

    public Object getLocalStorage(){
        return executeScript(Object.class, "return window.localStorage;");
    }

    public void setLocalStorage(String key, String value){
        executeScript(JSExecuter.class, "window.localStorage.setItem(arguments[0], arguments[1]);", key, value);
    }
}
