package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;

@Feature("Profile")
@Owner("Egor Khlebnikov")
public class UserProfileTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4844")
    @DisplayName("Profile: user can delete default address")
    @Severity(SeverityLevel.CRITICAL)
    void profileOldDefaultAddressDeletable() {
        String address1 = "Street 1";
        String address2 = "Street 2";
        userReadyAPI(data);
        sideMenu.openProfile();

        up.clickAddNewAddress();
        up.addressSelectCountry(userCountry);
        up.addressSelectCity(userCity);
        up.addressSelectAddress(address1);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.clickAddNewAddress();
        up.addressSelectCountry(userCountry);
        up.addressSelectCity(userCity);
        up.addressSelectAddress(address2);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.selectAddress(1);
        up.addressClickRemove();
        up.verifyAddressRemoved(userCountry, userCity, address1);

        up.selectAddress(1);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.selectAddress(1);
        up.addressClickRemove();
        up.verifyAddressRemoved(userCountry, userCity, address1);
        up.verifyAddressRemoved(userCountry, userCity, address2);
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5079")
    @DisplayName("Profile: language checkboxes are always present")
    @Severity(SeverityLevel.MINOR)
    void profileLanguageCheckboxesShown() {
        userReadyAPI(data);
        sideMenu.openProfile();

        up.openUserProfileAbout();
        up.selectLanguages(english);
        up.clickBackAbout();
        up.verifyNoLanguages();

        up.openUserProfileAbout();
        up.selectLanguages(english);
        up.clickSaveAbout();
        up.verifyLanguages(english);

        up.openUserProfileAbout();
        up.selectLanguages(russian);
        up.clickBackAbout();
        up.verifyLanguages(english);
        up.verifyNoLanguages(russian);

        up.openUserProfileAbout();
        up.selectLanguages(russian);
        up.clickSaveAbout();
        up.verifyLanguages(english, russian);

        up.openUserProfileAbout();
        up.removeLanguages(russian);
        up.clickBackAbout();
        up.verifyLanguages(english, russian);

        up.openUserProfileAbout();
        up.removeLanguages(russian);
        up.clickSaveAbout();
        up.verifyLanguages(english);
        up.verifyNoLanguages(russian);

        up.openUserProfileAbout();
        up.removeLanguages(english);
        up.clickSaveAbout();
        up.verifyNoLanguages();
    }
}