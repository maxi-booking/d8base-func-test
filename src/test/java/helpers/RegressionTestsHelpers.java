package helpers;

import api.Registration;

public class RegressionTestsHelpers extends config.TestBase {

    public static void userRegister() {
        log.forceMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailRandom);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
    }

    public static void serviceRegister() {
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNameRandom);
        pbl.enterServiceDescription(serviceDescriptionRandom);
        pbl.setDuration(serviceDurationRandom);
        pbl.setPriceFixed(servicePriceRandom, randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(serviceSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.publishService();
    }

    public static void userReadyAPI() {
        Registration.registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();
    }
}