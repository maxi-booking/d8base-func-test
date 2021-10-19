package pages;

import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Booking {

    @Step("Click Search button")
    public void clickSearchEN() {
        $("app-main-menu").$(byText("Search")).click();
        sleep(300);
    }

    @Step("Close default filters")
    public void closeFilters() {
        $("app-applied-filters").$("ion-chip").click();
        sleep(1000);
    }

    @Step("Input search text and press Enter")
    public void findService(String searchQuery) {
        sleep(1000);
        $("app-search").$("form").$("input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("app-search").$("form").$("input").pressEnter();
        Attach.screenshotAs("Screenshot");
        sleep(500);
    }

    @Step("Main page, input search text and press Enter")
    public void findServiceMainPage(String searchQuery) {
        sleep(1000);
        $("ion-searchbar").$("input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("ion-searchbar").$("input").pressEnter();
        Attach.screenshotAs("Screenshot");
        sleep(500);
    }


    @Step("Verify that the search result is correct")
    public void verifyServiceSearch(
            String FirstName,
            String LastName,
            String ServiceName,
            String ServicePrice) {
        $("app-search-result").$("ion-card-content").$("app-professional-card")
                .shouldHave(text(FirstName), text(LastName));
        $("app-search-result").$("ion-card-content").$("app-service-link").shouldHave(text(ServiceName));
        $("app-search-result").$("ion-card-content").$("app-price").shouldHave(text(ServicePrice));
    }

    @Step("Select a service")
    public void chooseService() {
        $("app-search-result").$("ion-card-content").$("app-service-link").$("a").click();
    }

    @Step("Verify that the service data is correct")
    public void verifyServiceBase(
            String ServiceName,
            String ServicePrice,
            String ServiceDuration,
            String FirstName,
            String LastName,
            String ServiceDescription) {
        $("app-service-widget").$("app-service-title").shouldHave(text(ServiceName));
        $("app-service-widget").$("app-price").shouldHave(text(ServicePrice));
        $("app-service-widget").$("app-duration-viewer").shouldHave(text(ServiceDuration));
        $("app-service-widget").$("app-professional-card")
                .shouldHave(text(FirstName), text(LastName));
        $("app-service-widget").shouldHave(text(ServiceDescription));
    }

    @Step("Verify service location")
    public void verifyServiceLocation(String ServiceLocation) {
        $("app-service-widget").$("app-service-location").shouldHave(text(ServiceLocation));
    }

    @Step("Verify service country/city/address")
    public void verifyServiceGeo(String ServiceCountry, String ServiceCity, String ServiceAddress) {
        $("app-service-widget").$("app-service-location")
                .shouldHave(text(ServiceCountry), text(ServiceCity), text(ServiceAddress));
    }

    @Step("Check if payment by cash")
    public void verifyServicePaymentCash() {
        $("app-service-widget").$("app-payment-method-viewer").shouldHave(text("Cash"));
    }

    @Step("Check if payment online")
    public void verifyServicePaymentOnline() {
        sleep(400);
        $("app-service-widget").$("app-payment-method-viewer").shouldHave(text("Online payment"));
    }

    @Step("Check instant booking")
    public void verifyInstantBooking() {
        $("app-service-widget").shouldHave(text("Ordering of this service will be approved automatically"));
    }

    @Step("Click the 'Date' button to book")
    public void clickDate() {
        sleep(500);
        $(byText("Order")).click();
        sleep(500);
    }

    @Step("Select the next day")
    public void clickNextDay() {
        sleep(200);
        $("app-calendar-component").$("ion-item").$("[slot=end]").click();
        sleep(200);
    }

    @Step("Pick booking time")
    public void bookTime() {
        sleep(500);
        $("app-calendar-component").$(withText("11:00")).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Click the 'Accept and continue' button")
    public void clickForward() {
        sleep(500);
        $("app-date-time-step").$(byText("Accept and continue")).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Click the 'Accept and continue' button")
    public void clickAccept() {
        sleep(500);
        $("app-confirmation-step").$(byText("Accept and continue")).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Select address")
    public void selectAddress() {
        sleep(200);
        $("app-client-details-step").$("ion-radio-group").$("ion-item").click();
    }

    @Step("Select 'Book this for me' option")
    public void bookForMe() {
        $("app-client-details-step").$("ion-checkbox").click();
        $("app-client-details-step").$(byText("Accept and continue")).click();
    }

    @Step("app-order")
    public void selectNewUser() {
        $("app-order").$("section").$("ion-item",1).click();
    }

    @Step("Fill E-Mail")
    public void fillEmail(String Email) {
        $("app-order").$("app-client-identification").$("input").sendKeys(Email);
        $("app-order").$("app-client-identification").$("ion-button[type='submit']").click();
    }

    @Step("Fill the form")
    public void fillFrom(String Password, String Country, String FirstName, String LastName) {
        $("app-order").$("app-client-identification").$("input[type='password']",0).sendKeys(Password);
        $("app-order").$("app-client-identification").$("input[type='password']",1).sendKeys(Password);

        $("app-country-selector").$("button").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(Country);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();

        $("app-order").$("app-client-identification").$("form").$("input[type='text']",0).sendKeys(FirstName);
        $("app-order").$("app-client-identification").$("form").$("input[type='text']",1).sendKeys(LastName);
        $("app-order").$("app-client-identification").$("ion-button[type='submit']").click();
    }

    @Step("Select service location")
    public void chooseServiceLocation() {
        sleep(200);
        $("app-location-step").$("ion-radio-group").$("ion-item").click();
        sleep(200);
        $("app-location-step").$(byText("Forward")).click();
    }

    @Step("Place the order")
    public void placeOrder() {
        sleep(1000);
        $("app-order").$(byText("Place order")).click();
        sleep(2000);
    }

    @Step("Verify the order details")
    public void verifyOrderDetails(String ServiceName, String Price, String Duration, String FirstName, String LastName) {
        sleep(1000);
        $("app-sent-order-page").$("main").$("app-service-title").shouldHave(text(ServiceName));
        $("app-sent-order-page").$("main").$("app-price").shouldHave(text(Price));
        $("app-sent-order-page").$("main").$("app-duration-viewer").shouldHave(text(Duration));
        $("app-sent-order-page").$("main").shouldHave(text(FirstName+" "+LastName));
    }

    @Step("Click Orders button")
    public void clickOrders() {
        $("app-sent-order-page").$("ion-toolbar").$("ion-item",2).click();
    }
}
