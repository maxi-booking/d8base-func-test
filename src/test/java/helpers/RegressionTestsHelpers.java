package helpers;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServiceBooking.bookingClient;
import static api.ServiceBooking.bookingProfessional;
import static api.ServicePublish.*;
import static api.Orders.*;
import static api.Accounts.*;
import static helpers.DateTimeFormatter.getDateTime;

public class RegressionTestsHelpers extends config.TestBase {

    public static void userRegisterUI(Data data) {
        log.forceMainPage();
        log.popupSelect(data.country[0], data.city[0]);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(data.firstName[0]);
        reg.fillEmail(data.email[0]);
        reg.choosePassword(data.password[0]);
        reg.selectCountry(data.country[0]);
        reg.selectCity(data.city[0]);
        reg.confirmAndWait();
    }

    public static void serviceRegisterUI(Data data) {
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(data.category);
        pbl.chooseSubcategory(data.subcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(data.name);
        pbl.enterServiceDescription(data.description);
        pbl.setDuration(data.duration);
        pbl.setPriceFixed(data.price, data.currencyId);
        pbl.selectServiceLocation(data.sType);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(data.specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(data.iBooking);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.publishService();
    }

    public static void userRegisterAPI(Data data) {
        data.accessToken[0] = registration(data.firstName[0], data.lastName[0], data.email[0], data.password[0]);
        data.locationsId[0] = locations(data.accessToken[0], data.country[0], data.city[0]);
    }

    public static void serviceRegisterAPI(Data data) {
        changeAccountTypeToProfessional(data.accessToken[0]);
        data.professionalId = createProfessional(data.accessToken[0], data.category, data.subcategory, data.specialization);
        data.serviceId = servicePublish(data.accessToken[0], data.professionalId, data.name, data.description, data.duration, data.sType, data.iBooking);
        data.professionalLocationId = professionalLocations(data.accessToken[0], data.professionalId, data.country[0], data.city[0], data.address, data.units);
        setSchedule(data.accessToken[0], data.professionalId, 7);
        if (data.sType == online) {
            data.serviceLocationId = data.locationsId[0];
        } else if (data.sType == client) {
            data.serviceLocationId = data.locationsId[0];
            serviceLocations(data.accessToken[0], data.serviceId, data.professionalLocationId, data.locationDistance);
        } else if (data.sType == professional) {
            data.serviceLocationId = serviceLocations(data.accessToken[0], data.serviceId, data.professionalLocationId);
        }
        servicePrices(data.accessToken[0], data.serviceId, data.price, data.currency, data.payment);
    }

    public static void bookingCreateAPI(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        bookingProfessional(data.accessToken[0], data.serviceId, data.locationsId[0], data.sType, data.dateTime);
    }

    public static void userBookService(Data data) {
        data.orderId = bookingClient(data.accessToken[0], data.serviceId, data.locationsId[0], data.sType, data.dateTime);
    }

    public static void clientBookService(Data data) {
        int locationsId;
        if (data.sType == professional) {
            locationsId = data.serviceLocationId;
        } else {
            locationsId = data.locationsId[1];
        }
        data.orderId = bookingClient(data.accessToken[1], data.serviceId, locationsId, data.sType, data.dateTime);
    }

    public static void completeOrder(Data data) {
        orderComplete(data.accessToken[0], data.orderId);
    }

    public static void addReview(Data data) {
        sendReview(data.accessToken[1], data.professionalId, data.review, data.rating);
    }

    public static void addToFavorite(Data data) {
        saveProfessional(data.accessToken[1], data.professionalId);
    }

    public static void userReadyAPI(Data data) {
        data.accessToken[0] = registration(data.firstName[0], data.lastName[0], data.email[0], data.password[0]);
        data.locationsId[0] = locations(data.accessToken[0], data.country[0], data.city[0]);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        log.forceEN();
    }

    public static void clientRegisterAPI(Data data) {
        data.accessToken[1] = registration(data.firstName[1], data.email[1], data.password[1]);
        data.locationsId[1] = locations(data.accessToken[1], data.country[1], data.city[1]);
    }

    public static void clientReadyAPI(Data data) {
        data.accessToken[1] = registration(data.firstName[1], data.lastName[1], data.email[1], data.password[1]);
        data.locationsId[1] = locations(data.accessToken[1], data.country[1], data.city[1]);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[1], data.password[1]);
        log.forceEN();
    }

    public static void serviceReadyAPI(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        log.forceEN();
    }

    public static void masterBookingReadyAPI(Data data) {
        bookingCreateAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        log.forceEN();
    }

    public static void clientBookingReadyAPI(Data data) {
        bookingCreateAPI(data);
        clientRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[1], data.password[1]);
        log.forceEN();
    }
}