package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class Messages {

    @Step("Open the chat from Order details menu")
    public void openChatFromOrderDetails() {
        $("app-sent-order-page").$("app-chat-button").$("ion-button").click();
    }

    public void findUserChat(String userName) {
        step("Find the messenger: {userName}", () -> {
            $("app-chats-list-page ion-searchbar input").sendKeys(userName);
        });
    }

    @Step("Select user")
    public void selectUser() {
        $("app-chats-list-page").$("ion-list").$("ion-item").click();
    }

    @Step("Send the message: {textMessage}")
    public void sendMessage(String textMessage) {
        $("app-chat-page ion-footer[role='contentinfo'] ion-item").shouldHave(cssClass("item-interactive"));
        $("app-chat-page form input[name='message']").sendKeys(textMessage);
        $("app-chat-page ion-footer[role='contentinfo'] ion-item").shouldHave(cssClass("ion-valid"));
        $("app-chat-page form ion-button[type='submit']").shouldHave(cssClass("ion-activatable"));
        $("app-chat-page form ion-button[type='submit']").click();
    }

    @Step("Verify the message ({textMessage})")
    public void checkMessage(String textMessage) {
        $("app-chat-page").$("ion-content").shouldHave(text(textMessage));
    }
}