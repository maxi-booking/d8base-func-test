package tests.regressionTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;

@Feature("Search")
@Owner("Egor Khlebnikov")
public class SearchTests extends TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4932")
    @DisplayName("'Best works' will not show if professional doesn't have any")
    @Severity(SeverityLevel.NORMAL)
    void t00000() {
        //setup
        serviceReadyAPI(data);

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        search.verifyBestWorksNotExists();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4932")
    @DisplayName("'Best works' will show if filled")
    @Severity(SeverityLevel.NORMAL)
    void t00001() {
        //setup
        serviceReadyAPI(data);
        pp.openMyProfessionalProfileLink();
        pp.uploadBestWorks(randomFile);

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        search.verifyBestWorksExists();
    }
}