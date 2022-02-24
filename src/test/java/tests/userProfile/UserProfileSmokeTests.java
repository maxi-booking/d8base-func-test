package tests.userProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static api.Registration.locations;
import static api.Registration.registration;

@Execution(value = ExecutionMode.SAME_THREAD)
public class UserProfileSmokeTests extends config.TestBase {

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration for User Profile test")
    void t00000() {
        String accessToken = registration(firstNames[9], lastNames[9], emails[9], passwords[9], countries[9], phoneNumbers[9]);
        locations(accessToken, countries[9], cities[9]);
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[9], lastNames[9], emails[9], phoneNumbers[9], countries[9], cities[9]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: functionality verification")
    void t00001() {
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();

        up.verifyProfile(firstNames[9], lastNames[9], emails[9], countries[9], cities[9]);
        up.openUserProfileMain();
        up.verifyProfileMainMin(firstNames[9], lastNames[9], emails[9]);
        up.clickBackMain();
        up.clickEditContacts(0);
        up.verifyContactDefault();
        up.clickBackContacts();
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(countries[9], cities[9]);
        up.addressClickBack();
        up.openUserProfileAbout();
        up.verifyAboutDefault();
        up.clickBackAbout();
        up.verifyProfile(firstNames[9], lastNames[9], emails[9], countries[9], cities[9]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: main - change all the info and verify")
    void t00002() {
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();

        up.openUserProfileMain();
        up.inputFirstName(userFirstName9);
        up.inputLastName(userLastName9);
        up.inputPatronymic(user10PatronymicNew);
        up.selectGenderMale();
        up.fillPhoneNumber(userPhoneNumber9, userCountry9);
        up.inputEmail(testUser10New);
        up.clickSaveMain();
        up.verifyProfile(userFirstName9, userLastName9, testUser10New, countries[9], cities[9]);
        up.checkVerificationEmailSent(testUser10New);
        up.openUserProfileMain();
        up.verifyGenderMale();
        up.verifyProfileMainMax(userFirstName9, userLastName9, user10PatronymicNew, userPhoneNumber9, emails[9]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: contacts - change info, add new and verify")
    void t00003() {
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();

        up.clickEditContacts(0);
        up.verifySelectContactDefault();
        up.verifyEnterYourContact(emptySpace);
        up.clickBackContacts();
        up.clickAddNewContact();
        up.verifySelectContactDefault();
        up.verifyEnterYourContact(emptySpace);
        up.clickBackContacts();

        up.clickEditContacts(0);
        up.enterContact(userPhoneNumber9);
        up.saveContact();
        up.verifyContactExists(userPhoneNumber9);

        up.clickAddNewContact();
        up.selectContact("E-mail");
        up.enterContact(testUser10New);
        up.saveContact();
        up.verifyContactExists(userPhoneNumber9);
        up.verifyContactExists(testUser10New);

        up.clickEditContacts(1);
        up.enterContact(emails[9]);
        up.saveContact();
        up.verifyContactExists(userPhoneNumber9);
        up.verifyContactRemoved(testUser10New);
        up.verifyContactExists(emails[9]);

        up.clickEditContacts(1);
        up.removeContact();
        up.verifyContactExists(userPhoneNumber9);
        up.verifyContactRemoved(testUser10New);
        up.verifyContactRemoved(emails[9]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: address - change info and verify")
    void t00004() {
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();
        up.verifyProfileAddressExists(countries[9], cities[9]);
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(countries[9], cities[9]);
        up.addressClickBack();

        up.openUserProfileLocationEdit(0);
        up.addressSelectCountry(userCountry9);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(userCity9);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(userCountry9, user10RegionNew, user10SubregionNew, userCity9, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.addressClickBack();
        up.verifyProfileAddressExists(countries[9], cities[9]);

        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(countries[9], cities[9]);
        up.addressSelectCountry(userCountry9);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(userCity9);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(userCountry9, user10RegionNew, user10SubregionNew, userCity9, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.addressClickSave();
        up.verifyProfileAddressExists(userCountry9, userCity9);

        up.clickAddNewAddress();
        up.verifyAddressDefault(userCountry9, user10RegionNew, userCity9);
        up.addressSelectCountry(userCountry9_2);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(userCity9_2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(userCountry9_2, user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.addressClickBack();
        up.verifyProfileAddressExists(countries[9], cities[9]);

        up.clickAddNewAddress();
        up.verifyAddressDefault(userCountry9, user10RegionNew, userCity9);
        up.addressSelectCountry(userCountry9_2);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(userCity9_2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(userCountry9_2, user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.addressClickSave();
        up.verifyProfileAddressExists(userCountry9_2, userCity9_2);

        up.openUserProfileLocationEdit(1);
        up.verifyAddressFull(userCountry9_2, user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.removeAddress();
        up.verifyAddressRemoved(userCountry9_2, userCity9_2);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: about - change info and verify")
    void t00005() {
        log.openMainPage();
        log.popupSkip();
        log.account(9);
        log.forceEN();
        sideMenu.clickProfile();
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