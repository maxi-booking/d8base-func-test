package pages;

import com.codeborne.selenide.Condition;
import config.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Registration extends TestBase {

    @Step("Open the page")
    public void openPageUrl(String urlUserRegistration) {
        open(urlUserRegistration);
    }

    @Step("Open the page")
    public void openPageEN() {
        $("app-main-menu").$(byText("Sign up")).click();
    }

    @Step("Open the page")
    public void openPageRU() {
        $("app-main-menu").$(byText("Регистрация")).click();
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
        $("input[name='email']").setValue(userEmail);
    }

    @Step("Choose a password")
    public void choosePassword(String userPassword) {
        $("input[name='password']").setValue(userPassword);
        $("input[name='confirm']").setValue(userPassword);
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
        $("app-country-selector").$("button").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Select a city")
    public void selectCity(String userCity) {
        $("app-city-selector").$("button").click();
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

    @Step("Verify basic registration data")

    public void verifyRegistrationDataBasic(
            String userFirstName,
            String userEmail,
            String userCountry,
            String userCity
    ) {
        $("app-profile").$(withText(userFirstName)).should(Condition.visible);
        $("app-profile").$(withText(userEmail)).should(Condition.visible);
        $("app-profile").$(withText(userCountry)).should(Condition.visible);
        $("app-profile").$(withText(userCity)).should(Condition.visible);

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
        $("app-profile").$(withText(userFirstName)).should(Condition.visible);
        $("app-profile").$(withText(userLastName)).should(Condition.visible);
        $("app-profile").$(withText(userEmail)).should(Condition.visible);
        $("app-profile").$(withText(userPhoneNumber)).should(Condition.visible);
        $("app-profile").$(withText(userCountry)).should(Condition.visible);
        $("app-profile").$(withText(userCity)).should(Condition.visible);
    }

    @Step("Verify that there is no phone country code autofilled")
    public void verifyNoPhoneCountryCode() {
        String value = $("app-registration").$("app-phone-editor").$("ionic-selectable").getText();
        if (!value.equals(empty)) {fail();}
    }

    @Step("Verify autofilled phone country code")
    public void verifyPhoneCountryCode(String userCountry) {
        String value = $("app-registration").$("app-phone-editor").$("ionic-selectable").getText();
        if (value.equals(empty)) {fail();}
        $("app-registration-form").$("button[type='button']").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item", 0).shouldHave(Condition.cssClass("ionic-selectable-item-is-selected"));
    }
}
