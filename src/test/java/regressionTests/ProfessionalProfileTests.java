package regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceRegister;
import static helpers.RegressionTestsHelpers.userRegister;

public class ProfessionalProfileTests extends config.TestBase {
    @Test
    @Feature("Professional Profile")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4848")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Professional Profile: delete education")
    void t00000() {
        userRegister();
        serviceRegister();
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
        userRegister();
        serviceRegister();
        log.openMainPage();
        log.forceEN();
        sideMenu.clickLogOut();

        sideMenu.clickSearch();
        sch.search(serviceName);
        sch.clickProfessionalsName();
        pp.clickSocialShare();
        log.toastVisible();
    }
}