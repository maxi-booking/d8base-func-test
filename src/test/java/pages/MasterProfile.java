package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MasterProfile {

    @Step("Open the chat")
    public void clickChat() {
        $("app-professional-page").$("section").$("ion-button",0).click();
    }
}
