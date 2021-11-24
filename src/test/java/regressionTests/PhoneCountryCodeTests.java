package regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PhoneCountryCodeTests  extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4873")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Phone number should not be autofilled if user's location unknown")
    void t00000() {
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
    void t00001() {
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        log.clickSideMenu();
        reg.openPageEN();
        reg.verifyPhoneCountryCode(userCountry);
    }
}
