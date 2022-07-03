package config;

public enum Lang {
    ENGLISH("ENG"), RUSSIAN("RUS"), GERMAN("DEU"), FRENCH("FRA"), SPANISH("ESP"), ARABIC("عرب"), GREEK("Ελλ");

    private final String lang;

    Lang(String lang) {
        this.lang = lang;
    }

    public String getLangText() {
        return lang;
    }
}