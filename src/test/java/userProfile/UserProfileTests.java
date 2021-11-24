package userProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class UserProfileTests extends config.TestBase {

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration for User Profile test")
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
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: functionality verification")
    void t00001() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        up.openUserProfile();

        up.verifyProfile(user10FirstName, user10LastName,testUser10, user10Country, user10City);
        up.openUserProfileMain();
        up.verifyProfileMainMin(user10FirstName, user10LastName, testUser10);
        up.clickBackMain();
        up.clickEditContacts(0);
        up.verifyContactDefault();
        up.clickBackContacts();
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(user10Country, user10City);
        up.clickBackAddress();
        up.openUserProfileAbout();
        up.verifyAboutDefault();
        up.clickBackAbout();
        up.verifyProfile(user10FirstName, user10LastName,testUser10,user10Country, user10City);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: main - change all the info and verify")
    void t00002() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        up.openUserProfile();

        up.openUserProfileMain();
        up.inputFirstName(user10FirstNameNew);
        up.inputLastName(user10LastNameNew);
        up.inputPatronymic(user10PatronymicNew);
        up.selectGenderMale();
        up.fillPhoneNumber(user10PhoneNumberNew, user10CountryNew);
        up.inputEmail(testUser10New);
        up.clickSaveMain();
        up.verifyProfile(user10FirstNameNew, user10LastNameNew,testUser10New,user10Country, user10City);
        up.checkVerificationEmailSent(testUser10New);
        up.openUserProfileMain();
        up.verifyGenderMale();
        up.verifyProfileMainMax(user10FirstNameNew, user10LastNameNew, user10PatronymicNew, user10PhoneNumberNew, testUser10);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: contacts - change info, add new and verify")
    void t00003() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        up.openUserProfile();

        up.clickEditContacts(0);
        up.verifySelectContactDefault();
        up.verifyEnterYourContact(empty);
        up.clickBackContacts();
        up.clickAddNewContact();
        up.verifySelectContactDefault();
        up.verifyEnterYourContact(empty);
        up.clickBackContacts();

        up.clickEditContacts(0);
        up.enterContact(user10PhoneNumberNew);
        up.saveContact();
        up.verifyContactExists(user10PhoneNumberNew);

        up.clickAddNewContact();
        up.selectContact("E-mail");
        up.enterContact(testUser10New);
        up.saveContact();
        up.verifyContactExists(user10PhoneNumberNew);
        up.verifyContactExists(testUser10New);

        up.clickEditContacts(1);
        up.enterContact(testUser10);
        up.saveContact();
        up.verifyContactExists(user10PhoneNumberNew);
        up.verifyContactRemoved(testUser10New);
        up.verifyContactExists(testUser10);

        up.clickEditContacts(1);
        up.removeContact();
        up.verifyContactExists(user10PhoneNumberNew);
        up.verifyContactRemoved(testUser10New);
        up.verifyContactRemoved(testUser10);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: address - change info and verify")
    void t00004() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        up.openUserProfile();
        up.verifyProfileAddressExists(user10Country, user10City);
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(user10Country, user10City);
        up.clickBackAddress();

        up.openUserProfileLocationEdit(0);
        up.addressSelectCountry(user10CountryNew);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(user10CityNew);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(user10CountryNew, user10RegionNew, user10SubregionNew, user10CityNew, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.clickBackAddress();
        up.verifyProfileAddressExists(user10Country, user10City);

        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(user10Country, user10City);
        up.addressSelectCountry(user10CountryNew);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(user10CityNew);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(user10CountryNew, user10RegionNew, user10SubregionNew, user10CityNew, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.clickSaveAddress();
        up.verifyProfileAddressExists(user10CountryNew, user10CityNew);

        up.clickAddNewAddress();
        up.verifyAddressDefault(user10CountryNew, user10RegionNew, user10CityNew);
        up.addressSelectCountry(user10Country2);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(user10City2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(user10Country2, user10Region2, user10Subregion2, user10City2, user10District2, user10ZipCode2, user10Address2);
        up.clickBackAddress();
        up.verifyProfileAddressExists(user10Country, user10City);

        up.clickAddNewAddress();
        up.verifyAddressDefault(user10CountryNew, user10RegionNew, user10CityNew);
        up.addressSelectCountry(user10Country2);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(user10City2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(user10Country2, user10Region2, user10Subregion2, user10City2, user10District2, user10ZipCode2, user10Address2);
        up.clickSaveAddress();
        up.verifyProfileAddressExists(user10Country2, user10City2);

        up.openUserProfileLocationEdit(1);
        up.verifyAddressFull(user10Country2, user10Region2, user10Subregion2, user10City2, user10District2, user10ZipCode2, user10Address2);
        up.removeAddress();
        up.verifyAddressRemoved(user10Country2, user10City2);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: about - change info and verify")
    void t00005() {
        log.popupSkip();
        log.account10();
        log.forceEN();

        log.clickSideMenu();
        up.openUserProfile();
        up.openUserProfileAbout();
        up.verifyAboutDefault();
        up.clickBackAbout();

        up.openUserProfileAbout();
        up.selectDateBirth(user10dateDD, user10dateMM, user10dateYYYY);
        up.selectNationality(user10Nationality);
        up.selectLanguage(user10Language1);
        up.clickBackAbout();

        up.openUserProfileAbout();
        up.verifyAboutDefault();
        up.selectDateBirth(user10dateDD, user10dateMM, user10dateYYYY);
        up.selectNationality(user10Nationality);
        up.selectLanguage(user10Language1);
        up.selectLanguage(user10Language2);
        up.clickSaveAbout();
        up.verifyAbout(user10Nationality, user10Language1, user10dateDD, user10dateMM, user10dateYYYY);
        up.verifyAboutExtraLanguage(user10Language2);
    }
}
