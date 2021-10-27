package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MasterProfile {

    @Step("Open the chat")
    public void clickChat() {
        $x("//ion-button[@class='book-btn ion-color ion-color-primary md button button-outline ion-activatable ion-focusable hydrated']").click();
    }
}
