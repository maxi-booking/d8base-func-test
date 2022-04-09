package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.sleep;
import static helpers.RegressionTestsHelpers.userReadyAPI;

@Feature("Service Publication")
@Owner("Egor Khlebnikov")
public class ServicePublicationTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4887")
    @DisplayName("Service publish: city is selectable - client's place")
    @Severity(SeverityLevel.CRITICAL)
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4887")
    @DisplayName("Service publish: city is selectable - professional's place")
    @Severity(SeverityLevel.CRITICAL)
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4237")
    @DisplayName("Service publish: user can not select fixed price with the value: 0")
    @Severity(SeverityLevel.NORMAL)
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4237")
    @DisplayName("Service publish: user can not select price range with min value: 0")
    @Severity(SeverityLevel.NORMAL)
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4237")
    @DisplayName("Service publish: user can not select price range with max value: 0")
    @Severity(SeverityLevel.NORMAL)
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4256")
    @DisplayName("Service publish: element language retention start with English - check titles")
    @Severity(SeverityLevel.MINOR)
    void t00200() {
        log.openMainPage();
        log.popupSkip();
        language.select(english);
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        language.select(russian);
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4256")
    @DisplayName("Service publish: element language retention start with Russian - check titles")
    @Severity(SeverityLevel.MINOR)
    void t00201() {
        log.openMainPage();
        log.popupSkip();
        language.select(russian);
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        language.select(english);
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4256")
    @DisplayName("Service publish: element language retention start with English - check lists")
    @Severity(SeverityLevel.MINOR)
    void t00202() {
        log.openMainPage();
        log.popupSkip();
        language.select(english);
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageEng();
        pbl.verifyCategoryListLanguageEng();
        pbl.verifySubcategoryListLanguageEng();
        language.select(russian);
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4256")
    @DisplayName("Service publish: element language retention start with Russian - check lists")
    @Severity(SeverityLevel.MINOR)
    void t00203() {
        log.openMainPage();
        log.popupSkip();
        language.select(russian);
        sideMenu.clickPublishNewService();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
        log.refreshPage();
        log.popupSkip();
        pbl.verifyCategorySubcategoryTitleLanguageNotEng();
        pbl.verifyCategoryListLanguageNotEng();
        pbl.verifySubcategoryListLanguageNotEng();
        language.select(english);
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4900")
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
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4621")
    @DisplayName("Service publish: can not publish service without any payment option")
    @Severity(SeverityLevel.MINOR)
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

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5077")
    @DisplayName("Service publish: schedule can be edited after backing")
    @Severity(SeverityLevel.MINOR)
    void publishScheduleEditAfterBack() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration(serviceDuration);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceName, serviceDuration, serviceDescription);
        pbl.checkPrice(servicePrice);
        pbl.stepFinalClickBack();
        sleep(3000);
        pbl.step7ClickEditSchedule();
        sch.clickBack();
        pbl.clickSeventhStep();
        pbl.publishService();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5084")
    @DisplayName("Service publish: 4th step should be blocked by spinner after confirmation")
    @Severity(SeverityLevel.NORMAL)
    void publish4StepSpinnerWorks() {
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration(serviceDuration);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillEmail(userEmail);
        pbl.fillUserInfo(userFirstName, userLastName, userPassword, userCountry, userCity);
        pbl.clickFourthStepNoDelay();
        pbl.verifySpinnerWorks();
    }
}