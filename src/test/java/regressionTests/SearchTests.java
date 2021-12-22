package regressionTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceRegister;
import static helpers.RegressionTestsHelpers.userRegister;

public class SearchTests extends TestBase {
    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4932")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("'Best works' will not show if professional doesn't have any")
    void t00000() {
        //setup
        userRegister();
        serviceRegister();

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        search.verifyBestWorksNotExists();
    }

    @Test
    @Feature("Search")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4932")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("'Best works' will show if filled")
    void t00001() {
        //setup
        userRegister();
        serviceRegister();
        pp.openMyProfessionalProfileLink();
        pp.uploadBestWorks(randomFile);

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        search.verifyBestWorksExists();
    }
}