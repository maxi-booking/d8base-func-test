package regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceRegister;
import static helpers.RegressionTestsHelpers.userRegister;

public class ServicePublicationTests extends config.TestBase {
    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4887")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Service publish: city is selectable - client's place")
    void t00000() {
        userRegister();
        log.openMainPage();
        log.forceEN();

        log.clickSideMenu();
        pbl.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceFixed(servicePrice, rub);
        pbl.selectServiceLocation(client);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(serviceSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.fillServiceDistance(serviceDistance);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.publishService();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4887")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Service publish: city is selectable - professional's place")
    void t00001() {
        userRegister();
        log.openMainPage();
        log.forceEN();

        log.clickSideMenu();
        pbl.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceFixed(servicePrice, rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(serviceSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.publishService();
    }
}