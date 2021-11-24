package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.Lang;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class LogIn extends config.TestBase {

    public void refreshPage() {
        Selenide.refresh();
    }

    public void openMainPage() {
        open(urlBase);
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
        $("app-login").$("input", 0).setValue("SeleTest5@gg.gg");
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

    public void logOut() {
        open(urlLogOut);
    }

    public void account1() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser1);
        $("app-login").$("input", 1).setValue(testPassword1);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account2() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser2);
        $("app-login").$("input", 1).setValue(testPassword2);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account3() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser3);
        $("app-login").$("input", 1).setValue(testPassword3);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account4() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser4);
        $("app-login").$("input", 1).setValue(testPassword4);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account5() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser5);
        $("app-login").$("input", 1).setValue(testPassword5);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account6() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser6);
        $("app-login").$("input", 1).setValue(testPassword6);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account7() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser7);
        $("app-login").$("input", 1).setValue(testPassword7);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account10() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser10);
        $("app-login").$("input", 1).setValue(testPassword10);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }
    public void account12() {
        forceEN();
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$("[id='main-menu.log-in']").click();
        $("app-login").$("input", 0).setValue(user12Email);
        $("app-login").$("input", 1).setValue(user12Password);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void forceRU() {
        sleep(500);
        String currentUrl = WebDriverRunner.url();
        String desiredLanguage = Lang.RUSSIAN.getLangText();
        if (currentUrl.equals(urlProfile)) {
            sleep(500);
            String currentLanguage = $("app-profile").$("[menu='flag-menu']").$("ion-label").getText();
            $("app-profile").$("ion-item").click();
            sleep(500);
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $("[menu='flag-menu']").click();
                $("app-flag-menu").$("ion-select").click();
                $("ion-alert").$("button", 1).click();
                $(byText("OK")).click();
                sleep(1000);
                if ($("ion-alert").exists()) {
                    $("ion-alert").pressEscape();
                }
            }
        } else if (currentUrl.equals(urlBase)) {
            sleep(500);
            String currentLanguage = $("app-main").$("[menu='flag-menu']").$("ion-label").getText();
            $("app-main").$("ion-item").click();
            sleep(500);
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $("[menu='flag-menu']").click();
                $("app-flag-menu").$("ion-select").click();
                $("ion-alert").$("button", 1).click();
                $(byText("OK")).click();
                sleep(1000);
                if ($("ion-alert").exists()) {
                    $("ion-alert").pressEscape();
                }
            }
        } else
            fail();
    }

    public void forceEN() {
        sleep(500);
        String currentUrl = WebDriverRunner.url();
        String desiredLanguage = Lang.ENGLISH.getLangText();
        if (currentUrl.equals(urlProfile)) {
            sleep(500);
            String currentLanguage = $("app-profile").$("[menu='flag-menu']").$("ion-label").getText();
            $("app-profile").$("ion-item").click();
            sleep(500);
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $("[menu='flag-menu']").click();
                $("app-flag-menu").$("ion-select").click();
                $("ion-alert").$("button", 0).click();
                $(byText("OK")).click();
                sleep(1000);
                if ($("ion-alert").exists()) {
                    $("ion-alert").pressEscape();
                }
            }
        } else if (currentUrl.equals(urlBase)) {
            sleep(500);
            String currentLanguage = $("app-main").$("[menu='flag-menu']").$("ion-label").getText();
            $("app-main").$("ion-item").click();
            sleep(500);
            if (!desiredLanguage.equalsIgnoreCase(currentLanguage)) {
                $("[menu='flag-menu']").click();
                $("app-flag-menu").$("ion-select").click();
                $("ion-alert").$("button", 0).click();
                $(byText("OK")).click();
                sleep(1000);
                if ($("ion-alert").exists()) {
                    $("ion-alert").pressEscape();
                }
            }
        } else
            fail();
    }

    public void langRU() {
        $("app-main").$("ion-item").click();
        $("[menu='flag-menu']").click();
        $("app-flag-menu").$("ion-select").click();
        $("ion-alert").$("button", 0).click();
        $(byText("OK")).click();
    }

    public void langEN() {
        $("app-main").$("ion-item").click();
        $("[menu='flag-menu']").click();
        $("app-flag-menu").$("ion-select").click();
        $("ion-alert").$("button", 0).click();
        $(byText("OK")).click();
    }

    public void langRUProfile() {
        $("app-profile").$("ion-item").click();
        $("[menu='flag-menu']").click();
        $("app-flag-menu").$("ion-select").click();
        $("ion-alert").$("button", 1).click();
        $(byText("OK")).click();
    }

    public void langENProfile() {
        $("app-profile").$("ion-item").click();
        $("[menu='flag-menu']").click();
        $("app-flag-menu").$("ion-select").click();
        $("ion-alert").$("button", 0).click();
        $(byText("OK")).click();
    }

    public void popupSelect() {
        $("ion-alert").$("button", 1).click();

        $("app-on-map-popover").$("app-country-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(user1Country);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label").click();

        $("app-on-map-popover").$("app-city-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("ion-label").click();
        $("app-on-map-popover").$(byText("Save")).click();
    }

    public void popupSkip() {
        sleep(200);
        $("ion-alert").pressEscape();
        sleep(200);
    }

    public void clickSideMenu() {
        sleep(300);
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    public void clickSideMenuFromProfile() {
        sleep(300);
        $("app-profile").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    public void clickLogoFromProfile() {
        $("app-profile").$("ion-item").click();
    }
}
