package helpers;

import static org.junit.jupiter.api.Assertions.fail;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

public class DayHelper {

    public static String getDayXDaysForward(int value) {
        String desiredDay;
        int lastDayOfTheMonth = 0;
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        String month = currentMonth.toString().charAt(0) + currentMonth.toString().toLowerCase().substring(1);
        int day = currentDate.getDayOfMonth() + value - 1;
        switch (month) {
            case "January":
            case "March":
            case "October":
            case "December":
            case "August":
            case "July":
            case "May":
                lastDayOfTheMonth = 31;
                break;
            case "February":
                lastDayOfTheMonth = 28;
                break;
            case "April":
            case "September":
            case "November":
            case "June":
                lastDayOfTheMonth = 30;
                break;
            default:
                fail();
                break;
        }
        if (day == lastDayOfTheMonth) {
            desiredDay = "1";
        } else if (day > lastDayOfTheMonth) {
            desiredDay = Integer.toString(day - lastDayOfTheMonth);
        }
        else {
            desiredDay = Integer.toString(day + 1);
        }
        return desiredDay;
    }
}