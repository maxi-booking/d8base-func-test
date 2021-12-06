package regressionTests;

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
        log.popupSkip();
        log.forceRU();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user11FirstName);
        reg.fillUserLastName(user11LastName);
        reg.fillEmail(user11Email);
        reg.choosePassword(user11Password);
        reg.fillPhoneNumber(user11PhoneNumber, user11Country);
        reg.selectCountry(user11Country);
        reg.selectCity(user11City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user11FirstName, user11LastName, user11Email, user11PhoneNumber, user11Country, user11City);
        up.verifyRussianLang();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4873")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Phone number should not be autofilled if user's location unknown")
    void t00001() {
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
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email should not be case sensitive: register uppercase e-mail and try to log in with lower/upper/mixed case")
    void t00101() {
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4721")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email should not be case sensitive: register mixed case e-mail and try to log in with lower/upper/mixed case")
    void t00102() {
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.logOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }
}