package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FooterTests extends config.TestBase {

    @Test
    @Feature("Footer")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/4928")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Footer: '/for-performers' can be opened via footer click")
    void t00000() {
        log.openMainPage();
        log.popupClickCancel();
        ftr.clickForPerformersPage();
        ftr.verifyForPerformers();
    }
}
