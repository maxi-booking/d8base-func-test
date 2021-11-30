package helpers;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;

public class DayHelper {

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
}