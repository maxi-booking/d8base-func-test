package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Footer")
@Owner("Egor Khlebnikov")
public class FooterTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4928")
    @DisplayName("Footer: '/for-performers' can be opened via footer click")
    @Severity(SeverityLevel.CRITICAL)
    void t00000() {
        log.openMainPage();
        log.popupClickCancel();
        ftr.clickForPerformersPage();
        ftr.verifyForPerformers();
    }
}
