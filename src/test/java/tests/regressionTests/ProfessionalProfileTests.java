package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;

public class ProfessionalProfileTests extends config.TestBase {
    @Test
    @Feature("Professional Profile")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4848")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Professional Profile: delete education")
    void t00000() {
        serviceReadyAPI();
        log.openMainPage();
        log.forceEN();

        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(masterEducationUniversity);

        pp.educationClickSave();
        pp.educationVerificationBasic(0, masterEducationUniversity);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();
    }

    @Test
    @Feature("Professional Profile")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4909")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Professional Profile: social share button should be clickable (another person)")
    void t00001() {
        serviceReadyAPI();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickLogOut();

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNameRandom);
        search.clickProfessionalsName();
        pp.clickSocialShare();
        log.toastVisible();
    }
}