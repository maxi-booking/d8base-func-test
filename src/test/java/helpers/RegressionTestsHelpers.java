package helpers;

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

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration("0", "0", serviceDuration);
        pbl.setPriceFixed(servicePrice, rub);
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
}