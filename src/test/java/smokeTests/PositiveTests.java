package smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositiveTests extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00000() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user1FirstName);
        reg.fillUserLastName(user1LastName);
        reg.fillEmail(testUser1);
        reg.choosePassword(testPassword1);
        reg.fillPhoneNumber(user1PhoneNumber, user1Country);
        reg.selectCountry(user1Country);
        reg.selectCity(user1City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user1FirstName, user1LastName, testUser1, user1PhoneNumber, user1Country, user1City);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00001() {
        log.popupSelect(user2Country, user2City);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user2FirstName);
        reg.fillUserLastName(user2LastName);
        reg.fillEmail(testUser2);
        reg.choosePassword(testPassword2);
        reg.fillPhoneNumber(user2PhoneNumber, user2Country);
        reg.selectCountry(user2Country);
        reg.selectCity(user2City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user2FirstName, user2LastName, testUser2, user2PhoneNumber, user2Country, user2City);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00002() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user3FirstName);
        reg.fillUserLastName(user3LastName);
        reg.fillEmail(testUser3);
        reg.choosePassword(testPassword3);
        reg.fillPhoneNumber(user3PhoneNumber, user3Country);
        reg.selectCountry(user3Country);
        reg.selectCity(user3City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user3FirstName, user3LastName, testUser3, user3PhoneNumber, user3Country, user3City);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Minimal registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic Positive User Registration")
    void t00003() {
        log.popupSelect(user5Country, user5City);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user5FirstName);
        reg.fillEmail(testUser5);
        reg.choosePassword(testPassword5);
        reg.selectCountry(user5Country);
        reg.selectCity(user5City);
        reg.confirm();
        reg.verifyRegistrationDataBasic(user5FirstName, testUser5, user5Country, user5City);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00004() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user6FirstName);
        reg.fillUserLastName(user6LastName);
        reg.fillEmail(testUser6);
        reg.choosePassword(testPassword6);
        reg.fillPhoneNumber(user6PhoneNumber, user6Country);
        reg.selectCountry(user6Country);
        reg.selectCity(user6City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user6FirstName, user6LastName, testUser6, user6PhoneNumber, user6Country, user6City);
    }

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00005() {
        log.popupSelect(user7Country, user7City);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(user7FirstName);
        reg.fillUserLastName(user7LastName);
        reg.fillEmail(testUser7);
        reg.choosePassword(testPassword7);
        reg.fillPhoneNumber(user7PhoneNumber, user7Country);
        reg.selectCountry(user7Country);
        reg.selectCity(user7City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user7FirstName, user7LastName, testUser7, user7PhoneNumber, user7Country, user7City);
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online")
    void t00100() {
        log.popupSkip();
        log.account1();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service1Name);
        pbl.enterServiceDescription(service1Description);
        pbl.setDuration(service1DurationDays, service1DurationHours, service1DurationMinutes);
        pbl.setPriceFixed(service1Price, rub);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(service1Specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(service1Name, service1TotalDuration, service1Description);
        pbl.checkPrice(service1Price);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Client's place")
    void t00101() {
        log.popupSkip();
        log.account2();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service2Name);
        pbl.enterServiceDescription(service2Description);
        pbl.setDuration(service2DurationDays, service2DurationHours, service2DurationMinutes);
        pbl.setPriceFixed(service2Price, rub);
        pbl.selectServiceLocation(client);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(service2Specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(service2Country, service2City, service2Address);
        pbl.fillServiceDistance(service2Distance);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(service2Name, service2TotalDuration, service2Description, service2Country, service2City, service2Address);
        pbl.checkPrice(service2Price);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Professional's place")
    void t00102() {
        log.popupSkip();
        log.account3();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service3Name);
        pbl.enterServiceDescription(service3Description);
        pbl.setDuration(service3DurationDays, service3DurationHours, service3DurationMinutes);
        pbl.setPriceFixed(service3Price, rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(service3Specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(service3Country, service3City, service3Address);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormWithAddress(service3Name, service3TotalDuration, service3Description, service3Country, service3City, service3Address);
        pbl.checkPrice(service3Price);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing with no account, service location: Online")
    void t00103() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service4Name);
        pbl.enterServiceDescription(service4Description);
        pbl.setDuration(service4DurationDays, service4DurationHours, service4DurationMinutes);
        pbl.setPriceFixed(service4Price, rub);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillEmail(testUser4);
        pbl.fillUserInfo(user4FirstName, user4LastName, testPassword4, user4Country, user4City);
        pbl.clickFourthStep();

        pbl.clickFifthStep();

        pbl.fillSpecialization(service4Specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(service4Name, service4TotalDuration, service4Description);
        pbl.checkPrice(service4Price);
        pbl.publishService();
    }

    @Test
    @Feature("Service Publish")
    @Owner("Egor Khlebnikov")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online (2)")
    void t00104() {
        log.popupSkip();
        log.account7();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service7Name);
        pbl.enterServiceDescription(service7Description);
        pbl.setDuration(service7DurationDays, service7DurationHours, service7DurationMinutes);
        pbl.setPriceFixed(service7Price, eur);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(service7Specialization);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(service7Name, service7TotalDuration, service7Description);
        pbl.checkPrice(service7Price);
        pbl.publishService();
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online")
    void t00200() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.closeFilters();
        bkn.findService(service1Name);
        bkn.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service1Name, service1Price, service1TotalDuration, user1FirstName, user1LastName, service1Description);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.clickForward();
        bkn.clickAccept();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service1Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user1FirstName, service1Name, service1Price, service1TotalDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking client's place")
    void t00201() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.closeFilters();
        bkn.findService(service2Name);
        bkn.verifyServiceSearch(user2FirstName, user2LastName, service2Name, service2Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service2Name, service2Price, service2TotalDuration, user2FirstName, user2LastName, service2Description);
        bkn.verifyServiceLocation("Client's place");
        bkn.verifyServiceGeo(service2Country, service2City, service2Address);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.bookTime(1100);
        bkn.clickForward();
        bkn.clickAccept();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service2Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user2FirstName, service2Name, service2Price, service2TotalDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking master's place")
    void t00202() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.closeFilters();
        bkn.findService(service3Name);
        bkn.verifyServiceSearch(user3FirstName, user3LastName, service3Name, service3Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service3Name, service3Price, service3TotalDuration, user3FirstName, user3LastName, service3Description);
        bkn.verifyServiceLocation("Professional's place");
        bkn.verifyServiceGeo(service3Country, service3City, service3Address);
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.clickForward();
        bkn.clickAccept();
        bkn.selectAddress();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service3Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user3FirstName, service3Name, service3Price, service3TotalDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online (2)")
    void t00203() {
        log.popupSkip();
        log.account6();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.closeFilters();
        bkn.findService(service7Name);
        bkn.verifyServiceSearch(user7FirstName, user7LastName, service7Name, service7Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service7Name, service7Price, service7TotalDuration, user7FirstName, user7LastName, service7Description);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.clickForward();
        bkn.clickAccept();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service7Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user7FirstName, service7Name, service7Price, service7TotalDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online (3)")
    void t00204() {
        log.popupSkip();
        log.account6();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.closeFilters();
        bkn.findService(service4Name);
        bkn.verifyServiceSearch(user4FirstName, user4LastName, service4Name, service4Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service4Name, service4Price, service4TotalDuration, user4FirstName, user4LastName, service4Description);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        bkn.bookTime(1100);
        bkn.clickForward();
        bkn.clickAccept();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service4Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user4FirstName, service4Name, service4Price, service4TotalDuration);
    }


    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check orders from user side")
    void t00300() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        topBar.clickMyOrders();
        ord.checkOrderOutbox(user1FirstName, service1Name, service1Price, service1TotalDuration);
        ord.checkOrderOutbox(user2FirstName, service2Name, service2Price, service2TotalDuration);
        ord.checkOrderOutbox(user3FirstName, service3Name, service3Price, service3TotalDuration);

    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: online")
    void t00301() {
        log.popupSkip();
        log.account1();
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(user5FirstName, service1Price, service1TotalDuration);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: client's place")
    void t00302() {
        log.popupSkip();
        log.account2();
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(user5FirstName, service2Price, service2TotalDuration);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: master's place")
    void t00303() {
        log.popupSkip();
        log.account3();
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(user5FirstName, service3Price, service3TotalDuration);
        ord.completeOrder();
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Client side order cancel")
    void t00304() {
        log.popupSkip();
        log.account6();
        log.forceEN();
        topBar.clickMyOrders();
        ord.checkOrderOutbox(user4FirstName, service4Name, service4Price, service4TotalDuration);
        ord.discardOrderClient("I don't like the service");
        ord.checkDiscardOrderOutbox(user4FirstName, service4Name);
    }

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Order cancel")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master side order cancel")
    void t00305() {
        log.popupSkip();
        log.account7();
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(user6FirstName, service7Price, service7TotalDuration);
        ord.discardOrder();
        ord.checkDiscardOrderInbox(user6FirstName, service7Price, service7TotalDuration);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with 10 words")
    void t00400() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickAllReviewsLink();
        rev.clickSendReviewButton();
        rev.choseRating(4);
        rev.sendReviewText(reviewText1);
        rev.pressSend();
        rev.verifyReview(user3FirstName + " " + user3LastName, user5FirstName, reviewText1);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review with a lot of characters")
    void t00401() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(2);
        rev.clickReviewTab();
        rev.clickAllReviewsLink();
        rev.clickSendReviewButton();
        rev.choseRating(1);
        rev.sendReviewText(reviewText2);
        rev.scrollDown();
        rev.pressSend();
        rev.verifyReview(user2FirstName + " " + user2LastName, user5FirstName, reviewText2);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review add to favorites in the process")
    void t00402() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(3);
        rev.clickReviewTab();
        rev.clickAllReviewsLink();
        rev.clickSendReviewButton();
        rev.addToFavorite();
        rev.choseRating(5);
        rev.sendReviewText(reviewText3 + " " + reviewText4);
        rev.pressSend();
        rev.verifyReview(user1FirstName + " " + user1LastName, user5FirstName, reviewText3 + " " + reviewText4);

        rev.clickMenuMain();
        rev.openBookmarksMenu();
        rev.verifyBookmark(user1FirstName + " " + user1LastName);
    }

    @Test
    @Feature("Reviews")
    @Owner("Egor Khlebnikov")
    @Story("Review master answers")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Review answer")
    void t00403() {
        log.popupSkip();
        log.account2();
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
        log.popupSkip();
        log.account5();
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
        log.popupSkip();
        log.account3();
        log.forceEN();
        topBar.clickChat();
        msg.findUserChat(user5FirstName);
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
        log.popupSkip();
        log.account5();
        log.forceEN();
        topBar.clickChat();
        msg.findUserChat(user3FirstName);
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
        log.popupSkip();
        log.account5();
        log.forceEN();

        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(user1FirstName + " " + user1LastName);

        fav.removeBookmark();
        fav.verifyDelBookmark(user1FirstName + " " + user1LastName);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from search results")
    void t00601() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSearch();
        fav.closeFilters();
        fav.findService(service1Name);
        fav.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        fav.clickFavSearch();
        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(user1FirstName + " " + user1LastName);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from master's profile")
    void t00602() {
        log.popupSkip();
        log.account5();
        log.forceEN();

        sideMenu.clickSearch();
        fav.closeFilters();
        fav.findService(service2Name);
        fav.verifyServiceSearch(user2FirstName, user2LastName, service2Name, service2Price);
        fav.selectMasterSearch();
        fav.clickFavMasterProfile();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(user2FirstName + " " + user2LastName, user2City);
        fav.verifyBookmarkOnline(user1FirstName + " " + user1LastName);
    }

    @Test
    @Feature("Favorites")
    @Owner("Egor Khlebnikov")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from the service info")
    void t00603() {
        log.popupSkip();
        log.account5();
        log.forceEN();
        sideMenu.clickSearch();
        fav.closeFilters();
        fav.findService(service3Name);
        fav.verifyServiceSearch(user3FirstName, user3LastName, service3Name, service3Price);
        fav.selectServiceSearch();
        fav.clickFavServiceInfo();
        sideMenu.clickBookmarks();
        fav.verifyBookmark(user3FirstName + " " + user3LastName, user3City);
        fav.verifyBookmark(user2FirstName + " " + user2LastName, user2City);
        fav.verifyBookmark(user1FirstName + " " + user1LastName, user1City);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking from the main page: guest")
    void t00700() {
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(service1Name);
        bkn.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service1Name, service1Price, service1TotalDuration, user1FirstName, user1LastName, service1Description);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();

        bkn.pickTheDate(next2Days);
        bkn.bookTime(1100);
        bkn.clickForward();

        bkn.selectNewUser();
        bkn.clickAccept();

        reg.fillUserFirstName(user8FirstName);
        reg.fillUserLastName(user8LastName);
        reg.fillEmail(testUser8);
        reg.choosePassword(testPassword8);

        reg.selectCountry(user8Country);
        reg.selectCity(user8City);
        reg.confirm();

        bkn.placeOrder(); //todo

        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service1Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user1FirstName, service1Name, service1Price, service1TotalDuration);
    }

    @Test
    @Feature("Booking")
    @Owner("Egor Khlebnikov")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking: guest")
    void t00701() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(service1Name);
        bkn.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service1Name, service1Price, service1TotalDuration, user1FirstName, user1LastName, service1Description);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking();
        bkn.clickOrder();

        bkn.pickTheDate(next3Days);
        bkn.bookTime(1100);
        bkn.clickForward();

        bkn.selectNewUser();
        bkn.clickAccept();

        reg.fillUserFirstName(user9FirstName);
        reg.fillUserLastName(user9LastName);
        reg.fillEmail(testUser9);
        reg.choosePassword(testPassword9);

        reg.selectCountry(user9Country);
        reg.selectCity(user9City);
        reg.confirm();

        bkn.placeOrder(); //todo

        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service1Name);
        bkn.clickOrders();
        ord.checkOrderOutbox(user1FirstName, service1Name, service1Price, service1TotalDuration);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: professional name")
    void t00800() {
        log.popupSkip();
        log.forceEN();

        sch.searchMain(user1FirstName + " " + user1LastName);
        bkn.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service1Name, service1Price, service1TotalDuration, user1FirstName, user1LastName, service1Description);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: service name")
    void t00801() {
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(service2Name);
        bkn.verifyServiceSearch(user2FirstName, user2LastName, service2Name, service2Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service2Name, service2Price, service2TotalDuration, user2FirstName, user2LastName, service2Description);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: speciality")
    void t00802() {
        log.popupSkip();
        log.forceEN();

        bkn.findServiceMainPage(service3Specialization);
        bkn.verifyServiceSearch(user3FirstName, user3LastName, service3Name, service3Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service3Name, service3Price, service3TotalDuration, user3FirstName, user3LastName, service3Description);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: professional name")
    void t00803() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        sch.search(user1FirstName + " " + user1LastName);
        bkn.verifyServiceSearch(user1FirstName, user1LastName, service1Name, service1Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service1Name, service1Price, service1TotalDuration, user1FirstName, user1LastName, service1Description);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: service name")
    void t00804() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(service2Name);
        bkn.verifyServiceSearch(user2FirstName, user2LastName, service2Name, service2Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service2Name, service2Price, service2TotalDuration, user2FirstName, user2LastName, service2Description);
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: speciality")
    void t00805() {
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        bkn.findService(service3Specialization);
        bkn.verifyServiceSearch(user3FirstName, user3LastName, service3Name, service3Price);
        bkn.chooseService();
        bkn.verifyServiceBase(service3Name, service3Price, service3TotalDuration, user3FirstName, user3LastName, service3Description);
    }
}