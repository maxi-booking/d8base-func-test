package helpers;

public class RegressionTestsHelpers extends config.TestBase {

    public static void userRegister() {
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        log.clickSideMenu();
        reg.openPageEN();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmail);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirm();
    }

    public static void serviceRegister() {
        log.forceEN();

        log.clickSideMenu();
        pbl.clickPublishNewService();

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