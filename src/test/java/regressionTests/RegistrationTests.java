package regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

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
        log.clickSideMenu();
        reg.openPageRU();
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
        log.clickSideMenu();
        reg.openPageEN();
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
        log.clickSideMenu();
        reg.openPageEN();
        reg.verifyPhoneCountryCode(userCountry);
    }
}