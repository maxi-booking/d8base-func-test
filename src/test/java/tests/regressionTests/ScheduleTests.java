package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static helpers.RegressionTestsHelpers.serviceReadyAPI;

@Feature("Schedule")
@Owner("Egor Khlebnikov")
public class ScheduleTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5103")
    @DisplayName("Schedule: picker time selection is working")
    @Severity(SeverityLevel.NORMAL)
    void scheduleTimeCanBeSelectedWithPicker() {
        serviceReadyAPI(data);
        sideMenu.clickSchedule();
        sch.selectTimeAll(randomStartTime, randomEndTime);
        sch.verifyAllTime(randomStartTime, randomEndTime);
        sch.clickSave();
        sch.verifySuccessfulSave();
        sch.clickBack();
        sideMenu.clickSchedule();
        sch.verifyAllTime(randomStartTime, randomEndTime);
    }

    @Test
    @Disabled
    @Story("Service stability")
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5110")
    @DisplayName("Service should not hang")
    @Severity(SeverityLevel.TRIVIAL)
    void serviceHang() {
        data.days = 5;
        serviceReadyAPI(data);
        int i = 0;
        while (i < 100) {
            sideMenu.openMenu();
            $("[id='main-menu.professional-schedule']").scrollIntoView(true).click();
            $("app-professional-schedule-page app-column-header ion-button").click();
            i++;
        }
        sideMenu.openMenu();
        $("[id='main-menu.professional-schedule']").scrollIntoView(true).click();
        sch.addDay(saturday);
    }
}