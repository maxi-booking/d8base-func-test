package tests.scheduleTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceReadyAPI;

@Feature("Schedule")
@Owner("Egor Khlebnikov")
public class ScheduleTests extends TestBase {

    @Test
    @DisplayName("Schedule: back button is working")
    @Severity(SeverityLevel.CRITICAL)
    void scheduleBackButtonTest() {
        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyScheduleEntered();
    }

    @Test
    @DisplayName("Schedule: add button is working")
    @Severity(SeverityLevel.BLOCKER)
    void scheduleAddButtonTest() {
        data.days = 1;
        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.verifyDaysQty(1);
        sch.addDay(tuesday);
        sch.verifyDaysQty(2);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyDaysQty(2);
    }

    @Test
    @DisplayName("Schedule: check box is working")
    @Severity(SeverityLevel.BLOCKER)
    void scheduleCheckBoxTest() {
        data.days = 2;
        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.verifyDayEnabled(1);
        sch.verifyDayEnabled(2);
        sch.disableDay(2);
        sch.verifyDayDisabled(2);
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyDayEnabled(1);
        sch.verifyDayEnabled(2);
        sch.disableDay(2);
        sch.verifyDayDisabled(2);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyDaysQty(1);
    }

    @Test
    @DisplayName("Schedule: time selection is working")
    @Severity(SeverityLevel.BLOCKER)
    void scheduleFunctionalityVerification() {
        data.days = 3;
        data.startTime = "09:00";
        data.endTime = "17:00";
        int startTime = 900;
        int endTime = 1700;

        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.selectTimeAll(0, 2345);
        sch.verifyAllTime(0, 2345);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyScheduleEntered();
        sch.verifyAllTime(0, 2345);
        sch.selectTimeAll(startTime, endTime);
        sch.verifyAllTime(startTime, endTime);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyScheduleEntered();
        sch.verifyAllTime(startTime, endTime);
        sch.selectAndCancelTime(0, 2345);
        sch.verifyAllTime(0, endTime);
        sch.selectTimeAll(randomStartTime, randomEndTime);
        sch.verifyAllTime(randomStartTime, randomEndTime);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sch.verifyScheduleExited();
        sideMenu.clickSchedule();
        sch.verifyScheduleEntered();
        sch.verifyAllTime(randomStartTime, randomEndTime);
    }
}