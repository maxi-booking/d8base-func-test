package pages;

import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static config.TestData.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Booking {
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
            String firstName,
            String lastName,
            String serviceName,
            String servicePrice) {
        $("app-search-result").$("ion-card-content").$("app-professional-card")
                .shouldHave(text(firstName), text(lastName));
        $("app-search-result").$("ion-card-content").$("app-service-link").shouldHave(text(serviceName));
        String servicePriceActual = $("app-search-result").$("ion-card-content").$("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            fail();
        }
    }

    @Step("Select a service")
    public void chooseService() {
        Attach.screenshotAs("Screenshot");
        $("app-search-result").$("ion-card-content").$("app-service-link").$("a").click();
    }

    @Step("Verify that the service data is correct")
    public void verifyServiceBase(
            String serviceName,
            String servicePrice,
            String serviceDuration,
            String firstName,
            String lastName,
            String serviceDescription) {
        $("app-service-widget").$("app-service-title").shouldHave(text(serviceName));
        String servicePriceActual = $("app-service-widget").$("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            fail();
        }
        $("app-service-widget").$("app-duration-viewer").shouldHave(text(serviceDuration));
        $("app-service-widget").$("app-professional-card")
                .shouldHave(text(firstName), text(lastName));
        $("app-service-widget").shouldHave(text(serviceDescription));
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

    @Step("Click the 'Order' button to book")
    public void clickOrder() {
        sleep(500);
        $("#pick-service").scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Pick the date from calendar - ({date})")
    public void pickTheDate(String date) {
        if ($("app-book").$("div.calendar").$(withText(date)).has(cssClass("unavailable"))) {
            $("app-book").$("div.calendar", 1).$(withText(date)).scrollIntoView(true).click();
        } else if ($("app-book").$("div.calendar", 1).$(withText(date)).has(cssClass("unavailable"))) {
            $("app-book").$("div.calendar", 2).$(withText(date)).scrollIntoView(true).click();
        } else {
            $("app-book").$("div.calendar").$(withText(date)).scrollIntoView(true).click();
        }
        sleep(200);
        $("app-book").$(byText("Accept and continue")).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Select previous day")
    public void clickPreviousDay() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button",0).click();
        sleep(200);
    }

    @Step("Select next day")
    public void clickNextDay() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button",1).click();
        sleep(200);
    }

    @Step("Verify previous day button inactive")
    public void verifyPreviousDayInactive() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button",0).shouldHave(cssClass("button-disabled"));
        sleep(200);
    }

    @Step("Verify previous day button active")
    public void verifyPreviousDayActive() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button",0).shouldNotHave(cssClass("button-disabled"));
        sleep(200);
    }


    @Step("Pick booking time")
    public void bookTime(int value) {
        String hours = String.valueOf(value).substring(0, 2);
        String minutes = String.valueOf(value).substring(2);
        int hoursInt = Integer.parseInt(hours);
        String time = "";
        if ($("app-time-step").$("main").$(withText("AM")).exists() && $("app-time-step").$("main").$(withText("PM")).exists()) {
            if (hoursInt < 12) {
                time = hours + ":" + minutes + " AM";
            }
            if (hoursInt == 12) {
                time = hours + ":" + minutes + " PM";
            }
            if (hoursInt > 12) {
                hoursInt = hoursInt - 12;
                hours = String.valueOf(hoursInt);
                time = hours + ":" + minutes + " PM";
            }
        } else {
            time = hours + ":" + minutes;
        }
        sleep(500);
        $("app-time-step").$(withText(time)).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Click the 'Accept and continue' button")
    public void clickForward() {
        sleep(500);
        $("app-time-step").$(byText("Accept and continue")).scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Click the 'Accept and continue' button")
    public void clickAccept() {
        sleep(500);
        $("app-confirmation-step").$(byText("Accept and continue")).scrollIntoView(true).click();
        sleep(500);
    }


    @Step("Click 'Order for another person")
    public void clickOrderForAnotherPerson() {
        sleep(200);
        $("app-client-details-step").$("form").$("ion-checkbox").scrollIntoView(true).click();
    }

    @Step("Order for another person: name - {value}")
    public void fillOrderForAPName(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='text']",0).scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: surname - {value}")
    public void fillOrderForAPSurname(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='text']",1).scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: email - {value}")
    public void fillOrderForAPEmail(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='email']").scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: phone - {country} {number}")
    public void fillOrderForAPPhoneNumber(String country, String number) {
        $("app-client-details-step").$("form").$("section").$("ionic-selectable").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(country);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item", 0).click();
        $("app-client-details-step input[inputmode='decimal']").setValue(number);
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
        $("app-confirmation-step").$("section").$("ion-item", 1).click();
    }

    @Step("Fill E-Mail")
    public void fillEmail(String Email) {
        $("app-order").$("app-client-identification").$("input").sendKeys(Email);
        $("app-order").$("app-client-identification").$("ion-button[type='submit']").click();
    }

    @Step("Fill the form")
    public void fillFrom(String Password, String Country, String FirstName, String LastName) {
        $("app-order").$("app-client-identification").$("input[type='password']", 0).sendKeys(Password);
        $("app-order").$("app-client-identification").$("input[type='password']", 1).sendKeys(Password);

        $("app-country-selector").$("button").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(Country);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();

        $("app-order").$("app-client-identification").$("form").$("input[type='text']", 0).sendKeys(FirstName);
        $("app-order").$("app-client-identification").$("form").$("input[type='text']", 1).sendKeys(LastName);
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
        Attach.screenshotAs("Screenshot");
        $("app-client-details-step").$(byText("Accept and continue")).click();
        sleep(2000);
    }

    @Step("Click to see order details")
    public void showOrderDetails() {
        $("app-sent-order-page").$("div div a").click();
    }

    @Step("Verify the order details")
    public void verifyOrderDetails(String ServiceName) {
        sleep(1000);
        $("app-sent-order-page").$("section").shouldHave(text(ServiceName));
    }

    @Step("Click Orders button")
    public void clickOrders() {
        $("app-sent-order-page").$("ion-toolbar").$("ion-item.my-orders").click();
    }
}