package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class loginPage extends BasePage {

    public loginPage(WebDriver driver) {super(driver);}


    By eMail = By.name("username");
    By password = By.name("password");
    By submitButton = By.name("btnLogin");



    public void fillTheForm(){
        writeText(eMail,System.getProperty("email"));
        writeText(password, System.getProperty("password"));
    }

    public void submitForm(){
        click(submitButton);
        waitPageStable();
    }



}
