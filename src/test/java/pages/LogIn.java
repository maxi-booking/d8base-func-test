package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.selector.ByShadow;
import helpers.Attach;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.SelectableModal.selectModal;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class LogIn extends config.TestBase {

    public void closeAlert() {
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
    }

    public void logoClick() {
        $$("ion-item").filter(visible).get(0).scrollIntoView(true).click();
    }

    public void logIn(String login, String password) {
        step("Log In with:\n" + login + "\n" + password, () -> {
            sideMenu.clickLogIn();
            $("app-login input[name='email']").setValue(login);
            $("app-login input[name='password']").setValue(password);
            $("app-login ion-input[name='email']").shouldHave(cssClass("ion-valid"));
            $("app-login ion-input[name='password']").shouldHave(cssClass("ion-valid"));
            sleep(200);
            $$("app-login-form ion-button[type='submit']").filter(visible).get(0).click();
            $$("app-login-form ion-button[type='submit']").filter(visible).get(0).shouldHave(cssClass("ion-activated"));
            $("app-login-form ion-button[type='submit']").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void noLoginError() {
        step("Login and password should be correct", () -> {
            $$("app-error-flashbag").filter(visible).get(0).shouldNot(exist);
        });
    }

    @Step("No toast message")
    public void noToast() {
        $("ion-toast").shouldNotBe(visible, Duration.ofSeconds(1));
    }

    @Step("EToast message visible")
    public void toastVisible() {
        $("ion-toast").shouldBe(visible, Duration.ofSeconds(10));
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

    public void popupSelect(String country, String city) {
        step("Pop-up select country: " + country + "; city: " + city, () -> {
            popupSkip();
            language.select(defaultLanguage);
            refreshPage();

            $("ion-alert button", 1).click();
            $("ion-alert button").shouldNotBe(visible, Duration.ofSeconds(10));

            $("app-on-map-popover app-country-selector ion-item ionic-selectable.ionic-selectable-is-enabled").shouldBe(visible);
            sleep(200);
            $("app-on-map-popover app-country-selector ion-item").click();
            selectModal(country);

            $("app-on-map-popover app-city-selector ion-item").click();
            selectModal(city);
            $("app-on-map-popover ion-button").click();
        });
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
            System.out.println("Url:" + currentUrl + ". Should be: " + urlProfile);
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

    public void openLanguageMenu() {
        step("Open language menu", () -> {
            $$("ion-menu-toggle[menu='flag-menu']").filter(visible).get(0).click();
            $$("app-flag-menu ionic-selectable").filter(visible).get(0).click();
        });
    }

    public void verifyLanguageMenuOrder() {
        step("Menu should have the right order of items", () -> {
            $("ion-modal").shouldBe(visible, Duration.ofSeconds(10));
            $$("ionic-selectable-modal ion-label").filter(visible).get(0).shouldHave(exactText("English"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(1).shouldHave(exactText("Deutsch"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(2).shouldHave(exactText("Français"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(3).shouldHave(exactText("Ελληνικά"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(4).shouldHave(exactText("Español"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(5).shouldHave(exactText("Русский"));
            $$("ionic-selectable-modal ion-label").filter(visible).get(6).shouldHave(exactText("عرب"));
        });
    }
}