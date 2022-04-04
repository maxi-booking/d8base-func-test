package helpers;

import config.TestBase;
import pages.Schedule;

import java.time.Duration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static helpers.DateTimeFormatter.scheduleMinutesConvertToId;

public class SchedulePicker extends TestBase {

    public static void fillScheduleTimeSlot(int time, int id) {
        int hours = time / 100;
        int minutes = scheduleMinutesConvertToId(time % 100);
        $("app-professional-schedule-page app-schedule-editor ion-datetime", id).click();
        $("ion-picker div.picker-opts").shouldBe(visible, Duration.ofSeconds(10));
        int currentPicker = Integer.parseInt($("ion-picker div.picker-opts button.picker-opt-selected").getText());
        while (true) {
            if (hours < currentPicker && !$("ion-picker div.picker-opts", 0).$("button[opt-index='" + hours + "']").isDisplayed()) {
                currentPicker -= 1;
                $("ion-picker div.picker-opts", 0).$("button[opt-index='" + currentPicker + "']").click();
            } else if (hours > currentPicker && !$("ion-picker div.picker-opts", 0).$("button[opt-index='" + hours + "']").isDisplayed()) {
                currentPicker += 1;
                $("ion-picker div.picker-opts", 0).$("button[opt-index='" + currentPicker + "']").click();
            } else {
                $("ion-picker div.picker-opts", 0).$("button[opt-index='" + hours + "']").click();
                break;
            }
        }
        $("ion-picker div.picker-opts", 0).$("button.picker-opt-selected[opt-index='" + hours + "']").shouldBe(visible, Duration.ofSeconds(10));
        currentPicker = Integer.parseInt($("ion-picker div.picker-opts button.picker-opt-selected").getText());
        if (currentPicker == 45 || minutes == 0) {
            $("ion-picker div.picker-opts", 1).$("button[opt-index='1']").click();
        }
        if (currentPicker == 0 || minutes == 45) {
            $("ion-picker div.picker-opts", 1).$("button[opt-index='2']").click();
        }
        $("ion-picker div.picker-opts", 1).$("button[opt-index='" + minutes + "']").click();
        $("ion-picker div.picker-opts", 1).$("button.picker-opt-selected[opt-index='" + minutes + "']").shouldBe(visible, Duration.ofSeconds(10));
    }

    public static void confirmScheduleTimeSlot(int time, int id) {
        fillScheduleTimeSlot(time, id);
        clickConfirm();
    }

    public static void cancelScheduleTimeSlot(int time, int id) {
        fillScheduleTimeSlot(time, id);
        clickCancel();
    }

    public static void clickCancel() {
        $("ion-picker div.picker-toolbar-cancel button[type='button']").shouldBe(visible, Duration.ofSeconds(10));
        $("ion-picker div.picker-toolbar-cancel button[type='button']").click();
        $("ion-picker div.picker-toolbar-cancel button[type='button']").shouldHave(cssClass("ion-activated"));
        $("ion-picker").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public static void clickConfirm() {
        $("ion-picker div.picker-toolbar button[type='button']", 1).shouldBe(visible, Duration.ofSeconds(10));
        $("ion-picker div.picker-toolbar button[type='button']", 1).click();
        $("ion-picker div.picker-toolbar button[type='button']", 1).shouldHave(cssClass("ion-activated"));
        $("ion-picker").shouldNotBe(visible, Duration.ofSeconds(10));
    }
}