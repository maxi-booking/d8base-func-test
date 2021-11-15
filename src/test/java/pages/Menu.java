package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Menu {
    public void openMenu() {
        $("ion-button").click();
        sleep(300);
    }

    @Step("Click Menu > Professional profile")
    public void menuClickProfessionalProfile() {
        openMenu();
        $("[id='main-menu.professional-profile']").click();
        sleep(300);
    }
}
