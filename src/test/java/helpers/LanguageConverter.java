package helpers;

import config.Lang;
import config.TestBase;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.fail;

public class LanguageConverter extends TestBase {

    public static String getLanguageString(String language) {
        String lang = null;
        language = language.toLowerCase();
        switch (language) {
            case "english":
                lang = Lang.ENGLISH.getLangText();
                break;
            case "russian":
                lang = Lang.RUSSIAN.getLangText();
                break;
            case "german":
                lang = Lang.GERMAN.getLangText();
                break;
            case "french":
                lang = Lang.FRENCH.getLangText();
                break;
            case "hispanic":
                lang = Lang.HISPANIC.getLangText();
                break;
            default:
                System.out.println("Unknown language: " + language);
                fail();
                break;
        }
        return lang;
    }

    public static int getLanguageId(String language) {
        int lang = 99;
        language = language.toLowerCase();
        switch (language) {
            case "english":
                lang = 0;
                break;
            case "russian":
                lang = 1;
                break;
            case "german":
                lang = 2;
                break;
            case "french":
                lang = 3;
                break;
            case "hispanic":
                lang = 4;
                break;
            default:
                System.out.println("Unknown language: " + language);
                fail();
                break;
        }
        return lang;
    }
}