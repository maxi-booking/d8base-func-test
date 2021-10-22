package pages;

import config.Lang;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class UserProfile {

    @Step("Open the page")
    public void openUserProfile() {
        sleep(200);
        $("app-main-menu").$(byText("Profile")).click();
        sleep(300);
    }

    @Step("Verify profile data")
    public void verifyProfile(
            String firstName,
            String lastName,
            String email,
            String country,
            String city) {
        sleep(300);
        $("app-profile").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(country),
                text(city));
    }

    @Step("Open Profile: Main")
    public void openUserProfileMain() {
        $("app-profile").$("ion-item-divider").$("ion-button").click();
    }

    @Step("Open Profile: Address")
    public void openUserProfileAddress() {
        $("app-profile").$("ion-item-group").$("ion-button").click();
    }

    @Step("Open Profile: About")
    public void openUserProfileAbout() {
        $("app-profile").$("ion-item-divider",3).$("ion-button").click();
    }

    @Step("Click Back: Main")
    public void clickBackMain() {
        $("app-user-edit").$("app-column-header").$("ion-button").click();
    }

    @Step("Click Save: Main")
    public void clickSaveMain() {
        $("app-profile").$("form").$("ion-button[type='submit']").click();
    }

    @Step("Click Back: Address")
    public void clickBackAddress() {
        $("app-user-location-edit").$("app-column-header").$("ion-button").click();
    }

    @Step("Click Save: Address")
    public void clickSaveAddress() {
        $("app-user-location-edit").$("app-location-editor").$("ion-button").click();
    }

    @Step("Verify Profile: Main")
    public void verifyProfileMain(
        String firstName,
        String lastName,
        String email) {
        sleep(300);
        $("app-user-edit").shouldHave(
                text(firstName),
                text(lastName),
                text(email));
    }

    @Step("Verify Profile: Address")
    public void verifyProfileAddress(
            String country,
            String city) {
        sleep(300);
        $("app-user-location-edit").$("app-location-editor").$("app-country-selector").shouldHave(text(country));
        $("app-user-location-edit").$("app-location-editor").$("app-city-selector").shouldHave(text(city));
    }

    @Step("Verify language")
    public void verifyRussianLang() {
        $("app-profile").$("ion-toolbar").shouldHave(text(Lang.RUSSIAN.getLangText()));
    }

}
