package cancelOrders;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.orders.ordersPage;

public class cancelOrdersTests extends BaseTests {
    private pages.orders.ordersPage ordersPage;
    @Test(description = "Adds item to the cart")
    public void cancelOrder() throws InterruptedException {
        ordersPage = new ordersPage(driver);
        commonPage.verifyString("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", driver.getTitle());

        //wwww.hepsiburada.com adresine login olunur.
        commonPage.hoverLoginButton();
        commonPage.clickLogin();

        //Login form doldurulur
        commonPage.verifyString("Üye Giriş Sayfası & Üye Ol - Hepsiburada", driver.getTitle());
        loginPage.fillTheForm();
        loginPage.submitForm();
        commonPage.waitPage();

        //Siparişlerim sayfasına girilir.
        commonPage.clickMyAccount();
        commonPage.clickMyOrders();

        //Sipariş seçilerek iptal edilir.
        ordersPage.waitPage();
        commonPage.verifyString("Siparişlerim - Hepsiburada", driver.getTitle());
        ordersPage.clickOrder();
        ordersPage.cancelOrder();
        ordersPage.selectFromDropDown();
        ordersPage.applyCancellation();
        ordersPage.waitCancellation();
        commonPage.verifyString("Ürün iptal edildi", ordersPage.getText());
    }
}
