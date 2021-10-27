package pages;

import config.Lang;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
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
        $("app-profile").$("ion-button[routerlink='edit/']").click();
        sleep(500);
    }

    @Step("Open Profile: Address")
    public void openUserProfileAddress() {
        $("app-profile").$("ion-item-group").$("ion-button").click();
        sleep(500);
    }

    @Step("Open Profile: About")
    public void openUserProfileAbout() {
        $("app-profile").$("ion-button[routerlink='about/']").click();
        sleep(500);
    }

    @Step("Click Back: Main")
    public void clickBackMain() {
        $("app-user-edit").$("app-column-header").$("ion-button").click();
        sleep(500);
    }

    @Step("Click Save: Main")
    public void clickSaveMain() {
        $("app-user-edit").$("ion-button[type='submit']").click();
        sleep(3000);
    }

    @Step("Change First name")
    public void inputFirstName(String firstName) {
        $("app-user-edit").$("input[name='first_name']").setValue(firstName);
    }

    @Step("Change Last name")
    public void inputLastName(String lastName) {
        $("app-user-edit").$("input[name='last_name']").setValue(lastName);
    }

    @Step("Change Patronymic")
    public void inputPatronymic(String patronymic) {
        $("app-user-edit").$("input[name='patronymic']").setValue(patronymic);
    }

    @Step("Select gender: male")
    public void selectGenderMale() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",0).click();
    }

    @Step("Select gender: female")
    public void selectGenderFemale() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",1).click();
    }

    @Step("Select gender: no gender")
    public void selectGenderNot() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",2).click();
    }

    @Step("Fill a phone number")
    public void fillPhoneNumber(String userPhoneNumber, String userCountry) {
        $("app-phone-editor").$("input").setValue(userPhoneNumber);
        $("app-phone-editor").$("button[type='button']").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item", 0).click();
    }

    @Step("Change E-mail")
    public void inputEmail(String email) {
        $("app-user-edit").$("input[name='email']").setValue(email);
    }

    @Step("Click Back: Address")
    public void clickBackAddress() {
        $("app-user-location-edit").$("app-column-header").$("ion-button").click();
    }

    @Step("Click Save: Address")
    public void clickSaveAddress() {
        $("app-user-location-edit").$("app-location-editor").$("ion-button").click();
    }

    @Step("Verify Profile: Main - minimal")
    public void verifyProfileMainMin(
        String firstName,
        String lastName,
        String email) {
        sleep(300);
        $("app-user-edit").$("input[name='first_name']").shouldHave(value(firstName));
        $("app-user-edit").$("input[name='last_name']").shouldHave(value(lastName));
        $("app-user-edit").$("input[name='email']").shouldHave(value(email));
    }

    @Step("Verify Profile: Main - full")
    public void verifyProfileMainMax(
            String firstName,
            String lastName,
            String patronymic,
            String phoneNumber,
            String email) {
        sleep(300);
        $("app-user-edit").$("input[name='first_name']").shouldHave(value(firstName));
        $("app-user-edit").$("input[name='last_name']").shouldHave(value(lastName));
        $("app-user-edit").$("input[name='patronymic']").shouldHave(value(patronymic));
        $("app-user-edit").$("app-phone-editor").$("input").shouldHave(value(phoneNumber));
        $("app-user-edit").$("input[name='email']").shouldHave(value(email));
    }

    @Step("Verify Profile: Main - Gender: Male")
    public void verifyGenderMale() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",0).should(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",1).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",2).shouldNot(cssClass("segment-button-checked"));
    }

    @Step("Verify Profile: Main - Gender: Female")
    public void verifyGenderFemale() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",0).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",1).should(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",2).shouldNot(cssClass("segment-button-checked"));
    }

    @Step("Verify Profile: Main - Gender: Not Specified")
    public void verifyGenderNot() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",0).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",1).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button",2).should(cssClass("segment-button-checked"));
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
        sleep(500);
        $("app-profile").$("ion-toolbar").shouldHave(text(Lang.RUSSIAN.getLangText()));
    }

    @Step("Check that verification E-mail sent")
    public void checkVerificationEmailSent(String email) {
        $("app-profile").$("app-info-row").shouldHave(text(email));
    }

    @Step("Profile: Contacts - click edit")
    public void clickEditContacts(Integer value) {
        $("app-profile").$("app-contacts-edit").$("ion-icon", value).click();
        sleep(500);
    }

    @Step("Profile: Contacts - click back")
    public void clickBackContacts() {
        $("app-user-contact-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Profile: Contacts - contacts: select contact")
    public void selectContact(String value) {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-select").click();
        sleep(200);
        $("ion-popover").$("ion-select-popover").$(withText(value)).closest("ion-item").click();
    }

    @Step("Profile: Contacts - contacts: enter value")
    public void enterContact(String value) {
        $("app-user-contact-edit").$("app-contact-edit").$("input[name='contact-value']").setValue(value);
    }

    @Step("Profile: Contacts - click 'Add new contact'")
    public void clickAddNewContact() {
        $("app-profile").$("app-contacts-edit").$("app-add-button").$("ion-item").click();
        sleep(500);
    }

    @Step("Profile: Contacts - remove contact")
    public void removeContact() {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-row").$("ion-button",0).click();
        sleep(500);
    }

    @Step("Profile: Contacts - save contact")
    public void saveContact() {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-button[type='submit']").click();
        sleep(500);
    }

    @Step("Verify contact exists")
    public void verifyContactExists(String value) {
        sleep(500);
        $("app-profile").$("app-contacts-edit").shouldHave(text(value));
    }

    @Step("Verify contact removed")
    public void verifyContactRemoved(String value) {
        $("app-profile").$("app-contacts-edit").shouldNotHave(text(value));
    }

    @Step("Verify 'Select Contact'")
    public void verifySelectContact(String value) {
        $("app-profile").$("app-contacts-edit").$("ion-select").$("div").shouldHave(text(value));
    }

    @Step("Verify 'Select Contact' is set default")
    public void verifySelectContactDefault() {
        sleep(500);
        $("app-profile").$("app-contacts-edit").$("ion-select[class='ion-untouched']").exists();
    }

    @Step("Verify 'Enter your contact'")
    public void verifyEnterYourContact(String value) {
        sleep(500);
        $("app-user-contact-edit").$("app-contact-edit").$("input").shouldHave(value(value));
    }

    @Step("Profile: About - click 'back'")
    public void clickBackAbout() {
        $("app-about-edit").$("app-column-header").$("ion-button").click();
        sleep(500);
    }

    @Step("Verify 'About'")
    public void verifyAbout(String nationality, String language, String dateDD, String dateMM, String dateYYYY) {
        sleep(500);
        $("app-profile").$("main").shouldHave(text(nationality), text(language), text(dateYYYY + "-" + dateMM + "-" + dateDD));
    }

    @Step("Verify 'About' language")
    public void verifyAboutExtraLanguage(String language) {
        sleep(500);
        $("app-profile").$("main").shouldHave(text(language));
    }


    @Step("Verify 'About' default")
    public void verifyAboutDefault() {
        sleep(500);
        $("app-about-edit").$("form").$("ion-input[class='ng-pristine']",0).exists();
        $("app-about-edit").$("form").$("ionic-selectable[class='ion-untouched']",0).exists();
        $("app-about-edit").$("form").$("ionic-selectable[class='ion-untouched']",1).exists();
    }

    @Step("Profile: About - select birth date")
    public void selectDateBirth(String dateDD, String dateMM, String dateYYYY) {
        $("app-about-edit").$("form").$("input").setValue(dateDD+dateMM+dateYYYY);
    }

    @Step("Profile: About - select nationality")
    public void selectNationality(String value) {
        $("app-about-edit").$("form").$("button",0).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile: About - select languages")
    public void selectLanguage(String value) {
        $("app-about-edit").$("form").$("button",1).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
        $("ionic-selectable-modal").$("ion-footer").$("ion-button",1).click();
        sleep(500);
    }

    @Step("Profile: About - click 'save'")
    public void clickSaveAbout() {
        $("app-about-edit").$("form").$("ion-button").click();
        sleep(500);
    }
}
