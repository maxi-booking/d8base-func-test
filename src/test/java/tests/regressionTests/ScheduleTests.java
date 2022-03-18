package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceReadyAPI;

public class ScheduleTests extends config.TestBase {

    @Test
    @Feature("Schedule")
    @Owner("Egor Khlebnikov")
    @Story("https://redmine.maxi-booking.ru/issues/5103")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Schedule: picker time selection is working")
    void scheduleTimeCanBeSelectedWithPicker() {
        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.selectTime(1215,1630);
        sch.verifyTime(1215,1630);
        sch.clickSave();
        sch.verifySuccessfulSave();
    }
}