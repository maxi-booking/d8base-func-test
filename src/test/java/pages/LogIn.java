package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Lang;
import helpers.Attach;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.fail;

public class LogIn extends config.TestBase {

    public void refreshPage() {
        Selenide.refresh();
    }

    public void openMainPage() {
        open(urlBase);
    }

    public void forceMainPage() {
        if (!$("body").exists()) {
            open(urlBase);
            return;
        }
        sleep(500);
        String currentUrl = WebDriverRunner.url();
        if (!currentUrl.equals(urlBase)) {
            open(urlBase);
        }
    }

    @Step("Log out by URL")
    public void forceLogOut() {
        open(urlLogOut);
    }

    public void logoClick() {
        $$("ion-item").filter(visible).get(0).scrollIntoView(true).click();
    }

    @Step("Log In with ${login} : ${password}")
    public void logIn(String login, String password) {
        forceEN();
        sideMenu.clickLogIn();
        $("app-login").$("input", 0).setValue(login);
        $("app-login").$("input", 1).setValue(password);
        Attach.screenshotAs("Screenshot");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    @Step("No toast message")
    public void noToast() {
        sleep(1000);
        $("ion-toast").shouldNotBe(visible);
    }

    @Step("EToast message visible")
    public void toastVisible() {
        sleep(1000);
        $("ion-toast").shouldBe(visible);
    }

    public void logTempAcc() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue("2ServBknTestMaster@gg.gg");
        $("app-login").$("input", 1).setValue("qazxcdew");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void presetAcc1User() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$("[id='main-menu.log-in']").click();
        $("app-login").$("input", 0).setValue("SeleTest7@gg.gg");
        $("app-login").$("input", 1).setValue("qazxcdew");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void presetAcc2Master() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue("SeleTest6@gg.gg");
        $("app-login").$("input", 1).setValue("qazxcdew");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    @Step("Log in with {login} : {password}")
    public void account(int value) {
        forceEN();
        String login = emails[value];
        String password = passwords[value];
        sideMenu.clickLogIn();
        $$("input[name='email']").filter(visible).get(0).scrollIntoView(true).setValue(login);
        $$("input[name='password']").filter(visible).get(0).scrollIntoView(true).setValue(password);
        Attach.screenshotAs("Login_info");
        $$("ion-button[type='submit']").filter(visible).get(0).scrollIntoView(true).click();
        sleep(1000);
    }

    public void forceRU() {
        sleep(500);
        String desiredLanguage = Lang.RUSSIAN.getLangText();
        String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
        sleep(500);
        if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
            $$("[menu='flag-menu']").filter(visible).get(0).click();
            $("app-flag-menu").$("ion-select").click();
            $("ion-alert").$("button", 1).click();
            $(byText("OK")).click();
            sleep(1000);
            if ($("ion-alert").exists()) {
                $("ion-alert").pressEscape();
            }
        }
    }

    public void forceEN() {
        sleep(500);
        String desiredLanguage = Lang.ENGLISH.getLangText();
        String currentLanguage = $$("[menu='flag-menu']").filter(visible).get(0).getText();
        sleep(500);
        if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
            $$("[menu='flag-menu']").filter(visible).get(0).click();
            $("app-flag-menu").$("ion-select").click();
            $("ion-alert").$("button", 0).click();
            $(byText("OK")).click();
            sleep(1000);
            if ($("ion-alert").exists()) {
                $("ion-alert").pressEscape();
            }
        }
    }

    public void popupSelect(String country, String city) {
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
        sleep(200);
        $("ion-alert div.alert-head").shouldBe(visible, Duration.ofSeconds(10));
        $("ion-alert div.alert-head").click(0, 300);
        sleep(200);
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
}