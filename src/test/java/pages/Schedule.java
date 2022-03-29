package pages;

import com.codeborne.selenide.selector.ByShadow;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.DateTimeFormatter.dayIdToString;
import static helpers.SchedulePicker.cancelScheduleTimeSlot;
import static helpers.SchedulePicker.confirmScheduleTimeSlot;
import static io.qameta.allure.Allure.step;

public class Schedule {

    public void clickBack() {
        $$("app-column-header ion-button").filter(visible).get(0).click();
        $("app-column-header ion-button").shouldNotBe(visible, Duration.ofSeconds(10));
        $("ion-spinner").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void addDay(int day) {
        $("app-professional-schedule-page app-column-header ion-buttons.end-btns ion-button").shouldBe(visible, Duration.ofSeconds(10));
        $("app-professional-schedule-page app-column-header ion-buttons.end-btns ion-button").click();
        $("ion-popover app-timetable-add-time-popover ion-item").shouldBe(visible, Duration.ofSeconds(10));
        $("ion-popover app-timetable-add-time-popover ion-item", day).click();
        $("ion-popover app-timetable-add-time-popover ion-item").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void clickDay(int index) {
        $("app-professional-schedule-page app-schedule-editor ion-checkbox", index).click();
    }

    public void enableDay(int index) {
        if (!$("app-professional-schedule-page app-schedule-editor ion-item.label-item", index).$("ion-checkbox.checkbox-checked").exists()) {
            $("app-professional-schedule-page app-schedule-editor ion-checkbox", index).click();
        }
    }

    public void disableDay(int index) {
        index--;
        if ($("app-professional-schedule-page app-schedule-editor ion-item.label-item", index).$("ion-checkbox.checkbox-checked").exists()) {
            $("app-professional-schedule-page app-schedule-editor ion-checkbox", index).click();
        }
    }

    public void selectTimeAll(int from, int to) {
        step("Select time from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int i = 0;
            while ($("app-professional-schedule-page app-schedule-editor ion-datetime", i).exists()) {
                confirmScheduleTimeSlot(from, i);
                confirmScheduleTimeSlot(to, i + 1);
                i += 2;
            }
        });
    }

    public void selectTime(int day, int from, int to) {
        step("Select time for " + dayIdToString(day) + " from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int value = day * 2;
            confirmScheduleTimeSlot(from, value);
            confirmScheduleTimeSlot(to, value + 1);
        });
    }

    public void selectAndCancelTime(int from, int to) {
        step("Select time from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int i = 0;
            while ($("app-professional-schedule-page app-schedule-editor ion-datetime", i).exists()) {
                confirmScheduleTimeSlot(from, i);
                cancelScheduleTimeSlot(to, i + 1);
                i += 2;
            }
        });
    }

    public void clickSave() {
        step("Click 'Save'", () -> $("app-professional-schedule-page ion-button[type='submit']").click());
    }

    public void verifySuccessfulSave() {
        step("Schedule saved successfully.", () -> {
            $(ByShadow.cssSelector("div.toast-message", "ion-toast")).shouldBe(visible).shouldHave(textCaseSensitive("Saved"));
            $(ByShadow.cssSelector("div.toast-message", "ion-toast")).shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void verifyAllTime(int from, int to) {
        step("Verify that schedule time is from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int fromHours = from / 100;
            int fromMinutes = from % 100;
            int toHours = to / 100;
            int toMinutes = to % 100;
            String startTime = fromHours + ":" + fromMinutes;
            String endTime = toHours + ":" + toMinutes;

            int i = 0;
            while ($("app-professional-schedule-page app-schedule-editor ion-datetime", i).exists()) {
                $("app-professional-schedule-page app-schedule-editor ion-datetime", i).shouldHave(text(startTime));
                $("app-professional-schedule-page app-schedule-editor ion-datetime", i + 1).shouldHave(text(endTime));
                i += 2;
            }
        });
    }

    public void verifyTime(int day, int from, int to) {
        step("Verify that " + dayIdToString(day) + " schedule time is from " + from / 100 + ":" + from % 100 + " to " + to / 100 + ":" + to % 100, () -> {
            int fromHours = from / 100;
            int fromMinutes = from % 100;
            int toHours = to / 100;
            int toMinutes = to % 100;
            String startTime = fromHours + ":" + fromMinutes;
            String endTime = toHours + ":" + toMinutes;
            int value = day * 2;

            $("app-professional-schedule-page app-schedule-editor ion-datetime", value).shouldHave(text(startTime));
            $("app-professional-schedule-page app-schedule-editor ion-datetime", value + 1).shouldHave(text(endTime));
        });
    }

    public void verifyDayEnabled(int index) {
        index--;
        $("app-professional-schedule-page app-schedule-editor ion-checkbox", index).shouldHave(cssClass("checkbox-checked"));
    }

    public void verifyDayDisabled(int index) {
        index--;
        $("app-professional-schedule-page app-schedule-editor ion-checkbox", index).shouldNotHave(cssClass("checkbox-checked"));
    }

    public void verifyDaysQty(int amount) {
        $("app-schedule-editor div.date-row", amount).shouldNot(exist, Duration.ofSeconds(10));
        amount--;
        $("app-schedule-editor div.date-row", amount).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void verifyScheduleExited() {
        $("app-schedule-editor").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void verifyScheduleEntered() {
        $("app-schedule-editor").shouldBe(visible, Duration.ofSeconds(10));
    }
}