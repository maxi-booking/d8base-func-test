package helpers;

import config.TestBase;

public class Currency extends TestBase {

    public static String getCurrencyById(int currencyID) {
        String currency;
        switch (currencyID) {
            case 0:
                currency = "CAD";
                break;
            case 1:
                currency = "EUR";
                break;
            case 2:
                currency = "RUB";
                break;
            case 3:
                currency = "USD";
                break;
            default:
                System.out.println("Unknown currency ID: " + currencyID);
                throw  new IllegalArgumentException();
        }
        return currency;
    }

    public static String getCurrencySignById(int currencyID) {
        String currency;
        switch (currencyID) {
            case 0:
            case 3:
                currency = "$";
                break;
            case 1:
                currency = "€";
                break;
            case 2:
                currency = "₽";
                break;
            default:
                System.out.println("Unknown currency ID: " + currencyID);
                throw  new IllegalArgumentException();
        }
        return currency;
    }
}