package pages;

import com.codeborne.selenide.CollectionCondition;
import config.TestBase;
import helpers.ServiceDuration;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Currency.getCurrencySignById;
import static helpers.ServiceDuration.getDuration;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class Orders extends TestBase {

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
        $("app-inbox-page").$("ion-segment-button", 1).scrollIntoView(true).click();
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

    @Step("Simple order check inbox: order exists")
    public void checkOrderInbox(
            String userFirstName,
            String servicePrice,
            String serviceTotalDuration
    ) {
        sleep(400);
        $("app-inbox-page app-client-widget").shouldHave(
                text(userFirstName)
        );

        ServiceDuration duration = getDuration(serviceTotalDuration);
        if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
            System.out.println("Duration can not be 0");
            fail();
        }
        if (!duration.days.equals("0")) {
            $("app-inbox app-duration-viewer").shouldHave(text(duration.days));
        }
        if (!duration.hours.equals("0")) {
            $("app-inbox app-duration-viewer").shouldHave(text(duration.hours));
        }
        if (!duration.minutes.equals("0")) {
            $("app-inbox app-duration-viewer").shouldHave(text(duration.minutes));
        }

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
        $("app-outbox-page main").shouldHave(text(userFirstName));
        $("app-outbox-page main").shouldHave(text(serviceName));

        ServiceDuration duration = getDuration(serviceTotalDuration);
        if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
            System.out.println("Duration can not be 0");
            fail();
        }
        if (!duration.days.equals("0")) {
            $("app-outbox-page main").shouldHave(text(duration.days));
        }
        if (!duration.hours.equals("0")) {
            $("app-outbox-page main").shouldHave(text(duration.hours));
        }
        if (!duration.minutes.equals("0")) {
            $("app-outbox-page main").shouldHave(text(duration.minutes));
        }

        int cardNumber = 0;
        String servicePriceActualString = "";
        while ($("app-outbox-page").$("app-price", cardNumber).exists()) {
            String servicePriceActual = $("app-outbox-page").$("app-price", cardNumber).getText();
            servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
            servicePriceActualString = servicePriceActualString + " " + servicePriceActual;
            ++cardNumber;
        }
        if (!servicePriceActualString.contains(servicePrice)) {
            System.out.println("Service with incorrect price.");
            throw new IllegalArgumentException();
        }
    }

    @Step("Click Professional's name")
    public void clickProfessionalsName() {
        $("app-sent-order-list-item").$("ion-card").$("app-professional-card").$("a").click();
    }

    @Step("Click view details: Inbox")
    public void viewDetailsInbox(int index) {
        index--;
        $("app-infinite-scroll-container ion-card ion-item a", index).scrollIntoView(true).click();
    }

    @Step("Click view details: Outbox")
    public void viewDetailsOutbox() {
        $("app-outbox ion-card ion-button", 1).click();
    }

    @Step("Discard the order")
    public void discardOrder(String discardComment) {
        $("app-received-order-list-item ion-card ion-button").click();
        sleep(200);
        $("ion-popover app-cancel-confirmation-popover ion-item", 0).click();
        sleep(200);
        $("ion-action-sheet button", 1).click();
        sleep(200);
        $("ion-popover app-cancel-confirmation-popover").$("ion-item", 1).sendKeys(discardComment);
        $("ion-popover app-cancel-confirmation-popover").$("ion-button").click();
        sleep(500);
    }

    @Step("Discard the order: client")
    public void discardOrderClient(String discardComment) {
        $("app-sent-order-list-item ion-card ion-button", 1).click();
        sleep(200);
        $("ion-popover app-cancel-confirmation-popover ion-item", 0).click();
        sleep(200);
        $("ion-action-sheet button", 1).click();
        sleep(200);
        $("ion-popover app-cancel-confirmation-popover").$("ion-item", 1).sendKeys(discardComment);
        $("ion-popover app-cancel-confirmation-popover").$("ion-button").click();
        sleep(500);
    }

    @Step("Simple order check inbox: order discarded")
    public void checkDiscardOrderInbox(
            String userFirstName,
            String servicePrice,
            String serviceTotalDuration
    ) {
        $("app-inbox-page app-infinite-scroll-container").shouldNotHave(text(userFirstName));
        $("app-inbox-page app-infinite-scroll-container").shouldNotHave(text(servicePrice));

        ServiceDuration duration = getDuration(serviceTotalDuration);
        if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
            System.out.println("Duration can not be 0");
            fail();
        }
        if (!duration.days.equals("0")) {
            $("app-inbox-page app-infinite-scroll-container").shouldNotHave(text(duration.days));
        }
        if (!duration.hours.equals("0")) {
            $("app-inbox-page app-infinite-scroll-container").shouldNotHave(text(duration.hours));
        }
        if (!duration.minutes.equals("0")) {
            $("app-inbox-page app-infinite-scroll-container").shouldNotHave(text(duration.minutes));
        }
    }

    @Step("Orders Inbox should be empty")
    public void verifyNoOrdersInOrderInbox() {
        $$("app-inbox-page ion-card").shouldBe(CollectionCondition.size(0));
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
        int value = 0;
        while ($("ion-card", value).exists()) {
            value++;
        }
        $("app-received-order-list-item ion-card ion-button", 2).click();
        $("ion-card", value).shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Complete the order")
    public void completeOrder() {
        int value = 0;
        while ($("ion-card", value).exists()) {
            value++;
        }
        if ($("app-received-order-list-item ion-card ion-button[color='danger']", 0).exists()) {
            $("app-received-order-list-item ion-card ion-button", 1).click();
        } else {
            $("app-received-order-list-item ion-card ion-button").click();
        }
        $("ion-card", value).shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify that the order can't be completed")
    public void canNotCompleteOrder() {
        int value = 0;
        while ($("ion-card", value).exists()) {
            $("app-received-order-list-item ion-card ion-button.button-disabled", value).shouldBe(visible);
            value++;
        }
    }

    @Step("Click 'show more'")
    public void clickShowMore() {
        $("app-more-info ion-label").click();
    }

    @Step("Select master in Outbox")
    public void selectMaster(String userName) {
        $("app-outbox").$(byText(userName)).click();
    }

    // Share Order

    public void clickShareOrder() {
        step("Click share order button", () -> {
            $("app-infinite-scroll-container").shouldNotBe(visible, Duration.ofSeconds(10));
            $("app-received-order-page").shouldBe(visible, Duration.ofSeconds(10));
            $("app-received-order-page ion-button[slot='start']").scrollIntoView(true).click();
        });
    }

    public void shareOrderClickCopy() {
        step("Click copy button", () -> {
            $("app-order-share-popover ion-button[slot='end']").click();
        });
    }

    @Step("Get share order link")
    public String shareOrderGetId() {
        String value = $("app-order-share-popover input").getValue();
        return value;
    }

    public void shareOrderClickStop() {
        step("Click 'stop viewing by link' button", () -> {
            $("app-order-share-popover ion-button[color='danger']").click();
        });
    }

    public void detailsOrderClickBack() {
        step("Click back button, order details", () -> {
            $("ion-button[routerlink='/my-orders/inbox']").scrollIntoView(true).click();
        });
    }

    public void openOrderToShare() {
        step("Paste shared order link and open it", () -> {
            String copiedText = clipboard().getText();
            open(copiedText);
        });
    }

    public void shareOrderClickConfirm() {
        step("User confirms shared order", () -> {
            $("app-order-share main ion-button").click();
        });
    }

    public void shareOrderHaveAccount() {
        step("Select: I already have an account", () -> {
            $("ion-item ion-radio", 0).click();
        });
    }

    public void shareOrderNewUser() {
        step("Select: I'm new user", () -> {
            $("ion-item ion-radio", 1).click();
        });
    }

    public void verifyOrderId(int orderId) {
        step("Verify that order ID is " + orderId, () -> {
            $$("app-sent-order div h1").filter(visible).get(0).shouldHave(text(String.valueOf(orderId)));
        });
    }

    public void showOrderDetails() {
        step("Click 'show order details'", () -> {
            sleep(200);
            $$("div.order-full-info a").filter(visible).get(0).click();
        });
    }

    public void verifyOrderExists(int id) {
        step("Verify successful order exists.", () -> {
            $$("app-infinite-scroll-container app-order-status ion-badge[color='success']").filter(visible).get(id).shouldBe(visible);
        });
    }

    public void verifyOrderDataOutbox(String firstName, String lastName, String specialization, String serviceName, String servicePrice, String serviceDuration) {
        step("Verify that order data is all correct.", () -> {
            $$("app-infinite-scroll-container app-order-status ion-badge[color='success']").filter(visible).get(0).shouldBe(visible);
            $$("app-infinite-scroll-container app-professional-card").filter(visible).get(0).shouldHave(text(firstName + " " + lastName), text(specialization));
            $$("app-infinite-scroll-container app-service-title").filter(visible).get(0).shouldHave(text(serviceName));
            $$("app-infinite-scroll-container app-price").filter(visible).get(0).shouldHave(text(servicePrice));
            $$("app-infinite-scroll-container app-duration-viewer").filter(visible).get(0).shouldHave(text(serviceDuration));
        });
    }

    public void verifyOrderDataInbox(String firstName, String lastName, String serviceName, String servicePrice, String serviceDuration) {
        step("Verify that order data is all correct.", () -> {
            $$("app-infinite-scroll-container app-order-status ion-badge[color='success']").filter(visible).get(0).shouldBe(visible);
            $$("app-infinite-scroll-container app-client-widget").filter(visible).get(0).shouldHave(text(firstName + " " + lastName));
            $$("app-infinite-scroll-container app-service-title").filter(visible).get(0).shouldHave(text(serviceName));
            $$("app-infinite-scroll-container app-price").filter(visible).get(0).shouldHave(text(servicePrice));
            $$("app-infinite-scroll-container app-duration-viewer").filter(visible).get(0).shouldHave(text(serviceDuration));
        });
    }

    public void verifyOrderDataInbox(String firstName, String serviceName, String servicePrice, String serviceDuration) {
        step("Verify that order data is all correct.", () -> {
            $$("app-infinite-scroll-container app-order-status ion-badge[color='success']").filter(visible).get(0).shouldBe(visible);
            $$("app-infinite-scroll-container app-client-widget").filter(visible).get(0).shouldHave(text(firstName));
            $$("app-infinite-scroll-container app-service-title").filter(visible).get(0).shouldHave(text(serviceName));
            $$("app-infinite-scroll-container app-price").filter(visible).get(0).shouldHave(text(servicePrice));
            $$("app-infinite-scroll-container app-duration-viewer").filter(visible).get(0).shouldHave(text(serviceDuration));
        });
    }

    public void clickViewDetails() {
        step("Click 'View Details'", () -> {
            $("app-infinite-scroll-container ion-item a").click();
        });
    }

    public void verifyOrderDetailsOutbox(String serviceName, String serviceDuration, int serviceLocation) {
        step("Verify order details. Service name: " + serviceName + ". Service duration: " + serviceDuration, () -> {
            $$("section").filter(visible).get(0).shouldHave(text(serviceName), text(serviceDuration));
            if (serviceLocation == 0) {
                $$("section").filter(visible).get(0).shouldHave(text(onlineLocation));
            } else if (serviceLocation == 1) {
                $$("section").filter(visible).get(0).shouldHave(text(clientLocation));
            } else if (serviceLocation == 2) {
                $$("section").filter(visible).get(0).shouldHave(text(professionalLocation));
            } else {
                System.out.println("Impossible service location. Should be online/client/professional.");
                throw new IllegalArgumentException();
            }
        });
    }

    public void verifyOrderDetailsOutbox(String serviceName, String serviceDuration, String serviceAddress) {
        step("Verify order details. Service name: " + serviceName + ". Service duration: " + serviceDuration + ". Service duration: " + serviceAddress, () -> {
            $$("section").filter(visible).get(0).shouldHave(text(serviceName), text(serviceDuration), text(serviceAddress));
        });
    }

    public void verifyOrderDetailsInbox(String userFirstName,
                                        String userLastName,
                                        int serviceId,
                                        String serviceName,
                                        String servicePrice,
                                        int serviceCurrencyId,
                                        String serviceTotalDuration,
                                        String serviceDescription,
                                        int serviceLocation,
                                        String[] paymentOptions,
                                        Boolean instantBooking) {
        step("Verify order details. Service name: " + serviceName + ". Service duration: " + serviceDuration, () -> {
            $$("app-client-widget").filter(visible).get(0).shouldHave(text(userFirstName + " " + userLastName));
            $$("app-received-order").filter(visible).get(0).shouldHave(text(String.valueOf(serviceId)));
            $$("app-service-title").filter(visible).get(0).shouldHave(text(serviceName));

            $$("app-price").filter(visible).get(0).shouldHave(text(servicePrice + " " + getCurrencySignById(serviceCurrencyId)));

            ServiceDuration duration = getDuration(serviceTotalDuration);
            if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
                System.out.println("Duration can not be 0");
                throw new IllegalArgumentException();
            }
            if (!duration.days.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.days));
            }
            if (!duration.hours.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.hours));
            }
            if (!duration.minutes.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.minutes));
            }

            $$("app-more-info ion-item").filter(visible).get(0).shouldHave(text(serviceDescription));

            if (serviceLocation == 0) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(onlineLocation));
            } else if (serviceLocation == 1) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(clientLocation));
            } else if (serviceLocation == 2) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(professionalLocation));
            } else {
                System.out.println("Impossible service location. Should be online/client/professional.");
                throw new IllegalArgumentException();
            }

            if (serviceLocation == 0) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(onlineLocation));
            } else if (serviceLocation == 1) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(clientLocation));
            } else if (serviceLocation == 2) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(professionalLocation));
            } else {
                System.out.println("Impossible service location. Should be online/client/professional.");
                throw new IllegalArgumentException();
            }

            if (paymentOptions == paymentCashOnline) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentCashOnline[0]), text(paymentCashOnline[1]));
            } else if (paymentOptions == paymentCash) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentCash[0]));
            } else if (paymentOptions == paymentOnline) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentOnline[0]));
            } else {
                System.out.println("Unknown payment options.");
                throw new IllegalArgumentException();
            }

            if (instantBooking == on) {
                $("ion-icon[name='checkbox-outline']").shouldBe(visible);
            } else if (instantBooking == off) {
                $("ion-icon[name='checkbox-outline']").shouldNotBe(visible);
            } else {
                System.out.println("Instant Booking error, value not found.");
                throw new IllegalArgumentException();
            }

            $$("ion-badge[color='success']").filter(visible).get(0).should(exist);

            //todo add bottom part
        });
    }

    public void verifyOrderDetailsInbox(String userFirstName,
                                        int orderId,
                                        String serviceName,
                                        String servicePrice,
                                        int serviceCurrencyId,
                                        String serviceTotalDuration,
                                        String serviceDescription,
                                        int serviceLocation,
                                        String[] paymentOptions,
                                        Boolean instantBooking) {
        step("Verify order details. Service name: " + serviceName + ". Service duration: " + serviceDuration, () -> {
            $$("app-client-widget").filter(visible).get(0).shouldHave(text(userFirstName));
            $$("app-received-order").filter(visible).get(0).shouldHave(text(String.valueOf(orderId)));
            $$("app-service-title").filter(visible).get(0).shouldHave(text(serviceName));

            $$("app-price").filter(visible).get(0).shouldHave(text(servicePrice + " " + getCurrencySignById(serviceCurrencyId)));

            ServiceDuration duration = getDuration(serviceTotalDuration);
            if (duration.days.equals("0") && duration.hours.equals("0") && duration.minutes.equals("0")) {
                System.out.println("Duration can not be 0");
                throw new IllegalArgumentException();
            }
            if (!duration.days.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.days));
            }
            if (!duration.hours.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.hours));
            }
            if (!duration.minutes.equals("0")) {
                $$("app-duration-viewer").filter(visible).get(0).shouldHave(text(duration.minutes));
            }

            $$("app-more-info ion-item").filter(visible).get(0).shouldHave(text(serviceDescription));

            if (serviceLocation == 0) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(onlineLocation));
            } else if (serviceLocation == 1) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(clientLocation));
            } else if (serviceLocation == 2) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(professionalLocation));
            } else {
                System.out.println("Impossible service location. Should be online/client/professional.");
                throw new IllegalArgumentException();
            }

            if (serviceLocation == 0) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(onlineLocation));
            } else if (serviceLocation == 1) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(clientLocation));
            } else if (serviceLocation == 2) {
                $$("app-service-location").filter(visible).get(0).shouldHave(text(professionalLocation));
            } else {
                System.out.println("Impossible service location. Should be online/client/professional.");
                throw new IllegalArgumentException();
            }

            if (paymentOptions == paymentCashOnline) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentCashOnline[0]), text(paymentCashOnline[1]));
            } else if (paymentOptions == paymentCash) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentCash[0]));
            } else if (paymentOptions == paymentOnline) {
                $$("app-payment-method-viewer").filter(visible).get(0).shouldHave(text(paymentOnline[0]));
            } else {
                System.out.println("Unknown payment options.");
                throw new IllegalArgumentException();
            }

            if (instantBooking == on) {
                $("ion-icon[name='checkbox-outline']").shouldBe(visible);
            } else if (instantBooking == off) {
                $("ion-icon[name='checkbox-outline']").shouldNotBe(visible);
            } else {
                System.out.println("Instant Booking error, value not found.");
                throw new IllegalArgumentException();
            }

            $$("ion-badge[color='success']").filter(visible).get(0).should(exist);

            //todo add bottom part
        });
    }

    public void checkOrderCardDisplayedWithLanguage(String value) {
        step("Check order card is displayed for " + value, () -> {
            language.select(value);
            ord.tabCurrentOrdersInbox();
            ord.verifyOrderExists(0);
        });
    }
}