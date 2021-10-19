package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LogIn extends config.TestBase {

    public void refreshPage() {
        Selenide.refresh();
    }

    public void presetAcc1User() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue("SeleTest5@gg.gg");
        $("app-login").$("input", 1).setValue("qazxcdew");
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void presetAcc2Master() {
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
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser1);
        $("app-login").$("input", 1).setValue(testPassword1);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account2() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser2);
        $("app-login").$("input", 1).setValue(testPassword2);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account3() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser3);
        $("app-login").$("input", 1).setValue(testPassword3);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account4() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser4);
        $("app-login").$("input", 1).setValue(testPassword4);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account5() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser5);
        $("app-login").$("input", 1).setValue(testPassword5);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account6() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser6);
        $("app-login").$("input", 1).setValue(testPassword6);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account7() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser7);
        $("app-login").$("input", 1).setValue(testPassword7);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void account10() {
        $("ion-buttons").$("ion-menu-toggle").$("ion-button").click();
        sleep(500);
        $("app-main-menu").$(byText("Log in")).click();
        $("app-login").$("input", 0).setValue(testUser10);
        $("app-login").$("input", 1).setValue(testPassword10);
        $("app-login-form").$("ion-button[type='submit']").click();
        sleep(1000);
    }

    public void langRU() {
        $("app-profile").$("ion-item").click();
        $("[menu='flag-menu']").click();
        $("app-flag-menu").$("ion-select").click();
        $("ion-alert").$("button", 1).click();
        $(byText("OK")).click();
    }

    public void langEN() {
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
        $("ion-alert").pressEscape();
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
