package helpers;

import com.github.javafaker.Faker;
import config.TestBase;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeFormatter extends TestBase {

    public static String getDateTime(int year, int month, int day, int hours, int minutes) {

        int lastDay;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                lastDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                lastDay = 30;
                break;
            case 2:
                lastDay = 28;
                break;
            default:
                System.out.println("Impossible date, 'month': " + month);
                throw new IllegalArgumentException();
        }

        if (day < 0 || day > lastDay) {
            System.out.println("Impossible date, 'day': " + day);
            throw new IllegalArgumentException();
        }

        if (hours < 0 || hours > 24) {
            System.out.println("Impossible date, 'hours': " + hours);
            throw new IllegalArgumentException();
        }

        if (minutes < 0 || minutes >= 60) {
            System.out.println("Impossible date, 'minutes': " + minutes);
            throw new IllegalArgumentException();
        }

        String monthFormatted = String.format("%02d", month);
        String dayFormatted = String.format("%02d", day);
        String hoursFormatted = String.format("%02d", hours);
        String minutesFormatted = String.format("%02d", minutes);

        String formattedDateTime = year + "-" + monthFormatted + "-" + dayFormatted + "T" + hoursFormatted + ":" + minutesFormatted + ":00.000Z";
        return formattedDateTime;
    }

    public static String getDateTime() {

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        int year = currentYear;
        int month = (int) ((Math.random() * 12) + 1);
        if (month < currentMonth) {
            year++;
        }
        if (month > (currentMonth + 2)) {
            month = (int) ((Math.random() * (currentMonth + 3)) + currentMonth);
        }
        int day = (int) ((Math.random() * 31) + 1);

        if (month == currentMonth && day <= currentDay) {
            day = currentDay + 1;
        }

        int lastDay;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                lastDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                lastDay = 30;
                break;
            case 2:
                lastDay = 28;
                break;
            default:
                System.out.println("Impossible date, 'month': " + month);
                throw new IllegalArgumentException();
        }

        if (day > lastDay) {
            day = lastDay;
        }

        if (month == currentMonth && day <= currentDay) {
            day = 1;
            if (month == 12) {
                month = 1;
                year++;
            } else {
                month++;
            }
        }

        int hours = (int) ((Math.random() * (16 - 9)) + 9);
        hours = hours - timeZone;

        int minutesArray[] = {0, 15, 30, 45};
        int randomArrayElement = (int) (Math.random() * 4);
        int minutes = minutesArray[randomArrayElement];

        String monthFormatted = String.format("%02d", month);
        String dayFormatted = String.format("%02d", day);
        String hoursFormatted = String.format("%02d", hours);
        String minutesFormatted = String.format("%02d", minutes);

        String formattedDateTime = year + "-" + monthFormatted + "-" + dayFormatted + "T" + hoursFormatted + ":" + minutesFormatted + ":00.000Z";
        return formattedDateTime;
    }

    public static String getDayXDaysForward(int value) {
        String desiredDay = "0";
        int lastDayOfTheMonth = 0;
        int lastDayOfTheNextMonth = 0;
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().charAt(0) + currentMonth.toString().toLowerCase().substring(1);
        int day = currentDate.getDayOfMonth() + value - 1;
        switch (month) {
            case "January":
                lastDayOfTheMonth = 31;
                lastDayOfTheNextMonth = 28;
                break;
            case "July":
                lastDayOfTheMonth = 31;
                lastDayOfTheNextMonth = 31;
                break;
            case "March":
            case "October":
            case "December":
            case "August":
            case "May":
                lastDayOfTheMonth = 31;
                lastDayOfTheNextMonth = 30;
                break;
            case "February":
                lastDayOfTheMonth = 28;
                lastDayOfTheNextMonth = 31;
                break;
            case "April":
            case "September":
            case "November":
            case "June":
                lastDayOfTheMonth = 30;
                lastDayOfTheNextMonth = 31;
                break;
            default:
                fail();
                break;
        }
        if (day < 0) {
            fail();
        } else if (day == lastDayOfTheMonth) {
            desiredDay = "1";
        } else if (day > (lastDayOfTheMonth + lastDayOfTheNextMonth)) {
            desiredDay = "1";
            System.out.println("Date is too far into the future, forced to '1'");
        } else if (day > lastDayOfTheMonth) {
            desiredDay = Integer.toString(day - lastDayOfTheMonth);
        } else {
            desiredDay = Integer.toString(day + 1);
        }
        return desiredDay;
    }

    public static String generateMonth() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}[(int) (Math.random() * 12)];
    }

    public static String monthConvertToNumber(String month) {
        switch (month) {
            case "January":
                month = "01";
                break;
            case "February":
                month = "02";
                break;
            case "March":
                month = "03";
                break;
            case "April":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "June":
                month = "06";
                break;
            case "July":
                month = "07";
                break;
            case "August":
                month = "08";
                break;
            case "September":
                month = "09";
                break;
            case "October":
                month = "10";
                break;
            case "November":
                month = "11";
                break;
            case "December":
                month = "12";
                break;
            default:
                fail();
                break;
        }
        return month;
    }

    public static int scheduleMinutesConvertToId(int minutes) {
        switch (minutes) {
            case 0:
                minutes = 0;
                break;
            case 15:
                minutes = 1;
                break;
            case 30:
                minutes = 2;
                break;
            case 45:
                minutes = 3;
                break;
            default:
                System.out.println("Minutes should be multiple of 15. Incorrect value: " + minutes);
                fail();
                break;
        }
        return minutes;
    }

    public static String dayIdToString(int value) {
        String day = null;
        switch (value) {
            case 0:
                day = "monday";
                break;
            case 1:
                day = "tuesday";
                break;
            case 2:
                day = "wednesday";
                break;
            case 3:
                day = "thursday";
                break;
            case 4:
                day = "friday";
                break;
            case 5:
                day = "saturday";
                break;
            case 6:
                day = "sunday";
                break;
            default:
                System.out.println("Day ID should be 0-6 range, incorrect value: " + value);
                fail();
                break;
        }
        return day;
    }

    public static int dayStringToId(String value) {
        int day = 0;
        value = value.toLowerCase();
        ;
        switch (value) {
            case "monday":
                day = 0;
                break;
            case "tuesday":
                day = 1;
                break;
            case "wednesday":
                day = 2;
                break;
            case "thursday":
                day = 3;
                break;
            case "friday":
                day = 4;
                break;
            case "saturday":
                day = 5;
                break;
            case "sunday":
                day = 6;
                break;
            default:
                System.out.println("Unknown day naming: " + value);
                fail();
                break;
        }
        return day;
    }

    public static int getNextDayId(int value) {
        int day = 0;
        switch (value) {
            case 0:
                day = 1;
                break;
            case 1:
                day = 2;
                break;
            case 2:
                day = 3;
                break;
            case 3:
                day = 4;
                break;
            case 4:
                day = 5;
                break;
            case 5:
                day = 6;
                break;
            case 6:
                day = 0;
                break;
            default:
                System.out.println("Day ID should be 0-6 range, incorrect value: " + value);
                fail();
                break;
        }
        return day;
    }

    public static String autoTimeZone(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        int offset = Math.abs(offsetInMillis / 3600000) - 3;
        hours = hours + offset;
        return hours + ":" + minutes;
    }
}