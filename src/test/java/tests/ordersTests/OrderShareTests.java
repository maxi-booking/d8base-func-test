package tests.ordersTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServiceBooking.bookingProfessional;
import static api.ServicePublish.*;
import static helpers.RegressionTestsHelpers.*;

@Feature("Orders")
@Story("Orders Share")
@Owner("Egor Khlebnikov")
public class OrderShareTests extends TestBase {

    @Test
    @DisplayName("Share order: client logged in, online order")
    @Severity(SeverityLevel.BLOCKER)
    void t00000() {
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        serviceLocations(data.locale, accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(data.locale, accessToken, serviceId, professionalLocationId);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        data.locale = language.select(defaultLanguage);
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, online);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        data.locale = language.select(defaultLanguage);
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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        serviceLocations(data.locale, accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        data.locale = language.select(defaultLanguage);
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        data.locale = language.select(defaultLanguage);
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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(data.locale, accessToken, serviceId, professionalLocationId);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        data.locale = language.select(defaultLanguage);
        ord.verifyOrderId(orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, serviceAddress);
        ord.shareOrderClickConfirm();
        ord.shareOrderHaveAccount();
        ord.shareOrderClickConfirm();
        log.logIn(clientEmail, clientPassword);
        data.locale = language.select(defaultLanguage);
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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, online, instantBooking);
        professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, online, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        data.locale = language.select(defaultLanguage);
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
        data.locale = language.select(defaultLanguage);

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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, client, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        serviceLocations(data.locale, accessToken, serviceId, professionalLocationId, serviceLocationDistance);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, locationsId, client, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

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
        data.locale = language.select(defaultLanguage);
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
        data.locale = language.select(defaultLanguage);

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
        String accessToken = registration(data.locale, userFirstName, userLastName, userEmail, userPassword);
        int locationsId = locations(data.locale, accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(data.locale, accessToken);
        int professionalId = createProfessional(data.locale, accessToken, serviceSubcategoryId, userSpecialization);
        int serviceId = servicePublish(data.locale, accessToken, professionalId, serviceName, serviceDescription, serviceDuration, professional, instantBooking);
        int professionalLocationId = professionalLocations(data.locale, accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(data.locale, accessToken, professionalId, 7);
        int serviceLocationId = serviceLocations(data.locale, accessToken, serviceId, professionalLocationId);
        servicePrices(data.locale, accessToken, serviceId, servicePrice, serviceCurrency, paymentCashOnline);
        int orderId = bookingProfessional(data.locale, accessToken, serviceId, serviceLocationId, professional, bookingDateTime);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        data.locale = language.select(defaultLanguage);

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = ord.shareOrderGetId();
        log.forceLogOut();

        log.openUrl(onlineServiceShareLink);
        log.popupSkip();
        data.locale = language.select(defaultLanguage);
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
        data.locale = language.select(defaultLanguage);

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
