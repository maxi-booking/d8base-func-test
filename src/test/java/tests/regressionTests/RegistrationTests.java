package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

public class RegistrationTests extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4753")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Language retention after registration")
    void t00000() {
        log.openMainPage();
        log.popupSkip();
        log.forceRU();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(firstNames[10]);
        reg.fillUserLastName(lastNames[10]);
        reg.fillEmail(emails[10]);
        reg.choosePassword(passwords[10]);
        reg.fillPhoneNumber(phoneNumbers[10], countries[10]);
        reg.selectCountry(countries[10]);
        reg.selectCity(cities[10]);
        reg.confirmAndWait();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[10], lastNames[10], emails[10], phoneNumbers[10], countries[10], cities[10]);
        up.verifyRussianLang();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4873")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Phone number should not be autofilled if user's location unknown")
    void t00001() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.verifyNoPhoneCountryCode();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4873")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Phone number should be autofilled if user's location known")
    void t00002() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.verifyPhoneCountryCode(userCountry);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email should not be case sensitive: register lowercase e-mail and try to log in with lower/upper/mixed case")
    void t00100() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPasswordRandom);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email should not be case sensitive: register uppercase e-mail and try to log in with lower/upper/mixed case")
    void t00101() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPasswordRandom);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email should not be case sensitive: register mixed case e-mail and try to log in with lower/upper/mixed case")
    void t00102() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPasswordRandom);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPasswordRandom);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Trying to register account with the same lowercase e-mail, but different formats: lowercase, mixed case, uppercase")
    void t00103() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPasswordRandom);
        reg.confirm();
        log.noToast();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Trying to register account with the same mixed case e-mail, but different formats: lowercase, mixed case, uppercase")
    void t00104() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPasswordRandom);
        reg.confirm();
        log.noToast();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Trying to register account with the same uppercase e-mail, but different formats: lowercase, mixed case, uppercase")
    void t00105() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPasswordRandom);
        reg.confirm();
        log.noToast();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4935")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Country code and phone number inputs should be visible and interactable, after pup-up got canceled")
    void t00200() {
        log.openMainPage();
        log.popupClickCancel();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.verifyPhoneNumber(userPhoneNumber);
        reg.verifyPhoneCountryCode(userCountry);
    }
}