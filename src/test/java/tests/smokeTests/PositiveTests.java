package tests.smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;
import static java.lang.String.valueOf;

public class PositiveTests extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Minimal registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic Positive User Registration")
    void t00000() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmail);
        reg.choosePassword(userPassword);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
        reg.completeTutorSlidesToPublish();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataBasic(userFirstName, userEmail, userCountry, userCity);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - in order")
    void t00001() {
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillUserLastName(userLastName);
        reg.fillEmail(userEmail);
        reg.choosePassword(userPassword);
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
        reg.completeTutorSlidesToSearch();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(userFirstName, userLastName, userEmail, userEmail, userCountry, userCity);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - reverse order")
    void t00002() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.choosePassword(userPassword);
        reg.fillEmail(userEmail);
        reg.fillUserLastName(userLastName);
        reg.fillUserFirstName(userFirstName);
        reg.confirmAndWait();
        reg.completeTutorSlidesToPublish();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(userFirstName, userLastName, userEmail, userPhoneNumber, userCountry, userCity);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - mixed order 1")
    void t00003() {
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.choosePassword(userPassword);
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmail);
        reg.fillUserLastName(userLastName);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
        reg.completeTutorSlidesToSearch();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(userFirstName, userLastName, userEmail, userPhoneNumber, userCountry, userCity);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - mixed order 2")
    void t00004() {
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.fillEmail(userEmail);
        reg.choosePassword(userPassword);
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.fillUserFirstName(userFirstName);
        reg.fillUserLastName(userLastName);
        reg.confirmAndWait();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(userFirstName, userLastName, userEmail, userPhoneNumber, userCountry, userCity);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - mixed order 3")
    void t00005() {
        log.openMainPage();
        log.popupSelect(userCountry, userCity);
        language.select(defaultLanguage);
        sideMenu.clickSignUp();
        reg.fillUserLastName(userLastName);
        reg.fillUserFirstName(userFirstName);
        reg.choosePassword(userPassword);
        reg.fillEmail(userEmail);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.fillPhoneNumber(userPhoneNumber, userCountry);
        reg.confirmAndWait();
        language.select(defaultLanguage);
        sideMenu.openProfile();
        reg.verifyRegistrationDataFull(userFirstName, userLastName, userEmail, userPhoneNumber, userCountry, userCity);
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online")
    void t00100() {
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
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Client's place")
    void t00101() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
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

        pbl.checkPublishFormWithAddress(serviceName, serviceDuration, serviceDescription, userCountry, userCity, serviceAddress);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Professional's place")
    void t00102() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
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

        pbl.checkPublishFormWithAddress(serviceName, serviceDuration, serviceDescription, userCountry, userCity, serviceAddress);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing with no account, service location: Online")
    void t00103() {
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
        pbl.clickFourthStep();

        pbl.clickFifthStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceName, serviceDuration, serviceDescription);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Service publication with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Service publishing >24h duration, service location: Online")
    void servicePublishing24hOnline() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration(serviceDurationLong);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.verifyNoScheduleEditButton();
        pbl.verifyScheduleInfiniteTime();
        pbl.instantBooking(on);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceName, serviceDurationLong, serviceDescription);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Service publication with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Service publishing >24h duration, service location: Client's place")
    void servicePublishing24hClient() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration(serviceDurationLong);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(client);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.verifyNoScheduleEditButton();
        pbl.verifyScheduleInfiniteTime();
        pbl.instantBooking(on);
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.fillServiceDistance(serviceDistance);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(serviceName, serviceDurationLong, serviceDescription, userCountry, userCity, serviceAddress);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Service publication with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Service publishing >24h duration, service location: Professional's place")
    void servicePublishing24hMaster() {
        userReadyAPI(data);
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(serviceCategory);
        pbl.chooseSubcategory(serviceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceName);
        pbl.enterServiceDescription(serviceDescription);
        pbl.setDuration(serviceDurationLong);
        pbl.setPriceFixed(servicePrice, serviceCurrencyId);
        pbl.selectServiceLocation(professional);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(userSpecialization);
        pbl.clickSixthStep();

        pbl.verifyNoScheduleEditButton();
        pbl.verifyScheduleInfiniteTime();
        pbl.instantBooking(on);
        pbl.fillServiceGeo(userCountry, userCity, serviceAddress);
        pbl.PaymentOptions(true, true, data);
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(serviceName, serviceDurationLong, serviceDescription, userCountry, userCity, serviceAddress);
        pbl.checkPrice(servicePrice);
        pbl.publishService();
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online")
    void t00200() {
        data.sType = online;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(online);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking client's place")
    void t00201() {
        data.sType = client;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(clientLocation);
        bkn.verifyServiceGeo(userCountry, userCity, serviceAddress);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking master's place")
    void t00202() {
        data.sType = professional;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(professionalLocation);
        bkn.verifyServiceGeo(userCountry, userCity, serviceAddress);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Booking a service with >24h duration - online")
    void booking24hOnline() {
        data.sType = online;
        data.duration = data.durationLong;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDurationLong, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(online);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDurationLong);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Booking a service with >24h duration - client's place")
    void booking24hClient() {
        data.sType = client;
        data.duration = data.durationLong;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDurationLong, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(clientLocation);
        bkn.verifyServiceGeo(userCountry, userCity, serviceAddress);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDurationLong);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking with >=24h duration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Booking a service with >24h duration - professional's place")
    void booking24hMaster() {
        data.sType = professional;
        data.duration = data.durationLong;

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDurationLong, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(professionalLocation);
        bkn.verifyServiceGeo(userCountry, userCity, serviceAddress);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDurationLong);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check orders from user side")
    void t00300() {
        data.sType = online;
        data.dateTime = dateTime[0];
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);
        String accessToken = data.accessToken[1];
        int locationsId = data.locationsId[1];
        String email = data.email[1];
        String password = data.password[1];
        clientBookService(data);
        String professionalFirstName1 = data.firstName[0];
        String serviceName1 = data.name;
        String servicePrice1 = data.price;
        String serviceDuration1 = data.duration;

        setRandomData();
        data.sType = professional;
        data.accessToken[1] = accessToken;
        data.locationsId[1] = locationsId;
        data.dateTime = dateTime[1];

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientBookService(data);
        String professionalFirstName2 = data.firstName[0];
        String serviceName2 = data.name;
        String servicePrice2 = data.price;
        String serviceDuration2 = data.duration;

        setRandomData();
        data.sType = client;
        data.accessToken[1] = accessToken;
        data.locationsId[1] = locationsId;
        data.dateTime = dateTime[2];

        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientBookService(data);
        String professionalFirstName3 = data.firstName[0];
        String serviceName3 = data.name;
        String servicePrice3 = data.price;
        String serviceDuration3 = data.duration;

        topBar.clickMyOrders();
        ord.checkOrderOutbox(professionalFirstName1, serviceName1, servicePrice1, serviceDuration1);
        ord.checkOrderOutbox(professionalFirstName2, serviceName2, servicePrice2, serviceDuration2);
        ord.checkOrderOutbox(professionalFirstName3, serviceName3, servicePrice3, serviceDuration3);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Master can't complete order too early: online")
    void orderButtonBlockedFreshOrderOnline() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(clientFirstName, servicePrice, serviceDuration);
        ord.canNotCompleteOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Master can't complete order too early: client's place")
    void orderButtonBlockedFreshOrderClient() {
        data.sType = client;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(clientFirstName, servicePrice, serviceDuration);
        ord.canNotCompleteOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Master can't complete order too early: master's place")
    void orderButtonBlockedFreshOrderMaster() {
        data.sType = professional;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(clientFirstName, servicePrice, serviceDuration);
        ord.canNotCompleteOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Client side order cancel")
    void t00304() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
        ord.discardOrderClient("I don't like the service");
        ord.verifyNoOrdersInOrderInbox();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master side order cancel")
    void t00305() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(clientFirstName, servicePrice, serviceDuration);
        ord.discardOrder("Appointment canceled");
        ord.verifyNoOrdersInOrderInbox();
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with 10 words")
    void t00400() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        completeOrder(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.choseRating(randomRating);
        rev.sendReviewText(reviewText1);
        rev.pressSend();
        rev.verifyReview(userFirstName + " " + userLastName, clientFirstName, reviewText1);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with a lot of characters")
    void t00401() {
        data.sType = client;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        completeOrder(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.choseRating(randomRating);
        rev.sendReviewText(reviewText2);
        rev.pressSend();
        rev.verifyReview(userFirstName + " " + userLastName, clientFirstName, reviewText2);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review add to favorites in the process")
    void t00402() {
        data.sType = professional;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        completeOrder(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.addToFavorite();
        rev.choseRating(randomRating);
        String rating = valueOf(randomRating);
        rev.sendReviewText(reviewText3 + " " + reviewText4);
        rev.pressSend();
        rev.verifyReview(userFirstName + " " + userLastName, clientFirstName, reviewText3 + " " + reviewText4);

        rev.clickMenuMain();
        rev.openBookmarksMenu();
        rev.verifyBookmark(userFirstName + " " + userLastName, rating);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review master answers")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Review answer")
    void t00403() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        completeOrder(data);
        addReview(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        sideMenu.clickProfessionalProfile();
        rev.clickMasterReviews();
        rev.postMasterComment(masterComment);
        rev.verifyMasterComment(masterComment);
    }

    @Test
    @Feature("Messages")
    @Owner("Egor Khlebnikov")
    @Story("2 users chatting tests")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Chat test")
    void chatMessagesTest() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        clientBookService(data);
        completeOrder(data);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        sideMenu.clickSentOrders();
        ord.tabArchivedOrdersOutbox();
        ord.clickProfessionalsName();
        pp.clickChat();
        msg.sendMessage(data.message[0]);
        msg.checkMessage(data.message[0]);
        log.forceLogOut();

        log.logIn(userEmail, userPassword);
        language.select(defaultLanguage);
        topBar.clickChat();
        msg.findUserChat(clientFirstName + " " + clientLastName);
        msg.selectUser();
        msg.checkMessage(data.message[0]);
        msg.sendMessage(data.message[1]);
        msg.checkMessage(data.message[1]);
        log.forceLogOut();

        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);
        topBar.clickChat();
        msg.findUserChat(userFirstName + " " + userLastName);
        msg.selectUser();
        msg.checkMessage(data.message[0]);
        msg.checkMessage(data.message[1]);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Remove master from favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Remove master from favorites")
    void t00600() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientRegisterAPI(data);
        addToFavorite(data);

        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmail, clientPassword);
        language.select(defaultLanguage);

        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(userFirstName + " " + userLastName);
        fav.removeBookmark();
        fav.verifyDelBookmark(userFirstName + " " + userLastName);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from search results")
    void t00601() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        fav.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        fav.clickFavSearch();
        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(userFirstName + " " + userLastName);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from master's profile")
    void t00602() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        fav.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        fav.selectMasterSearch();
        fav.clickFavMasterProfile();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(userFirstName + " " + userLastName, userCity);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from the service info")
    void t00603() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        clientReadyAPI(data);

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        fav.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        fav.selectServiceSearch();
        fav.clickFavServiceInfo();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(userFirstName + " " + userLastName, userCity);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking from the main page: guest")
    void t00700() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);

        bkn.findServiceMainPage(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(onlineLocation);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();

        bkn.pickTheDate(next2Days);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();

        bkn.selectNewUser();
        bkn.acceptConfirmation();

        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmail);
        reg.choosePassword(clientPassword);

        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();

        bkn.placeOrder();
        language.select(defaultLanguage);

        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking: guest")
    void t00701() {
        data.sType = online;
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSearch();

        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
        bkn.verifyServiceLocation(onlineLocation);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();

        bkn.pickTheDate(next3Days);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();

        bkn.selectNewUser();
        bkn.acceptConfirmation();

        reg.fillUserFirstName(clientFirstName);
        reg.fillUserLastName(clientLastName);
        reg.fillEmail(clientEmail);
        reg.choosePassword(clientPassword);

        reg.selectCountry(clientCountry);
        reg.selectCity(clientCity);
        reg.confirmAndWait();

        bkn.placeOrder();
        language.select(defaultLanguage);

        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(userFirstName, serviceName, servicePrice, serviceDuration);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: professional name")
    void t00800() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);

        search.searchMainNoQuotes(userFirstName + " " + userLastName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: service name")
    void t00801() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);

        bkn.findServiceMainPage(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: speciality")
    void t00802() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);

        bkn.findServiceMainPage(userSpecialization);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: professional name")
    void t00803() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSearch();

        search.searchNoQuotes(userFirstName + " " + userLastName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: service name")
    void t00804() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSearch();

        bkn.findService(serviceName);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: speciality")
    void t00805() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);
        log.openMainPage();
        log.popupSkip();
        language.select(defaultLanguage);
        sideMenu.clickSearch();

        bkn.findService(userSpecialization);
        bkn.verifyServiceSearch(userFirstName, userLastName, serviceName, servicePrice);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceName, servicePrice, serviceDuration, userFirstName, userLastName, serviceDescription);
    }
}