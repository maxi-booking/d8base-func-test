package tests.ordersTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServiceBooking.booking;
import static api.ServicePublish.*;
import static api.ServicePublish.servicePrices;
import static helpers.RegressionTestsHelpers.clientReadyAPI;
import static helpers.RegressionTestsHelpers.clientRegisterAPI;

public class OrderShareTests extends TestBase {

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client logged in, online order")
    void t00000() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, online, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI();
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client logged in, client's place order")
    void t00001() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationRandomDistance);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, client, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI();
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client logged in, professional's place order")
    void t00002() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, serviceLocationId, professional, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        clientReadyAPI();
        log.openUrl(onlineServiceShareLink);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without login, online order")
    void t00003() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, online, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        String clientAccessToken = clientRegisterAPI();
        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmailRandom, clientPasswordRandom);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without login, client's place order")
    void t00004() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationRandomDistance);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, client, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        String clientAccessToken = clientRegisterAPI();
        log.openUrl(onlineServiceShareLink);
        log.checkForErrors();
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmailRandom, clientPasswordRandom);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without login, professional's place order")
    void t00005() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, serviceLocationId, professional, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        String clientAccessToken = clientRegisterAPI();
        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        log.forceEN();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmailRandom, clientPasswordRandom);
        log.forceEN();
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.verifyOrderId(orderId);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without account, online order")
    void t00006() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, online, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, online, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
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
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmailRandom);
        reg.choosePassword(clientPasswordRandom);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, online);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without account, client's place order")
    void t00007() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, client, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationRandomDistance);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, locationsId, client, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
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
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmailRandom);
        reg.choosePassword(clientPasswordRandom);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order: client without account, professional's place order")
    void t00008() {
        String accessToken = registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, professional, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        int orderId = booking(accessToken, serviceId, serviceLocationId, professional, bookingDateTimeRandom);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
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
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();

        ord.shareOrderNewUser();
        ord.shareOrderClickConfirm();
        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmailRandom);
        reg.choosePassword(clientPasswordRandom);
        reg.fillPhoneNumber(clientPhoneNumber, clientCountry);
        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();
        log.forceEN();

        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.verifyOrderData(userFirstName, userLastName, serviceSpecializationRandom, serviceNameRandom, servicePriceRandom, serviceDurationRandom);
        ord.clickViewDetails();
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetails(serviceNameRandom, serviceDurationRandom, serviceAddress);
    }
}
