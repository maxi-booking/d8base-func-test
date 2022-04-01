package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.selector.ByShadow;
import config.Lang;
import helpers.Attach;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static helpers.LanguageConverter.getLanguageId;
import static helpers.LanguageConverter.getLanguageString;
import static helpers.SelectableModal.selectModal;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class LogIn extends config.TestBase {

    private void closeAlert() {
        $("ion-alert").pressEscape();
    }

    public void refreshPage() {
        Selenide.refresh();
    }

    public void openMainPage() {
        openUrl(urlFrontend);
    }

    public void forceMainPage() {
        step("Open main page", () -> {
            if (!$("body").exists()) {
                openUrl(urlFrontend);
                return;
            }
            sleep(500);
            String currentUrl = WebDriverRunner.url();
            if (!currentUrl.equals(urlFrontend)) {
                openUrl(urlFrontend);
            }
        });
    }

    public void openUrl(String url) {
        step("Open URL: " + url, () -> {
            open(url);
        });
    }

    public String copy() {
        return clipboard().getText();
    }

    @Step("Log out by URL")
    public void forceLogOut() {
        openUrl(urlLogOut);
        sleep(2000);
    }

    public void logoClick() {
        $$("ion-item").filter(visible).get(0).scrollIntoView(true).click();
    }

    public void logIn(String login, String password) {
        step("Log In with:\n" + login + "\n" + password, () -> {
            sideMenu.clickLogIn();
            $("app-login input[name='email']").setValue(login);
            $("app-login input[name='password']").setValue(password);
            $("app-login ion-input[name='email']").shouldHave(cssClass("ion-valid"), Duration.ofSeconds(10));
            $("app-login ion-input[name='password']").shouldHave(cssClass("ion-valid"), Duration.ofSeconds(10));
            $("app-login-form ion-button[type='submit']").click();
            $("app-login-form ion-button[type='submit']").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    @Step("No toast message")
    public void noToast() {
        $("ion-toast").shouldNotBe(visible, Duration.ofSeconds(1));
    }

    @Step("EToast message visible")
    public void toastVisible() {
        $("ion-toast").shouldBe(visible, Duration.ofSeconds(1));
    }

    public void logTempAcc() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue("email@email.gg");
        $("app-login").$("input", 1).setValue("qazxcdew");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account(int value) {
        step("Log In with " + emails[value] + " : " + passwords[value], () -> {
            String login = emails[value];
            String password = passwords[value];
            sideMenu.clickLogIn();
            $$("input[name='email']").filter(visible).get(0).scrollIntoView(true).setValue(login);
            $$("input[name='password']").filter(visible).get(0).scrollIntoView(true).setValue(password);
            $$("ion-button[type='submit']").filter(visible).get(0).scrollIntoView(true).click();
            $("app-login").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void forceRU() {
        step("Change language to Russian", () -> {
            sleep(1000);
            String desiredLanguage = Lang.RUSSIAN.getLangText();
            String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $$("[menu='flag-menu']").filter(visible).get(0).click();
                $("app-flag-menu ion-select").click();
                $("ion-alert button", 1).click();
                $("div.alert-button-group button", 1).click();
                $("div.alert-radio-group").shouldNotBe(visible, Duration.ofSeconds(10));
                sleep(1500);
                if ($("ion-alert").exists()) {
                    closeAlert();
                }
            }
        });
    }

    public void forceEN() {
        step("Change language to English", () -> {
            sleep(1000);
            String desiredLanguage = Lang.ENGLISH.getLangText();
            String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $$("[menu='flag-menu']").filter(visible).get(0).click();
                $("app-flag-menu ion-select").click();
                $("ion-alert button", 0).click();
                $("div.alert-button-group button", 1).click();
                $("div.alert-radio-group").shouldNotBe(visible, Duration.ofSeconds(10));
                sleep(1500);
                if ($("ion-alert").exists()) {
                    closeAlert();
                }
            }
        });
    }

    public void changeLanguageTo(String language) {
        step("Change language to " + language.substring(0, 1).toUpperCase() + language.substring(1).toLowerCase(), () -> {
            sleep(1000);
            String desiredLanguage = getLanguageString(language);
            String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $$("[menu='flag-menu']").filter(visible).get(0).click();
                $("app-flag-menu ion-select").click();
                $("ion-alert button", getLanguageId(language)).click();
                $("div.alert-button-group button", 1).click();
                $("div.alert-radio-group").shouldNotBe(visible, Duration.ofSeconds(10));
                sleep(1500);
                if ($("ion-alert").exists()) {
                    closeAlert();
                }
            }
            currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                System.out.println("Language ID is broken. Different order in the menu than expected, fix needed. \n Method: LanguageConverter.getLanguageId(~);");
                fail();
            }
        });
    }

    public void popupSelect(String country, String city) {
        step("Pop-up select country: " + country + "; city: " + city, () -> {
            popupSkip();
            forceEN();
            refreshPage();

            $("ion-alert button", 1).click();

            $("app-on-map-popover app-country-selector ion-item").click();
            selectModal(country);

            $("app-on-map-popover app-city-selector ion-item").click();
            selectModal(city);
            $("app-on-map-popover ion-button").click();
        });
    }

    public void popupSelectOld(String country, String city) {
        popupSkip();
        forceEN();
        refreshPage();

        $("ion-alert").$("button", 1).click();

        $("app-on-map-popover").$("app-country-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(country);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item").click();

        $("app-on-map-popover").$("app-city-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(city);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item").click();
        $("app-on-map-popover").$("ion-button").click();
    }

    public void popupSkip() {
        step("Pop-up skip", () -> {
            sleep(200);
            $("h2.alert-sub-title").shouldBe(visible, Duration.ofSeconds(10));
            sleep(1000);
            closeAlert();
            $("ion-alert").shouldNotBe(visible, Duration.ofSeconds(10));
            sleep(200);
        });
    }

    public void popupSkipMenus() {
        sleep(1000);
        if (!Configuration.browser.equals("safari")) {
            $("ion-alert").pressEscape();
        } else {
            $("ion-alert").$("button", 1).click();
            sleep(500);
            $("app-on-map-popover").$("ion-row ion-col ion-button", 1).click();
        }
        sleep(500);
    }

    public void popupSkipEsc() {
        sleep(1000);
        $("ion-alert").pressEscape();
        sleep(500);
    }

    public void popupClickCancel() {
        sleep(500);
        $("ion-alert").$("button", 1).click();
        sleep(500);
        $("app-on-map-popover").$("ion-row ion-col ion-button", 1).click();
        sleep(500);
    }

    public void verifySuccessfulLogIn() {
        sleep(1000);
        String currentUrl = WebDriverRunner.url();
        if (!currentUrl.equals(urlProfile)) {
            fail();
        }
    }

    public void checkForErrors() {
        String errorCheck = "No errors found.";
        sleep(1500);
        if ($("ion-toast").exists()) {
            errorCheck = "Error found: " + $(ByShadow.cssSelector("div", "ion-toast")).getText();
        }
        step("Check for errors. " + errorCheck, () -> {
            if ($("ion-toast").exists()) {
                Attach.screenshotAs("Error");
                fail();
            }
        });
    }

    public void shouldHaveTitle(String value) {
        step("Page title should be: " + value, () -> {
            $("title").shouldHave(attribute("text", value));
        });
    }

    public void shouldNotHaveTitle(String value) {
        step("Page title should be: " + value, () -> {
            $("title").shouldNotHave(attribute("text", value));
        });
    }
}