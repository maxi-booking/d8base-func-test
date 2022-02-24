package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.RegressionTestsHelpers.userReadyAPI;

public class ServicePublicationTests extends config.TestBase {
    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4887")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Service publish: city is selectable - client's place")
    void t00000() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(client);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.fillServiceDistance(serviceDistance);
        pbl.PaymentOptions(true, true, data);
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
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(professional);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.PaymentOptions(true, true, data);
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
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceFixed("0", serviceCurrencyId);
        pbl.selectServiceLocation(professional);
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
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceRange("0", "100", rub);
        pbl.selectServiceLocation(professional);
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
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceRange("0", "0", rub);
        pbl.selectServiceLocation(professional);
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
        log.openMainPage();
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
        log.openMainPage();
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
        log.openMainPage();
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
        log.openMainPage();
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
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4900")
    @Severity(SeverityLevel.CRITICAL)
    @CsvSource({
            "0, CAD",
            "1, EUR",
            "2, RUB",
            "3, USD"
    })
    void t00300(int currency, String currencyName) {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.setDuration(serviceDuration);
        pbl.setPriceRange(servicePriceMin, servicePriceMax, currency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.verifyPriceCurrency(servicePriceMin, servicePriceMax, currency);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publication")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4621")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Service publish: can not publish service without any payment option")
    void t00400() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[0]);
        pbl.enterServiceDescription(serviceDescriptions[0]);
        pbl.setDuration(serviceDurations[0]);
        pbl.setPriceFixed(servicePrices[0], serviceCurrencyId);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[0]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.PaymentOptions(false, false, data);
        pbl.verifySeventhStepContinueIsNotClickable();
    }
}