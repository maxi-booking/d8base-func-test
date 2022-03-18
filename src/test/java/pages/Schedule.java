package pages;

import com.codeborne.selenide.selector.ByShadow;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static helpers.DateTimeFormatter.scheduleMinutesConvertToId;
import static io.qameta.allure.Allure.step;

public class Schedule {

    public void selectTime(int from, int to) {
        step("Select time from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int fromHours = from / 100;
            int fromMinutes = scheduleMinutesConvertToId(from % 100);
            int toHours = to / 100;
            int toMinutes = scheduleMinutesConvertToId(to % 100);

            $("app-professional-schedule-page app-schedule-editor ion-datetime", 0).click();
            $("ion-picker div.picker-opts", 0).$(" button[opt-index='" + fromHours + "']").click();
            $("ion-picker div.picker-opts", 1).$(" button[opt-index='" + fromMinutes + "']").click();
            $("ion-picker div.picker-toolbar-cancel").sibling(0).click();

            $("app-professional-schedule-page app-schedule-editor ion-datetime", 1).click();
            $("ion-picker div.picker-opts", 0).$(" button[opt-index='" + toHours + "']").click();
            $("ion-picker div.picker-opts", 1).$(" button[opt-index='" + toMinutes + "']").click();
            $("ion-picker div.picker-toolbar-cancel").sibling(0).click();
        });
    }

    public void clickSave() {
        step("Click 'Save'", () -> $("app-professional-schedule-page ion-button[type='submit']").click());
    }

    public void verifySuccessfulSave() {
        step("Schedule saved successfully.", () -> {
            $(ByShadow.cssSelector("div.toast-message", "ion-toast")).shouldBe(visible).shouldHave(textCaseSensitive("Saved"));
        });
    }

    public void verifyTime(int from, int to) {
        step("Verify that schedule time is from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int fromHours = from / 100;
            int fromMinutes = from % 100;
            int toHours = to / 100;
            int toMinutes = to % 100;
            String startTime = fromHours + ":" + fromMinutes;
            String endTime = toHours + ":" + toMinutes;

            $("app-professional-schedule-page app-schedule-editor ion-datetime", 0).shouldHave(text(startTime));
            $("app-professional-schedule-page app-schedule-editor ion-datetime", 1).shouldHave(text(endTime));
        });
    }
}