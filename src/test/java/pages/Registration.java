package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByShadow;
import config.TestBase;
import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Registration extends TestBase {

    @Step("Open the page")
    public void openPageUrl(String urlUserRegistration) {
        open(urlUserRegistration);
    }

    @Step("Fill the first name")
    public void fillUserFirstName(String userFirstName) {
        $("input[name='name']").setValue(userFirstName);
    }

    @Step("Fill the last name")
    public void fillUserLastName(String userLastName) {
        $("input[name='name']", 1).setValue(userLastName);
    }

    @Step("Fill an email")
    public void fillEmail(String userEmail) {
        $$("input[name='email']").filter(visible).get(0).scrollIntoView(false).setValue(userEmail);
    }

    @Step("Choose a password")
    public void choosePassword(String userPassword) {
        $$("input[name='password']").filter(visible).get(0).scrollIntoView(false).setValue(userPassword);
        $$("input[name='confirm']").filter(visible).get(0).scrollIntoView(false).setValue(userPassword);
    }

    @Step("Fill a phone number")
    public void fillPhoneNumber(String userPhoneNumber, String userCountry) {
        $("app-phone-editor").$("input").setValue(userPhoneNumber);
        $("app-registration-form").$("button[type='button']").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item", 0).click();
    }

    @Step("Select a country")
    public void selectCountry(String userCountry) {
        $("app-registration-form").$("app-country-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Select a city")
    public void selectCity(String userCity) {
        $("app-registration-form").$("app-city-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCity);
        sleep(1000);
        $("ionic-selectable-modal").$("ion-item", 0).click();
    }

    @Step("Confirm")
    public void confirm() {
        sleep(400);
        $("app-registration").$("ion-button[type='submit']").click();
        sleep(2000);
    }

    @Step("Confirm")
    public void confirmAndWait() {
        sleep(400);
        int timeOut = 0;
        $("app-registration").$("ion-button[type='submit']").click();
        while ($("app-registration").isDisplayed()) {
            timeOut = timeOut + 2;
            if (timeOut >= 20) {
                Attach.screenshotAs("Screenshot");
                fail();}
            sleep(2000);
        }
        sleep(2000);
    }

    @Step("Verify basic registration data")

    public void verifyRegistrationDataBasic(
            String userFirstName,
            String userEmail,
            String userCountry,
            String userCity
    ) {
        $("app-profile").$(withText(userFirstName)).should(visible);
        $("app-profile").$(withText(userEmail)).should(visible);
        $("app-profile").$(withText(userCountry)).should(visible);
        $("app-profile").$(withText(userCity)).should(visible);

    }

    @Step("Verify full registration data")
    public void verifyRegistrationDataFull(
            String userFirstName,
            String userLastName,
            String userEmail,
            String userPhoneNumber,
            String userCountry,
            String userCity
    ) {
        $("app-profile").$(withText(userFirstName)).should(visible);
        $("app-profile").$(withText(userLastName)).should(visible);
        $("app-profile").$(withText(userEmail)).should(visible);
        $("app-profile").$(withText(userPhoneNumber)).should(visible);
        $("app-profile").$(withText(userCountry)).should(visible);
        $("app-profile").$(withText(userCity)).should(visible);
    }

    @Step("Verify that there is no phone country code autofilled")
    public void verifyNoPhoneCountryCode() {
        String value = $("app-registration").$("app-phone-editor").$("ionic-selectable").getText();
        if (!value.equals(empty)) {
            fail();
        }
    }

    @Step("Verify phone country code - {userCountry}")
    public void verifyPhoneCountryCode(String userCountry) {
        String value = $("app-registration").$("app-phone-editor").$("ionic-selectable").getText();
        if (value.equals(empty)) {
            fail();
        }
        $("app-registration-form").$("button[type='button']").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ion-virtual-scroll").$("ion-item", 0).shouldHave(Condition.cssClass("ionic-selectable-item-is-selected"));
        sleep(500);
        $("ion-virtual-scroll").$("ion-item", 0).pressEscape();
    }

    @Step("Verify phone number filled")
    public void verifyPhoneNumber(String userPhoneNumber) {
        $("app-phone-editor input").parent().shouldHave(Condition.cssClass("has-value"));
    }

    @Step("Complete turor slides to search")
    public void completeTutorSlidesToSearch() {
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
    }

    @Step("Complete turor slides to service publish")
    public void completeTutorSlidesToPublish() {
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(1).scrollIntoView(true).click();
    }
}