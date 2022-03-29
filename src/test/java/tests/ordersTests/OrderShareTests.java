package tests.ordersTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServiceBooking.bookingProfessional;
import static api.ServicePublish.*;
import static api.ServicePublish.servicePrices;
import static helpers.RegressionTestsHelpers.*;

@Feature("Orders")
@Story("Orders Share")
@Owner("Egor Khlebnikov")
public class OrderShareTests extends TestBase {

    @Test
    @DisplayName("Share order: client logged in, online order")
    @Severity(SeverityLevel.BLOCKER)
    void t00000() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI(data);
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
    }

    @Test
    @DisplayName("Share order: client logged in, client's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00001() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI(data);
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }

    @Test
    @DisplayName("Share order: client logged in, professional's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00002() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI(data);
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }

    @Test
    @DisplayName("Share order: client without login, online order")
    @Severity(SeverityLevel.BLOCKER)
    void t00003() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientRegisterAPI(data);
        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
    }

    @Test
    @DisplayName("Share order: client without login, client's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00004() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientRegisterAPI(data);
        log.openUrl(onlineServiceShareLink);
        log.checkForErrors();
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }

    @Test
    @DisplayName("Share order: client without login, professional's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00005() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientRegisterAPI(data);
        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }

    @Test
    @DisplayName("Share order: client without account, online order")
    @Severity(SeverityLevel.BLOCKER)
    void t00006() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmail);
        reg.choosePassword(clientPassword);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
    }

    @Test
    @DisplayName("Share order: client without account, client's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00007() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        log.openUrl(onlineServiceShareLink);
        log.checkForErrors();
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmail);
        reg.choosePassword(clientPassword);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }

    @Test
    @DisplayName("Share order: client without account, professional's place order")
    @Severity(SeverityLevel.BLOCKER)
    void t00008() {
        String accessToken = registration(userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, serviceCategory, serviceSubcategory, userSpecialization);
        int serviceId = servicePublish(accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmail);
        reg.choosePassword(clientPassword);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
    }
}
