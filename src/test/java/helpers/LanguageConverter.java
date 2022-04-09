package helpers;

import config.Lang;

import static org.junit.jupiter.api.Assertions.fail;

public class LanguageConverter {

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
            case "spanish":
                lang = Lang.SPANISH.getLangText();
                break;
            case "arabic":
                lang = Lang.ARABIC.getLangText();
                break;
            case "greek":
                lang = Lang.GREEK.getLangText();
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
            case "spanish":
                lang = 4;
                break;
            case "arabic":
                lang = 5;
                break;
            case "greek":
                lang = 6;
                break;
            default:
                System.out.println("Unknown language: " + language);
                fail();
                break;
        }
        return lang;
    }

    public static String languageByText(String language) {
        String lang = "language not chosen";
        language = language.toLowerCase();
        switch (language) {
            case "english":
                lang = "English";
                break;
            case "russian":
                lang = "Русский";
                break;
            case "german":
                lang = "Deutsch";
                break;
            case "french":
                lang = "Français";
                break;
            case "spanish":
                lang = "Español";
                break;
            case "arabic":
                lang = "عرب";
                break;
            case "greek":
                lang = "Ελληνικά";
                break;
            default:
                System.out.println("Unknown language: " + language);
                fail();
                break;
        }
        return lang;
    }
}