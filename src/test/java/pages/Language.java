package pages;

import com.codeborne.selenide.Selenide;
import config.TestBase;
import helpers.Attach;

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
            String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            Attach.screenshotAs("Debug-1");
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                String lang = languageByText(language);
                $$("[menu='flag-menu']").filter(visible).get(0).click();
                Attach.screenshotAs("Debug-2");

                //old method
                if ($("app-flag-menu ion-list ion-item").$("ion-select").exists()) {
                    $("app-flag-menu ion-select").click();
                    Attach.screenshotAs("Debug-3.1");
                    $("ion-alert").$(byText(lang)).click();
                    Attach.screenshotAs("Debug-4.1");
                    $("div.alert-button-group button", 1).click();
                    Attach.screenshotAs("Debug-5.1");
                    $("div.alert-radio-group").shouldNotBe(visible, Duration.ofSeconds(10));
                    Attach.screenshotAs("Debug-6.1");

                //new method
                } else {
                    $("app-flag-menu ion-list ion-item").click();
                    Attach.screenshotAs("Debug-3.2");
                    $("ionic-selectable-modal").$(byText(lang)).click();
                    Attach.screenshotAs("Debug-4.2");
                    $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
                    Attach.screenshotAs("Debug-5.2");
                    $("app-flag-menu").shouldNotBe(visible, Duration.ofSeconds(10));
                    Attach.screenshotAs("Debug-6.2");
                }

                sleep(1500);
                Attach.screenshotAs("Debug-7");
                if ($("ion-alert").exists()) {
                    Attach.screenshotAs("Debug-8");
                    log.closeAlert();
                    Attach.screenshotAs("Debug-9");
                }
                Attach.screenshotAs("Debug-end");
            }
        });
    }
}