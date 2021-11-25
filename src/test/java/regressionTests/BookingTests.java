package regressionTests;

import helpers.RegressionTestsHelpers;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceRegister;
import static helpers.RegressionTestsHelpers.userRegister;

public class BookingTests extends config.TestBase {
    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4857")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Booking: today's date")
    void t00000() {
        userRegister();
        serviceRegister();
        log.openMainPage();
        log.forceEN();
        log.clickSideMenu();
        bkn.clickSearchEN();

        bkn.closeFilters();
        bkn.findService(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime();
        bkn.clickForward();
        bkn.clickAccept();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceName);
    }
}