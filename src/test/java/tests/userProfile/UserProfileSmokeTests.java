package tests.userProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static api.Registration.locations;
import static api.Registration.registration;
import static helpers.RegressionTestsHelpers.userReadyAPI;

public class UserProfileSmokeTests extends config.TestBase {

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("User Registration via API, verify data")
    void userProfileInfoVerification() {
        userReadyAPI(data);
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(data.firstName[0], data.lastName[0], data.email[0], data.phoneNumber[0], data.country[0], data.city[0]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: functionality verification")
    void userProfileFunctionalityVerification() {
        userReadyAPI(data);
        sideMenu.clickProfile();

        up.verifyProfile(data.firstName[0], data.lastName[0], data.email[0], data.country[0], data.city[0]);
        up.openUserProfileMain();
        up.verifyProfileMainMin(data.firstName[0], data.lastName[0], data.email[0]);
        up.clickBackMain();
        up.clickEditContacts(0);
        up.verifyContactDefault();
        up.clickBackContacts();
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(data.country[0], data.city[0]);
        up.addressClickBack();
        up.openUserProfileAbout();
        up.verifyAboutDefault();
        up.clickBackAbout();
        up.verifyProfile(data.firstName[0], data.lastName[0], data.email[0], data.country[0], data.city[0]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: main - change all the info and verify")
    void userProfileMainChangeTest() {
        userReadyAPI(data);
        sideMenu.clickProfile();

        up.openUserProfileMain();
        up.inputFirstName(data.firstName[1]);
        up.inputLastName(data.lastName[1]);
        up.inputPatronymic(data.patronymic[1]);
        up.selectGenderMale();
        up.fillPhoneNumber(data.phoneNumber[1], data.country[1]);
        up.inputEmail(data.email[1]);
        up.clickSaveMain();
        up.verifyProfile(data.firstName[1], data.lastName[1], data.email[1], data.country[0], data.city[0]);
        up.checkVerificationEmailSent(data.email[1]);
        up.openUserProfileMain();
        up.verifyGenderMale();
        up.verifyProfileMainMax(data.firstName[1], data.lastName[1], data.patronymic[1], data.phoneNumber[1], data.email[0]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: contacts - change info, add new and verify")
    void userProfileContactsChangeTest() {
        userReadyAPI(data);
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
        up.enterContact(data.phoneNumber[1]);
        up.saveContact();
        up.verifyContactExists(data.phoneNumber[1]);

        up.clickAddNewContact();
        up.selectContact("E-mail");
        up.enterContact(data.email[1]);
        up.saveContact();
        up.verifyContactExists(data.phoneNumber[1]);
        up.verifyContactExists(data.email[1]);

        up.clickEditContacts(1);
        up.enterContact(data.email[0]);
        up.saveContact();
        up.verifyContactExists(data.phoneNumber[1]);
        up.verifyContactRemoved(data.email[1]);
        up.verifyContactExists(data.email[0]);

        up.clickEditContacts(1);
        up.removeContact();
        up.verifyContactExists(data.phoneNumber[1]);
        up.verifyContactRemoved(data.email[1]);
        up.verifyContactRemoved(data.email[0]);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: address - change info and verify")
    void userProfileAddressChangeTest() {
        userReadyAPI(data);
        sideMenu.clickProfile();
        up.verifyProfileAddressExists(data.country[0], data.city[0]);
        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(data.country[0], data.city[0]);
        up.addressClickBack();

        up.openUserProfileLocationEdit(0);
        up.addressSelectCountry(data.country[1]);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(userCity9);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(data.country[1], user10RegionNew, user10SubregionNew, userCity9, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.addressClickBack();
        up.verifyProfileAddressExists(data.country[0], data.city[0]);

        up.openUserProfileLocationEdit(0);
        up.verifyProfileAddress(data.country[0], data.city[0]);
        up.addressSelectCountry(data.country[1]);
        up.addressSelectRegion(user10RegionNew);
        up.addressSelectSubregion(user10SubregionNew);
        up.addressSelectCity(userCity9);
        up.addressSelectDistrict(user10DistrictNew);
        up.addressSelectZipCode(user10ZipCodeNew);
        up.addressSelectAddress(user10AddressNew);
        up.verifyAddressFull(data.country[1], user10RegionNew, user10SubregionNew, userCity9, user10DistrictNew, user10ZipCodeNew, user10AddressNew);
        up.addressClickSave();
        up.verifyProfileAddressExists(data.country[1], userCity9);

        up.clickAddNewAddress();
        up.verifyAddressDefault(data.country[1], user10RegionNew, userCity9);
        up.addressSelectCountry(data.country[0]);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(userCity9_2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(data.country[0], user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.addressClickBack();
        up.verifyProfileAddressExists(data.country[0], data.city[0]);

        up.clickAddNewAddress();
        up.verifyAddressDefault(data.country[1], user10RegionNew, userCity9);
        up.addressSelectCountry(data.country[0]);
        up.addressSelectRegion(user10Region2);
        up.addressSelectSubregion(user10Subregion2);
        up.addressSelectCity(userCity9_2);
        up.addressSelectDistrict(user10District2);
        up.addressSelectZipCode(user10ZipCode2);
        up.addressSelectAddress(user10Address2);
        up.verifyAddressFull(data.country[0], user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.addressClickSave();
        up.verifyProfileAddressExists(data.country[0], userCity9_2);

        up.openUserProfileLocationEdit(1);
        up.verifyAddressFull(data.country[0], user10Region2, user10Subregion2, userCity9_2, user10District2, user10ZipCode2, user10Address2);
        up.removeAddress();
        up.verifyAddressRemoved(data.country[0], userCity9_2);
    }

    @Test
    @Feature("User Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("User Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: about - change info and verify")
    void t00005() {
        userReadyAPI(data);
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