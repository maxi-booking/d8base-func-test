package helpers;

import java.util.Arrays;

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
        language.select(defaultLanguage);
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
        language.select(defaultLanguage);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(data.category[0]);
        pbl.chooseSubcategory(data.subcategory[0]);
        pbl.clickFirstStep();

        pbl.enterServiceName(data.name);
        pbl.enterServiceDescription(data.description[0]);
        pbl.setDuration(data.duration);
        pbl.setPriceFixed(data.price, data.currencyId);
        pbl.selectServiceLocation(data.sType);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(data.specialization[0]);
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
        data.professionalId = createProfessional(data.accessToken[0], data.category[0], data.subcategory[0], data.specialization[0], data.level[0], data.description[0]);
        data.serviceId = servicePublish(data.accessToken[0], data.professionalId, data.name, data.description[0], data.duration, data.sType, data.iBooking);
        data.professionalLocationId = professionalLocations(data.accessToken[0], data.professionalId, data.country[0], data.city[0], data.address, data.units);
        if (Integer.parseInt(data.duration) >= 1440) {
            Arrays.fill(data.startTime, "00:00");
            Arrays.fill(data.endTime, "23:59");
        }
        setSchedule(data.accessToken[0], data.professionalId, data.days, data.startTime, data.endTime);
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
        data.orderId = bookingProfessional(data.accessToken[0], data.serviceId, data.locationsId[0], data.sType, data.dateTime);
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
        data.accessToken[0] = registration(data.firstName[0], data.lastName[0], data.email[0], data.password[0], data.country[0], data.phoneNumber[0]);
        data.locationsId[0] = locations(data.accessToken[0], data.country[0], data.city[0]);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        language.select(defaultLanguage);
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
        language.select(defaultLanguage);
    }

    public static void serviceReadyAPI(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        language.select(defaultLanguage);
    }

    public static void serviceReadyAPIEnglish(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        language.select(english);
    }

    public static void masterBookingReadyAPI(Data data) {
        bookingCreateAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        language.select(defaultLanguage);
    }

    public static void clientBookingReadyAPI(Data data) {
        bookingCreateAPI(data);
        clientRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[1], data.password[1]);
        language.select(defaultLanguage);
    }

    public static void masterOrderReadyAPI(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[0], data.password[0]);
        language.select(defaultLanguage);
    }

    public static void clientOrderReadyAPI(Data data) {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(data.email[1], data.password[1]);
        language.select(defaultLanguage);
    }
}