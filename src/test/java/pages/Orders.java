package pages;

import com.codeborne.selenide.WebDriverRunner;
import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Orders extends config.TestBase {

    @Step("Open the page by url")
    public void openPageUrl(String urlOrders) {
        open(urlOrders);
    }

    @Step("Select new orders: Inbox")
    public void tabNewOrdersInbox() {
        $("app-inbox-page").$("ion-segment-button", 0).click();
    }

    @Step("Select current orders: Inbox")
    public void tabCurrentOrdersInbox() {
        $("app-inbox-page").$("ion-segment-button", 1).click();
    }

    @Step("Select archived orders: Inbox")
    public void tabArchivedOrdersInbox() {
        $("app-inbox-page").$("ion-segment-button", 2).click();
    }

    @Step("Select current orders: Outbox")
    public void tabCurrentOrdersOutbox() {
        $("app-outbox").$("ion-segment-button", 0).click();
    }

    @Step("Select archived orders: Outbox")
    public void tabArchivedOrdersOutbox() {
        $("app-outbox").$("ion-segment-button", 1).click();
    }

    @Step("Simple order check inbox")
    public void checkOrderInbox(
            String userFirstName,
            String servicePrice,
            String serviceTotalDuration
    ) {
        sleep(400);
        Attach.screenshotAs("Screenshot");
        $("app-inbox-page").shouldHave(
                text(userFirstName),
                text(serviceTotalDuration)
        );
        int cardNumber = 0;
        String servicePriceActualString = "";
        while ($("app-inbox-page").$("app-price", cardNumber).exists()) {
            String servicePriceActual = $("app-inbox-page").$("app-price", cardNumber).getText();
            servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
            servicePriceActualString = servicePriceActualString + " " + servicePriceActual;
            ++cardNumber;
        }
        if (!servicePriceActualString.contains(servicePrice)) {
            fail();
        }
    }

    @Step("Simple order check outbox")
    public void checkOrderOutbox(
            String userFirstName,
            String serviceName,
            String servicePrice,
            String serviceTotalDuration
    ) {
        $("app-outbox-page").shouldHave(
                text(userFirstName),
                text(serviceName),
                text(serviceTotalDuration)
        );
        int cardNumber = 0;
        String servicePriceActualString = "";
        while ($("app-outbox-page").$("app-price", cardNumber).exists()) {
            String servicePriceActual = $("app-outbox-page").$("app-price", cardNumber).getText();
            servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
            servicePriceActualString = servicePriceActualString + " " + servicePriceActual;
            ++cardNumber;
        }
        if (!servicePriceActualString.contains(servicePrice)) {
            fail();
        }
    }

    @Step("Click Professional's name")
    public void clickProfessionalsName() {
        $("app-sent-order-list-item").$("ion-card").$("app-professional-card").$("a").click();
    }

    @Step("Click view details")
    public void viewDetails() {
        $("app-received-order-list-item").$("ion-card").$("ion-button", 0).click();
    }

    @Step("Click view details: Outbox")
    public void viewDetailsOutbox() {
        $("app-outbox").$("ion-card").$("ion-button", 1).click();
    }

    @Step("Discard the order")
    public void discardOrder() {
        $("app-received-order-list-item").$("ion-card").$("ion-button", 1).click();
        sleep(500);
    }

    @Step("Discard the order: client")
    public void discardOrderClient(String discardComment) {
        $("app-sent-order-list-item ion-card ion-button", 1).click();
        sleep(200);
        $("ion-popover").$("app-cancel-confirmation-popover").$("ion-item", 0).click();
        sleep(200);
        $("ion-action-sheet button", 1).click();
        sleep(200);
        $("ion-popover").$("app-cancel-confirmation-popover").$("ion-item", 1).sendKeys(discardComment);
        $("ion-popover").$("app-cancel-confirmation-popover").$("ion-button").click();
        sleep(500);
    }

    @Step("Simple order check inbox")
    public void checkDiscardOrderInbox(
            String userFirstName,
            String servicePrice,
            String serviceTotalDuration
    ) {
        $("app-inbox-page").shouldNotHave(
                text(userFirstName),
                text(servicePrice),
                text(serviceTotalDuration)
        );
    }

    @Step("Simple order check outbox")
    public void checkDiscardOrderOutbox(
            String userFirstName,
            String serviceName
    ) {
        $("app-outbox-page").shouldNotHave(
                text(userFirstName),
                text(serviceName)
        );
    }

    @Step("Accept the order")
    public void acceptOrder() {
        $("app-received-order-list-item").$("ion-card").$("ion-button", 2).click();
        sleep(500);
    }

    @Step("Complete the order")
    public void completeOrder() {
        $("app-received-order-list-item ion-card ion-button").click();
        sleep(500);
    }

    @Step("Click 'show more'")
    public void clickShowMore() {
        $("app-more-info").$("ion-note").click();
    }

    @Step("Select master in Outbox")
    public void selectMaster(String userName) {
        $("app-outbox").$(byText(userName)).click();
    }
}