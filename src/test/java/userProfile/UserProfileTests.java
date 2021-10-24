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
    UserProfile uprof = new UserProfile();
    MasterProfile mprof = new MasterProfile();

    @Test
    @Feature("User Registration")
    @Owner("Egor Khlebnikov")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration")
    void t00000() {
        log.popupSkip();
        log.forceEN();
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
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile functionality verification")
    void t00001() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        uprof.openUserProfile();

        uprof.verifyProfile(user10FirstName, user10LastName,testUser10,user10Country, user10City);
        uprof.openUserProfileMain();
        uprof.clickBackMain();
        uprof.openUserProfileAddress();
        uprof.verifyProfileAddress(user10Country, user10City);
        uprof.clickBackAddress();
        uprof.verifyProfile(user10FirstName, user10LastName,testUser10,user10Country, user10City);
    }

    @Test
    @Feature("Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: main - change all the info and verify")
    void t00002() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        uprof.openUserProfile();

        uprof.openUserProfileMain();
        uprof.inputFirstName(user10FirstNameNew);
        uprof.inputLastName(user10LastNameNew);
        uprof.inputPatronymic(user10PatronymicNew);
        uprof.selectGenderMale();
        uprof.fillPhoneNumber(user10PhoneNumberNew, user10CountryNew);
        uprof.inputEmail(testUser10New);
        uprof.clickSaveMain();
        uprof.verifyProfile(user10FirstNameNew, user10LastNameNew,testUser10New,user10Country, user10City);
        uprof.checkVerificationEmailSent(testUser10New);
        uprof.openUserProfileMain();
        uprof.verifyGenderMale();
        uprof.verifyProfileMainMax(user10FirstNameNew, user10LastNameNew, user10PatronymicNew, user10PhoneNumberNew, testUser10);
    }

    @Test
    @Feature("Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: contacts - change info, add new and verify")
    void t00003() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        uprof.openUserProfile();

        uprof.clickEditContacts(0);
        uprof.verifySelectContactDefault();
        uprof.verifyEnterYourContact(empty);
        uprof.clickBackContacts();
        uprof.clickAddNewContact();
        uprof.verifySelectContactDefault();
        uprof.verifyEnterYourContact(empty);
        uprof.clickBackContacts();

        uprof.clickEditContacts(0);
        uprof.enterContact(user10PhoneNumberNew);
        uprof.saveContact();
        uprof.verifyContactExists(user10PhoneNumberNew);

        uprof.clickAddNewContact();
        uprof.selectContact("E-mail");
        uprof.enterContact(testUser10New);
        uprof.saveContact();
        uprof.verifyContactExists(user10PhoneNumberNew);
        uprof.verifyContactExists(testUser10New);

        uprof.clickEditContacts(1);
        uprof.enterContact(testUser10);
        uprof.saveContact();
        uprof.verifyContactExists(user10PhoneNumberNew);
        uprof.verifyContactRemoved(testUser10New);
        uprof.verifyContactExists(testUser10);

        uprof.clickEditContacts(1);
        uprof.removeContact();
        uprof.verifyContactExists(user10PhoneNumberNew);
        uprof.verifyContactRemoved(testUser10New);
        uprof.verifyContactRemoved(testUser10);
    }

    @Test
    @Feature("Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: about - change info and verify")
    void t00004() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        uprof.openUserProfile();
        uprof.openUserProfileAbout();
        uprof.verifyAboutDefault();
        uprof.clickBackAbout();

        uprof.openUserProfileAbout();
        uprof.selectDateBirth(user10dateDD, user10dateMM, user10dateYYYY);
        uprof.selectNationality(user10Nationality);
        uprof.selectLanguage(user10Langage1);
        uprof.clickBackAbout();

        uprof.openUserProfileAbout();
        uprof.verifyAboutDefault();
        uprof.selectDateBirth(user10dateDD, user10dateMM, user10dateYYYY);
        uprof.selectNationality(user10Nationality);
        uprof.selectLanguage(user10Langage1);
        uprof.selectLanguage(user10Langage2);
        uprof.clickSaveAbout();
        uprof.verifyAbout(user10Nationality, user10Langage1, user10dateDD, user10dateMM, user10dateYYYY);
        uprof.verifyAboutExtraLanguage(user10Langage2);
    }
}
