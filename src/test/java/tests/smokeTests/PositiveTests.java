package tests.smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.String.valueOf;

public class PositiveTests extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - in order")
    void t00000() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(firstNames[0]);
        reg.fillUserLastName(lastNames[0]);
        reg.fillEmail(emails[0]);
        reg.choosePassword(passwords[0]);
        reg.fillPhoneNumber(phoneNumbers[0], countries[0]);
        reg.selectCountry(countries[0]);
        reg.selectCity(cities[0]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToSearch();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[0], lastNames[0], emails[0], phoneNumbers[0], countries[0], cities[0]);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - reverse order")
    void t00001() {
        log.openMainPage();
        log.popupSelect(countries[1], cities[1]);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.selectCountry(countries[1]);
        reg.selectCity(cities[1]);
        reg.fillPhoneNumber(phoneNumbers[1], countries[1]);
        reg.choosePassword(passwords[1]);
        reg.fillEmail(emails[1]);
        reg.fillUserLastName(lastNames[1]);
        reg.fillUserFirstName(firstNames[1]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToPublish();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[1], lastNames[1], emails[1], phoneNumbers[1], countries[1], cities[1]);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - mixed order 1")
    void t00002() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillPhoneNumber(phoneNumbers[2], countries[2]);
        reg.choosePassword(passwords[2]);
        reg.fillUserFirstName(firstNames[2]);
        reg.fillEmail(emails[2]);
        reg.fillUserLastName(lastNames[2]);
        reg.selectCountry(countries[2]);
        reg.selectCity(cities[2]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToSearch();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[2], lastNames[2], emails[2], phoneNumbers[2], countries[2], cities[2]);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Minimal registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic Positive User Registration")
    void t00003() {
        log.openMainPage();
        log.popupSelect(countries[4], cities[4]);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(firstNames[4]);
        reg.fillEmail(emails[4]);
        reg.choosePassword(passwords[4]);
        reg.selectCountry(countries[4]);
        reg.selectCity(cities[4]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToPublish();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataBasic(firstNames[4], emails[4], countries[4], cities[4]);
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
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillEmail(emails[5]);
        reg.choosePassword(passwords[5]);
        reg.fillPhoneNumber(phoneNumbers[5], countries[5]);
        reg.selectCountry(countries[5]);
        reg.selectCity(cities[5]);
        reg.fillUserFirstName(firstNames[5]);
        reg.fillUserLastName(lastNames[5]);
        reg.confirmAndWait();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[5], lastNames[5], emails[5], phoneNumbers[5], countries[5], cities[5]);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - mixed order 3")
    void t00005() {
        log.openMainPage();
        log.popupSelect(countries[6], cities[6]);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserLastName(lastNames[6]);
        reg.fillUserFirstName(firstNames[6]);
        reg.choosePassword(passwords[6]);
        reg.fillEmail(emails[6]);
        reg.selectCountry(countries[6]);
        reg.selectCity(cities[6]);
        reg.fillPhoneNumber(phoneNumbers[6], countries[6]);
        reg.confirmAndWait();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[6], lastNames[6], emails[6], phoneNumbers[6], countries[6], cities[6]);
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online")
    void t00100() {
        log.openMainPage();
        log.popupSkip();
        log.account(0);
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[0]);
        pbl.enterServiceDescription(serviceDescriptions[0]);
        pbl.setDuration(serviceDurations[0]);
        pbl.setPriceFixed(servicePrices[0], randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[0]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceNames[0], serviceDurations[0], serviceDescriptions[0]);
        pbl.checkPrice(servicePrices[0]);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Client's place")
    void t00101() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[1]);
        pbl.enterServiceDescription(serviceDescriptions[1]);
        pbl.setDuration(serviceDurations[1]);
        pbl.setPriceFixed(servicePrices[1], randomCurrency);
        pbl.selectServiceLocation(client);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[1]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(service2Country, service2City, service2Address);
        pbl.fillServiceDistance(service2Distance);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(serviceNames[1], serviceDurations[1], serviceDescriptions[1], service2Country, service2City, service2Address);
        pbl.checkPrice(servicePrices[1]);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Professional's place")
    void t00102() {
        log.openMainPage();
        log.popupSkip();
        log.account(2);
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[2]);
        pbl.enterServiceDescription(serviceDescriptions[2]);
        pbl.setDuration(serviceDurations[2]);
        pbl.setPriceFixed(servicePrices[2], randomCurrency);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[2]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(service3Country, service3City, service3Address);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(serviceNames[2], serviceDurations[2], serviceDescriptions[2], service3Country, service3City, service3Address);
        pbl.checkPrice(servicePrices[2]);
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
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[3]);
        pbl.enterServiceDescription(serviceDescriptions[3]);
        pbl.setDuration(serviceDurations[3]);
        pbl.setPriceFixed(servicePrices[3], randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillEmail(emails[3]);
        pbl.fillUserInfo(firstNames[3], lastNames[3], passwords[3], countries[3], cities[3]);
        pbl.clickFourthStep();

        pbl.clickFifthStep();

        pbl.fillSpecialization(specializations[3]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceNames[3], serviceDurations[3], serviceDescriptions[3]);
        pbl.checkPrice(servicePrices[3]);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online (2)")
    void t00104() {
        log.openMainPage();
        log.popupSkip();
        log.account(6);
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[6]);
        pbl.enterServiceDescription(serviceDescriptions[6]);
        pbl.setDuration(serviceDurations[6]);
        pbl.setPriceFixed(servicePrices[6], randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[6]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceNames[6], serviceDurations[6], serviceDescriptions[6]);
        pbl.checkPrice(servicePrices[6]);
        pbl.publishService();
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online")
    void t00200() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[0]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking client's place")
    void t00201() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[1]);
        bkn.verifyServiceSearch(firstNames[1], lastNames[1], serviceNames[1], servicePrices[1]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[1], servicePrices[1], serviceDurations[1], firstNames[1], lastNames[1], serviceDescriptions[1]);
        bkn.verifyServiceLocation("Client's place");
        bkn.verifyServiceGeo(service2Country, service2City, service2Address);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[1]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[1], serviceNames[1], servicePrices[1], serviceDurations[1]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking master's place")
    void t00202() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[2]);
        bkn.verifyServiceSearch(firstNames[2], lastNames[2], serviceNames[2], servicePrices[2]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[2], servicePrices[2], serviceDurations[2], firstNames[2], lastNames[2], serviceDescriptions[2]);
        bkn.verifyServiceLocation("Professional's place");
        bkn.verifyServiceGeo(service3Country, service3City, service3Address);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[2]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[2], serviceNames[2], servicePrices[2], serviceDurations[2]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online (2)")
    void t00203() {
        log.openMainPage();
        log.popupSkip();
        log.account(5);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[6]);
        bkn.verifyServiceSearch(firstNames[6], lastNames[6], serviceNames[6], servicePrices[6]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[6], servicePrices[6], serviceDurations[6], firstNames[6], lastNames[6], serviceDescriptions[6]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[6]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[6], serviceNames[6], servicePrices[6], serviceDurations[6]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online (3)")
    void t00204() {
        log.openMainPage();
        log.popupSkip();
        log.account(5);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[3]);
        bkn.verifyServiceSearch(firstNames[3], lastNames[3], serviceNames[3], servicePrices[3]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[3], servicePrices[3], serviceDurations[3], firstNames[3], lastNames[3], serviceDescriptions[3]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[3]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[3], serviceNames[3], servicePrices[3], serviceDurations[3]);
    }


    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check orders from user side")
    void t00300() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
        ord.checkOrderOutbox(firstNames[1], serviceNames[1], servicePrices[1], serviceDurations[1]);
        ord.checkOrderOutbox(firstNames[2], serviceNames[2], servicePrices[2], serviceDurations[2]);

    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: online")
    void t00301() {
        log.openMainPage();
        log.popupSkip();
        log.account(0);
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(firstNames[4], servicePrices[0], serviceDurations[0]);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: client's place")
    void t00302() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(firstNames[4], servicePrices[1], serviceDurations[1]);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: master's place")
    void t00303() {
        log.openMainPage();
        log.popupSkip();
        log.account(2);
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(firstNames[4], servicePrices[2], serviceDurations[2]);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Client side order cancel")
    void t00304() {
        log.openMainPage();
        log.popupSkip();
        log.account(5);
        log.forceEN();
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[3], serviceNames[3], servicePrices[3], serviceDurations[3]);
        ord.discardOrderClient("I don't like the service");
        ord.checkDiscardOrderOutbox(firstNames[3], serviceNames[3]);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master side order cancel")
    void t00305() {
        log.openMainPage();
        log.popupSkip();
        log.account(6);
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(firstNames[5], servicePrices[6], serviceDurations[6]);
        ord.discardOrder();
        ord.checkDiscardOrderInbox(firstNames[5], servicePrices[6], serviceDurations[6]);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with 10 words")
    void t00400() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.choseRating(randomRating);
        rev.sendReviewText(reviewText1);
        rev.pressSend();
        rev.verifyReview(firstNames[2] + " " + lastNames[2], firstNames[4], reviewText1);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with a lot of characters")
    void t00401() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(2);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.choseRating(randomRating);
        rev.sendReviewText(reviewText2);
        rev.pressSend();
        rev.verifyReview(firstNames[1] + " " + lastNames[1], firstNames[4], reviewText2);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review add to favorites in the process")
    void t00402() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(3);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.addToFavorite();
        rev.choseRating(randomRating);
        String rating = valueOf(randomRating);
        rev.sendReviewText(reviewText3 + " " + reviewText4);
        rev.pressSend();
        rev.verifyReview(firstNames[0] + " " + lastNames[0], firstNames[4], reviewText3 + " " + reviewText4);

        rev.clickMenuMain();
        rev.openBookmarksMenu();
        rev.verifyBookmark(firstNames[0] + " " + lastNames[0], rating);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review master answers")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Review answer")
    void t00403() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
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
    @DisplayName("Conversation test: step 1 out of 3")
    void t00500() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSentOrders();
        ord.tabArchivedOrdersOutbox();
        ord.clickProfessionalsName();
        pp.clickChat();
        msg.sendMessage(testMessage1);
        msg.checkMessage(testMessage1);
    }

    @Test
    @Feature("Messages")
    @Owner("Egor Khlebnikov")
    @Story("2 users chatting tests")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Conversation test: step 2 out of 3")
    void t00501() {
        log.openMainPage();
        log.popupSkip();
        log.account(2);
        log.forceEN();
        topBar.clickChat();
        msg.findUserChat(firstNames[4]);
        msg.selectUser();
        msg.checkMessage(testMessage1);
        msg.sendMessage(testMessage2);
        msg.checkMessage(testMessage2);
    }

    @Test
    @Feature("Messages")
    @Owner("Egor Khlebnikov")
    @Story("2 users chatting tests")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Conversation test: step 3 out of 3")
    void t00502() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        topBar.clickChat();
        msg.findUserChat(firstNames[2]);
        msg.selectUser();
        msg.checkMessage(testMessage1);
        msg.checkMessage(testMessage2);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Remove master from favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Remove master from favorites")
    void t00600() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();

        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(firstNames[0] + " " + lastNames[0]);

        fav.removeBookmark();
        fav.verifyDelBookmark(firstNames[0] + " " + lastNames[0]);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from search results")
    void t00601() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNames[0]);
        fav.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        fav.clickFavSearch();
        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(firstNames[0] + " " + lastNames[0]);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from master's profile")
    void t00602() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNames[1]);
        fav.verifyServiceSearch(firstNames[1], lastNames[1], serviceNames[1], servicePrices[1]);
        fav.selectMasterSearch();
        fav.clickFavMasterProfile();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(firstNames[1] + " " + lastNames[1], cities[1]);
        fav.verifyBookmarkOnline(firstNames[0] + " " + lastNames[0]);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from the service info")
    void t00603() {
        log.openMainPage();
        log.popupSkip();
        log.account(4);
        log.forceEN();
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNames[2]);
        fav.verifyServiceSearch(firstNames[2], lastNames[2], serviceNames[2], servicePrices[2]);
        fav.selectServiceSearch();
        fav.clickFavServiceInfo();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(firstNames[2] + " " + lastNames[2], cities[2]);
        fav.verifyBookmark(firstNames[1] + " " + lastNames[1], cities[1]);
        fav.verifyBookmark(firstNames[0] + " " + lastNames[0], cities[0]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking from the main page: guest")
    void t00700() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(serviceNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();

        bkn.pickTheDate(next2Days);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();

        bkn.selectNewUser();
        bkn.acceptConfirmation();

        reg.fillUserFirstName(firstNames[7]);
        reg.fillUserLastName(lastNames[7]);
        reg.fillEmail(emails[7]);
        reg.choosePassword(passwords[7]);

        reg.selectCountry(countries[7]);
        reg.selectCity(cities[7]);
        reg.confirmAndWait();

        bkn.placeOrder();

        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[0]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking: guest")
    void t00701() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(serviceNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();

        bkn.pickTheDate(next3Days);
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();

        bkn.selectNewUser();
        bkn.acceptConfirmation();

        reg.fillUserFirstName(firstNames[8]);
        reg.fillUserLastName(lastNames[8]);
        reg.fillEmail(emails[8]);
        reg.choosePassword(passwords[8]);

        reg.selectCountry(countries[8]);
        reg.selectCity(cities[8]);
        reg.confirmAndWait();

        bkn.placeOrder();

        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[0]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: professional name")
    void t00800() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();

        search.searchMainNoQuotes(firstNames[0] + " " + lastNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: service name")
    void t00801() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(serviceNames[1]);
        bkn.verifyServiceSearch(firstNames[1], lastNames[1], serviceNames[1], servicePrices[1]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[1], servicePrices[1], serviceDurations[1], firstNames[1], lastNames[1], serviceDescriptions[1]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: speciality")
    void t00802() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(specializations[2]);
        bkn.verifyServiceSearch(firstNames[2], lastNames[2], serviceNames[2], servicePrices[2]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[2], servicePrices[2], serviceDurations[2], firstNames[2], lastNames[2], serviceDescriptions[2]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: professional name")
    void t00803() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        search.searchNoQuotes(firstNames[0] + " " + lastNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: service name")
    void t00804() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(serviceNames[1]);
        bkn.verifyServiceSearch(firstNames[1], lastNames[1], serviceNames[1], servicePrices[1]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[1], servicePrices[1], serviceDurations[1], firstNames[1], lastNames[1], serviceDescriptions[1]);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: speciality")
    void t00805() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(specializations[2]);
        bkn.verifyServiceSearch(firstNames[2], lastNames[2], serviceNames[2], servicePrices[2]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[2], servicePrices[2], serviceDurations[2], firstNames[2], lastNames[2], serviceDescriptions[2]);
    }
}