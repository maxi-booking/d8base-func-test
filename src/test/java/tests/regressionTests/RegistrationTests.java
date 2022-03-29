package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

@Feature("User Registration")
@Owner("Egor Khlebnikov")
public class RegistrationTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4753")
    @DisplayName("Language retention after registration")
    @Severity(SeverityLevel.NORMAL)
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
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(firstNames[10], lastNames[10], emails[10], phoneNumbers[10], countries[10], cities[10]);
        up.verifyRussianLang();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4873")
    @DisplayName("Phone number should not be autofilled if user's location unknown")
    @Severity(SeverityLevel.NORMAL)
    void t00001() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.verifyNoPhoneCountryCode();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4873")
    @DisplayName("Phone number should be autofilled if user's location known")
    @Severity(SeverityLevel.NORMAL)
    void t00002() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.verifyPhoneCountryCode(userCountry);
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Email should not be case sensitive: register lowercase e-mail and try to log in with lower/upper/mixed case")
    @Severity(SeverityLevel.NORMAL)
    void t00100() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Email should not be case sensitive: register uppercase e-mail and try to log in with lower/upper/mixed case")
    @Severity(SeverityLevel.NORMAL)
    void t00101() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Email should not be case sensitive: register mixed case e-mail and try to log in with lower/upper/mixed case")
    @Severity(SeverityLevel.NORMAL)
    void t00102() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailLowercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailUppercase, userPassword);
        log.verifySuccessfulLogIn();

        log.forceLogOut();
        closeWindow();
        log.forceMainPage();
        log.popupSkip();
        log.logIn(userEmailMixedCase, userPassword);
        log.verifySuccessfulLogIn();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Trying to register account with the same lowercase e-mail, but different formats: lowercase, mixed case, uppercase")
    @Severity(SeverityLevel.CRITICAL)
    void t00103() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailLowercase);
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Trying to register account with the same mixed case e-mail, but different formats: lowercase, mixed case, uppercase")
    @Severity(SeverityLevel.CRITICAL)
    void t00104() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailMixedCase);
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4721")
    @DisplayName("Trying to register account with the same uppercase e-mail, but different formats: lowercase, mixed case, uppercase")
    @Severity(SeverityLevel.CRITICAL)
    void t00105() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailUppercase);
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
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
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
        log.toastVisible();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4935")
    @DisplayName("Country code and phone number inputs should be visible and interactable, after pup-up got canceled")
    @Severity(SeverityLevel.NORMAL)
    void t00200() {
        log.openMainPage();
        log.popupClickCancel();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.verifyPhoneNumber(userPhoneNumber);
        reg.verifyPhoneCountryCode(userCountry);
    }

    @Test
    @Link(name = "Issue link", url = "https://github.com/maxi-booking/d8base-frontend/pull/516")
    @DisplayName("Email will be automatically trimmed")
    @Severity(SeverityLevel.NORMAL)
    void trimEmail() {
        String emailWithSpaces = randomSpaces + userEmail + randomSpaces;
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(emailWithSpaces);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
        log.forceLogOut();
        log.logIn(userEmail, userPassword);
    }
}