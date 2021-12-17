package regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        sideMenu.clickPublishNewService();

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
        sideMenu.clickPublishNewService();

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

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4237")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Service publish: user can not select fixed price with the value: 0")
    void t00100() {
        userRegister();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceFixed("0", rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();
        pbl.fieldIsRequiredPresent();
        pbl.isStepTwo();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4237")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Service publish: user can not select price range with min value: 0")
    void t00101() {
        userRegister();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceRange("0", "100", rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();
        pbl.fieldIsRequiredPresent();
        pbl.isStepTwo();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4237")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Service publish: user can not select price range with max value: 0")
    void t00102() {
        userRegister();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceRange("0", "0", rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();
        pbl.fieldIsRequiredPresent();
        pbl.isStepTwo();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4256")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Service publish: element language retention start with English - check titles")
    void t00200() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        log.forceRU();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4256")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Service publish: element language retention start with Russian - check titles")
    void t00201() {
        log.popupSkip();
        log.forceRU();
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        log.forceEN();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4256")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Service publish: element language retention start with English - check lists")
    void t00202() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
        log.forceRU();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4256")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Service publish: element language retention start with Russian - check lists")
    void t00203() {
        log.popupSkip();
        log.forceRU();
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
        log.forceEN();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
    }

    @ParameterizedTest(name = "Service publish: verify that with price range {1} currency stays the same")
    @CsvSource({
            "0, CAD",
            "1, EUR",
            "2, RUB",
            "3, USD"
    })
    void t00300(int currency, String currencyName) {
        userRegister();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceRange(servicePriceMin, servicePriceMax, currency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(serviceSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.verifyPriceCurrency(servicePriceMin, servicePriceMax, currency);
        pbl.publishService();
    }
}