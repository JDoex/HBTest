package buyABook;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.cart.cartPage;
import pages.common.commonPage;
import pages.deliveryInfo.deliveryInfoPage;
import pages.itemDetails.itemDetailsPage;
import pages.itemListing.itemListingPage;
import pages.login.loginPage;
import pages.orderSummary.orderSummaryPage;
import pages.paymentInfo.paymentInfoPage;

import static org.hamcrest.CoreMatchers.is;

public class buyABookTests extends BaseTests {
    private loginPage loginPage;
    private itemListingPage itemListingPage;
    private itemDetailsPage itemDetailsPage;
    private commonPage commonPage;
    private cartPage cartPage;
    private deliveryInfoPage deliveryInfoPage;
    private paymentInfoPage paymentInfoPage;
    private orderSummaryPage orderSummaryPage;


    @Test(description = "Adds item to the cart")
    public void buyABookWithMoneyTransfer() {
        commonPage = new commonPage(driver);
        itemListingPage = new itemListingPage(driver);
        itemDetailsPage = new itemDetailsPage(driver);
        loginPage = new loginPage(driver);
        cartPage = new cartPage(driver);
        deliveryInfoPage = new deliveryInfoPage(driver);
        paymentInfoPage = new paymentInfoPage(driver);
        orderSummaryPage = new orderSummaryPage(driver);

        commonPage.verifyString("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", driver.getTitle());

        //wwww.hepsiburada.com adresine login olunur.
        commonPage.hoverLoginButton();
        commonPage.clickLogin();

        //Login form doldurulur
        commonPage.verifyString("Üye Giriş Sayfası & Üye Ol - Hepsiburada", driver.getTitle());
        loginPage.fillTheForm();
        loginPage.submitForm();
        commonPage.waitPage();

        //Edebiyat kategorisi seçilir
        commonPage.verifyString("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", driver.getTitle());
        commonPage.navigateBooks();

        //En çok satan kitaplar listelenir.
        commonPage.verifyString("Edebiyat Kitapları Satın Al & 6 Taksit & Hızlı Kargo", driver.getTitle());
        itemListingPage.clickBestSellers();
        String booksName = itemListingPage.getBooksName();
        itemListingPage.pickABook();

        //Açılan sayfada doğru kitabın getirildiği kontrol edilir.
        commonPage.verifyStringContains(driver.getTitle(), booksName);
        String getBookName = itemDetailsPage.getBook();
        commonPage.verifyStringContains(getBookName, booksName);

        //Ürün eklenmeden önce sepetteki ürün sayısı kontrol edilir.
        int initItemCount = commonPage.getChartItemCount();

        //Ürünün fiyatı not edilir.
        String bookPrice = itemDetailsPage.getBookPrice();

        //Seçilen kitap sepete eklenir.
        itemDetailsPage.addToChart();

        //Ürünün sepete eklendiği kontrol edilir.
        String confirmationText = itemDetailsPage.getConfirmationText();
        commonPage.verifyString("Ürün sepetinizde", confirmationText);
        itemDetailsPage.closeModalWindow();

        //Sepette bulunan ürün sayısının arttığı kontrol edilir.
        int currentItemCount = commonPage.getChartItemCount();
        commonPage.verifyString(String.valueOf(initItemCount + 1), String.valueOf(currentItemCount));
        itemDetailsPage.goShoppingChart();

        //Sepetteki ürün bilgileri kontrol edilir
        commonPage.verifyString("Sepetim", driver.getTitle());
        String bookAtCart = cartPage.getBookName();
        String price = cartPage.getPrice();
        String totalPrice = cartPage.getTotalPrice();
        commonPage.verifyString(bookAtCart, booksName);
        commonPage.verifyString(price, bookPrice);
        commonPage.verifyString(totalPrice, bookPrice);
        cartPage.clickApplyCart();

        //Teslimat sayfasına devam edilir.
        deliveryInfoPage.waitPage();
        commonPage.verifyString("Teslimat Bilgileri", driver.getTitle());
        deliveryInfoPage.clickNext();

        //Ödeme yöntemi havale olarak seçilerek devam edilir.
        paymentInfoPage.waitPage();
        commonPage.verifyString("Ödeme Bilgileri", driver.getTitle());
        paymentInfoPage.clickTransfer();
        paymentInfoPage.selectBank();
        paymentInfoPage.clickNextButton();
        paymentInfoPage.clickTransferAfter();
        paymentInfoPage.clickNext();

        //Sipariş özeti sayfası
        orderSummaryPage.waitPage();
        commonPage.verifyString("Sipariş Özeti", driver.getTitle());
        String bookAtSummary = orderSummaryPage.getBookName();
        String priceAtSummary = orderSummaryPage.getPrice();
        commonPage.verifyString(bookAtSummary, booksName);
        commonPage.verifyString(priceAtSummary, bookPrice);

        //Siparişi Tamamla
        orderSummaryPage.clickagreeCheckBox();
        orderSummaryPage.clickApplyButton();
        commonPage.waitPage();
        commonPage.verifyString("Sipariş Tamamlandı", driver.getTitle());


    }
}
