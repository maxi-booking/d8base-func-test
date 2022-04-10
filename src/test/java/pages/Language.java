package pages;

import com.codeborne.selenide.Selenide;
import config.TestBase;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static helpers.LanguageConverter.*;
import static io.qameta.allure.Allure.step;

public class Language extends TestBase {

    public void select(String language) {
        step("Select " + language.substring(0, 1).toUpperCase() + language.substring(1) + " language", () -> {
            sleep(1000);
            String desiredLanguage = getLanguageString(language);
            step("Desired language: " + desiredLanguage);
            String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            step("Current language: " + currentLanguage);
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                String lang = languageByText(language);
                $$("[menu='flag-menu']").filter(visible).get(0).click();

                //old method
                if ($("app-flag-menu ion-list ion-item").$("ion-select").exists()) {
                    $("app-flag-menu ion-select").click();
                    $("ion-alert").$(byText(lang)).click();
                    $("div.alert-button-group button", 1).click();
                    $("div.alert-radio-group").shouldNotBe(visible, Duration.ofSeconds(10));

                //new method
                } else {
                    $("app-flag-menu ion-list ion-item").click();
                    $("ionic-selectable-modal").$(byText(lang)).click();
                    $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
                    $("app-flag-menu").shouldNotBe(visible, Duration.ofSeconds(10));
                }

                sleep(1500);
                if ($("ion-alert").exists()) {
                    log.closeAlert();
                }
            }
        });
    }
}