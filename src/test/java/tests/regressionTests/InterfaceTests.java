package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Feature("Interface")
@Owner("Egor Khlebnikov")
public class InterfaceTests extends config.TestBase {

    @ParameterizedTest(name = "Interface: language menu with correct order in {0}")
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5168")
    @Severity(SeverityLevel.MINOR)
    @CsvSource({
            englishLanguage,
            russianLanguage,
            germanLanguage,
            frenchLanguage,
            spanishLanguage,
            arabicLanguage,
            greekLanguage
    })
    void languageOrder(String localeLanguage) {
        log.openMainPage();
        log.popupSkip();
        language.select(localeLanguage);
        log.openLanguageMenu();
        log.verifyLanguageMenuOrder();
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5151")
    @DisplayName("Interface: french language should have FRA signature")
    @Severity(SeverityLevel.MINOR)
    void languageFrenchShouldBeFRA() {
        log.openMainPage();
        log.popupSkip();
        language.select(frenchLanguage);
        language.verifySignature("FRA");
    }
}