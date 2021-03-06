package pages;

import helpers.ServiceDuration;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.SelectableModal.selectModal;
import static helpers.ServiceDuration.getDuration;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class Booking {

    @Step("Verify that the search result is correct")
    public void verifyServiceSearch(
            String firstName,
            String lastName,
            String serviceName,
            String servicePrice) {
        $("app-search-result ion-card-content app-professional-card")
                .shouldHave(text(firstName), text(lastName));
        $("app-search-result ion-card-content app-service-link-search-card").shouldHave(text(serviceName));
        String servicePriceActual = $("app-search-result ion-card-content app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            fail();
        }
    }

    @Step("Select a service")
    public void chooseService() {
        $("app-search-result ion-card-content app-service-link-search-card a").click();
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

        ServiceDuration duration = getDuration(serviceDuration);
        if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
            System.out.println("Duration can not be 0");
            fail();
        }
        if (!duration.days.equals("0")) {
            $("app-service-widget").$("app-duration-viewer").shouldHave(text(duration.days));
        }
        if (!duration.hours.equals("0")) {
            $("app-service-widget").$("app-duration-viewer").shouldHave(text(duration.hours));
        }
        if (!duration.minutes.equals("0")) {
            $("app-service-widget").$("app-duration-viewer").shouldHave(text(duration.minutes));
        }

        $("app-service-widget").$("app-professional-card")
                .shouldHave(text(firstName), text(lastName));
        $("app-service-widget").shouldHave(text(serviceDescription));
    }

    @Step("Verify service location")
    public void verifyServiceLocation(String ServiceLocation) {
        $("app-service-widget app-service-location").shouldHave(text(ServiceLocation));
    }

    @Step("Verify service location")
    public void verifyServiceLocation(int ServiceLocation) {
        String value;
        switch (ServiceLocation) {
            case 0:
                value = "online";
                break;
            case 1:
                value = "client";
                break;
            case 2:
                value = "professional";
                break;
            default:
                value = null;
                System.out.println("Incorrect service location id: " + ServiceLocation);
                fail();
                break;
        }
        $("app-service-widget").$("app-service-location").shouldHave(text(value));
    }

    @Step("Verify service country/city/address")
    public void verifyServiceGeo(String ServiceCountry, String ServiceCity, String ServiceAddress) {
        $("app-service-widget").$("app-service-location")
                .shouldHave(text(ServiceCountry), text(ServiceCity), text(ServiceAddress));
    }

    @Step("Check if payment by cash")
    public void verifyServicePaymentCash() {
        $("app-service-widget app-payment-method-viewer ion-icon[name='cash-outline']").shouldBe(visible);
    }

    @Step("Check if payment online")
    public void verifyServicePaymentOnline() {
        $("app-service-widget app-payment-method-viewer ion-icon[name='card-outline']").shouldBe(visible);
    }

    @Step("Check instant booking")
    public void verifyInstantBooking(Boolean value) {
        if (value.equals(true)) {
            $("app-service-widget ion-icon[name='checkbox-outline']").shouldBe(visible);
        } else {
            $("app-service-widget ion-icon[name='checkbox-outline']").shouldNotBe(visible);
        }
    }

    @Step("Click the 'Order' button to book")
    public void clickOrder() {
        sleep(500);
        $("#pick-service").scrollIntoView(true).click();
        sleep(500);
    }

    @Step("Pick the date from calendar - ({date})")
    public void pickTheDate(String date) {
        $("app-book div.calendar div.day-first").shouldBe(visible, Duration.ofSeconds(10));
        sleep(200);
        if ($("app-book div.calendar", 0).$(withText(date)).has(cssClass("cursor-pointer"))) {
            $("app-book div.calendar", 0).$(withText(date)).scrollIntoView(true).click();
            $("app-book div.calendar", 0).$(withText(date)).shouldHave(cssClass("selected"), Duration.ofSeconds(10));
        } else if ($("app-book div.calendar", 1).$(withText(date)).has(cssClass("cursor-pointer"))) {
            $("app-book div.calendar", 1).$(withText(date)).scrollIntoView(true).click();
            $("app-book div.calendar", 1).$(withText(date)).shouldHave(cssClass("selected"), Duration.ofSeconds(10));
        } else {
            $("app-book div.calendar", 2).$(withText(date)).scrollIntoView(true).click();
            $("app-book div.calendar", 2).$(withText(date)).shouldHave(cssClass("selected"), Duration.ofSeconds(10));
        }
        $$("app-bottom-btn ion-button.footer__btn").filter(visible).get(0).shouldNotHave(cssClass("button-disabled"), Duration.ofSeconds(10));
        $$("app-bottom-btn ion-button.footer__btn").filter(visible).get(0).click();
        sleep(200);
    }

    @Step("Select previous day")
    public void clickPreviousDay() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button", 0).click();
        sleep(200);
    }

    @Step("Select next day")
    public void clickNextDay() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button", 1).click();
        sleep(200);
    }

    @Step("Verify previous day button inactive")
    public void verifyPreviousDayInactive() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button", 0).shouldHave(cssClass("button-disabled"));
        sleep(200);
    }

    @Step("Verify previous day button active")
    public void verifyPreviousDayActive() {
        sleep(200);
        $("app-time-step").$("app-calendar-component").$("ion-button", 0).shouldNotHave(cssClass("button-disabled"));
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
        $("app-time-step main div ion-button").shouldBe(visible, Duration.ofSeconds(10));
        $("app-time-step").$(withText(time)).scrollIntoView(true).click();
    }

    @Step("Click the 'Accept and continue' button")
    public void acceptTimeSelection() {
        $("app-time-step div.footer ion-button").shouldNotHave(cssClass("button-disabled"), Duration.ofSeconds(10));
        $("app-time-step div.footer ion-button").scrollIntoView(true).click();
        $("app-time-step div.footer ion-button").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Click the 'Accept and continue' button")
    public void acceptConfirmation() {
        $("app-confirmation-step div.footer ion-button").shouldNotHave(cssClass("button-disabled"), Duration.ofSeconds(10));
        $("app-confirmation-step div.footer ion-button").scrollIntoView(true).click();
        $("app-confirmation-step div.footer ion-button").shouldNotBe(visible, Duration.ofSeconds(10));
    }


    @Step("Click 'Order for another person")
    public void clickOrderForAnotherPerson() {
        sleep(200);
        $("app-client-details-step").$("form").$("ion-checkbox").scrollIntoView(true).click();
    }

    @Step("Order for another person: name - {value}")
    public void fillOrderForAPName(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='text']", 0).scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: surname - {value}")
    public void fillOrderForAPSurname(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='text']", 1).scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: email - {value}")
    public void fillOrderForAPEmail(String value) {
        $("app-client-details-step").$("form").$("section").$("input[type='email']").scrollIntoView(true).setValue(value);
    }

    @Step("Order for another person: phone - {country} {number}")
    public void fillOrderForAPPhoneNumber(String country, String number) {
        $("app-client-details-step").$("form").$("section").$("ionic-selectable").click();
        selectModal(country);
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
        $("app-client-details-step div.footer ion-button").shouldBe(visible, Duration.ofSeconds(10));
        $("app-client-details-step div.footer ion-button").click();
        $("app-sent-order-page div.order-full-info").shouldBe(visible, Duration.ofSeconds(10));
        sleep(500);
    }

    @Step("Click to see sent order details")
    public void showSentOrderDetails() {
        sleep(500);
        $("app-sent-order-page app-sent-order div.order-full-info__more-info a").shouldBe(visible, Duration.ofSeconds(10));
        $$("div.order-full-info__more-info a").filter(visible).get(0).click();
    }

    @Step("Verify the order details")
    public void verifyOrderDetails(String ServiceName) {
        $("app-sent-order-page section").shouldBe(visible, Duration.ofSeconds(10));
        $("app-sent-order-page section").shouldHave(text(ServiceName));
    }
}