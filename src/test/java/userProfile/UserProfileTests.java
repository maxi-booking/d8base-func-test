package userProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class UserProfileTests extends config.TestBase {

    Registration reg = new Registration();
    ServicePublish pbl = new ServicePublish();
    Booking bkn = new Booking();
    Orders ord = new Orders();
    Messages msg = new Messages();
    Favorites fav = new Favorites();
    Reviews rev = new Reviews();
    Search sch = new Search();
    LogIn log = new LogIn();
    UserProfile usr = new UserProfile();

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00000() {
        log.popupSkip();
        log.clickSideMenu();
        reg.openPageEN();
        reg.fillUserFirstName(user10FirstName);
        reg.fillUserLastName(user10LastName);
        reg.fillEmail(testUser10);
        reg.choosePassword(testPassword10);
        reg.fillPhoneNumber(user10PhoneNumber, user10Country);
        reg.selectCountry(user10Country);
        reg.selectCity(user10City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user10FirstName, user10LastName, testUser10, user10PhoneNumber, user10Country, user10City);
    }

    @Test
    @Feature("Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Profile verification")
    void t00001() {
        log.popupSkip();
        log.account10();
        log.langEN();

        log.clickSideMenu();
        usr.openUserProfile();
        usr.verifyProfile(user10FirstName, user10LastName,testUser10,user10Country, user10City);
        usr.openUserProfileMain();
//        usr.verifyProfileMain(user10FirstName, user10LastName,testUser10);
        usr.clickBackMain();
        usr.openUserProfileAddress();
        usr.verifyProfileAddress(user10Country, user10City);
        usr.clickBackAddress();
        usr.verifyProfile(user10FirstName, user10LastName,testUser10,user10Country, user10City);
    }


}
