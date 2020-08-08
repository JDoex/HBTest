package helpers.js;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface JSExecuter
{
    <T> T executeScript(Class<T> clazz, String script, Object... args);

    Long getPageHeight();

    String randomGenerate(int digit);

    Number randomNumberGenerate(int digit);

    String randomStringGenerate(int digit);

    JSExecuter openNewTab(String links);

    JSExecuter doubleClick(WebElement element);

    JSExecuter navigateToUrl(String links);

    JSExecuter clickElement(WebElement element);

    JSExecuter click(By by);

    String getText(WebElement element);

    JSExecuter selectByIndex(WebElement element, Integer index);

    JSExecuter selectByValue(WebElement element, String value);

    JSExecuter sendKeys(WebElement element, String value);

    Boolean isDisplayed(WebElement element);

    JSExecuter clear(WebElement element);

    String getSelectedOptionText(WebElement element);

    Long getSelectedOptionIndex(WebElement element);

    String getSelectedOptionValue(WebElement element);

    JSExecuter scrollToElement(WebElement element);

    Boolean isAttributePresent(WebElement element, String attribute);

    String getAttribute(WebElement element, String attribute);

    Boolean checkBoxChecked(WebElement element);

    JSExecuter allCheckBoxChecked();

    JSExecuter pageZoom(Integer percentValue);

    JSExecuter pageRefresh();

    String getCurrentUrl();

    String getLocationPathName();

    JSExecuter dragAndDrop(WebElement elementFrom, WebElement elementTo);

    JSExecuter hoverOver(WebElement element);

    JSExecuter containsText(WebElement element, String text);

    Boolean isRadioButtonChecked(WebElement element);

    Boolean isRadioButtonChecked(By by);

    String getText(By by);

    String getValue(By by);

    Boolean isHoverOver(By by);
}