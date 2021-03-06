package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static helpers.DateTimeFormatter.*;
import static helpers.RegressionTestsHelpers.*;
import static io.qameta.allure.Allure.step;

@Feature("Booking")
@Owner("Egor Khlebnikov")
public class BookingTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4857")
    @DisplayName("Booking: today's date")
    @Severity(SeverityLevel.CRITICAL)
    void bookingDateToday() {
        serviceReadyAPI(data);
        log.forceMainPage();
        data.locale = language.select(defaultLanguage);
        sideMenu.clickSearch();

        search.closeAllChips();
        search.search(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4764")
    @DisplayName("Booking: your own service for another person")
    @Severity(SeverityLevel.NORMAL)
    void bookingOwnServiceForAnother() {
        serviceReadyAPI(data);
        log.forceMainPage();
        data.locale = language.select(defaultLanguage);
        sideMenu.clickSearch();

        search.closeAllChips();
        search.search(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.fillOrderForAPName(userFirstName);
        bkn.fillOrderForAPSurname(userLastName);
        bkn.fillOrderForAPEmail(userEmail);
        bkn.fillOrderForAPPhoneNumber(userCountry, userPhoneNumber);
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(serviceName);
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4764")
    @DisplayName("Booking: a service for another person")
    @Severity(SeverityLevel.NORMAL)
    void bookingServiceForAnother() {
        userRegisterAPI(data);
        serviceRegisterAPI(data);

        String service = serviceName;
        setRandomData();

        userReadyAPI(data);
        sideMenu.clickSearch();

        search.closeAllChips();
        search.search(service);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.clickOrderForAnotherPerson();
        bkn.fillOrderForAPName(userFirstName);
        bkn.fillOrderForAPSurname(userLastName);
        bkn.fillOrderForAPEmail(userEmail);
        bkn.fillOrderForAPPhoneNumber(userCountry, userPhoneNumber);
        bkn.placeOrder();
        bkn.showSentOrderDetails();
        bkn.verifyOrderDetails(service);
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/4929")
    @DisplayName("Booking: time step, can't navigate back in time via arrows")
    @Severity(SeverityLevel.TRIVIAL)
    void bookingCanNotSelectWrongDayViaBackArrows() {
        serviceReadyAPI(data);
        sideMenu.clickSearch();

        search.closeAllChips();
        search.search(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.verifyPreviousDayInactive();
        bkn.clickNextDay();
        bkn.verifyPreviousDayActive();
        bkn.clickPreviousDay();
        bkn.verifyPreviousDayInactive();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5095")
    @DisplayName("Booking: 12 PM schedule shouldn't produce an extra time block")
    @Severity(SeverityLevel.TRIVIAL)
    void bookingSchedule12PMNoExtraBlock() {
        data.country[0] = "Iceland";
        data.city[0] = "Reykjav??k";
        Arrays.fill(data.startTime, timeZoneString(1200));
        Arrays.fill(data.endTime, timeZoneString(1800));
        serviceReadyAPI(data);

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        step("Verify that no extra blocks exists and the correct block is displayed", () -> {
            $("app-time-step app-calendar-component app-section-heading").shouldNotHave(text("11:59"));
            $("app-time-step app-calendar-component app-section-heading").shouldHave(text("12:00"));
            $("app-time-step app-calendar-component app-section-heading").shouldHave(text("59"));
            $("div.timetable", 0).shouldBe(visible);
            $("div.timetable", 1).shouldNot(exist);
        });
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5108")
    @DisplayName("Booking: next day with 00:00 schedule shouldn't produce an extra time block for a previous day")
    @Severity(SeverityLevel.TRIVIAL)
    void bookingScheduleWithMidnightTomorrowNoExtraBlock() {
        data.country[0] = "Iceland";
        data.city[0] = "Reykjav??k";
        Arrays.fill(data.startTime, timeZoneString(1200));
        Arrays.fill(data.endTime, timeZoneString(1800));
        int startTimeHours = Integer.parseInt(timeZoneString(0).substring(0, 2));
        if (startTimeHours < 0) {
            data.startTime[dayIdNext2Days] = (24 + startTimeHours) + timeZoneString(0).substring(2);
            data.endTime[dayIdNext2Days] = "23:59";
            data.startTime[dayUdNext3Days] = "00:00";
            data.endTime[dayUdNext3Days] = timeZoneString(600);
        } else {
            data.startTime[dayIdNext2Days] = timeZoneString(0);
            data.endTime[dayIdNext2Days] = timeZoneString(600);
        }
        serviceReadyAPI(data);

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(tomorrow);
        step("Verify that no extra blocks exists and the correct block is displayed", () -> {
            $("app-time-step app-calendar-component app-section-heading").shouldNotHave(text("11:59"));
            $("app-time-step app-calendar-component app-section-heading").shouldHave(text("12:00"));
            $("app-time-step app-calendar-component app-section-heading").shouldHave(text("59"));
            $("div.timetable", 0).shouldBe(visible);
            $("div.timetable", 1).shouldNot(exist);
        });
    }
}