package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.back;
import static helpers.RegressionTestsHelpers.*;

@Feature("Orders")
@Owner("Egor Khlebnikov")
public class OrdersTests extends config.TestBase {

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5107")
    @DisplayName("Orders: outbox titles should be correct after returning from a specific order")
    @Severity(SeverityLevel.NORMAL)
    void ordersOutboxCorrectTitlesAfterBack() {
        clientOrderReadyAPI(data);
        topBar.clickMyOrders();
        log.shouldHaveTitle("Sent orders");
        ord.verifyOrderDataOutbox(userFirstName, userLastName, userSpecialization, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        log.shouldNotHaveTitle("Sent orders");
        ord.verifyOrderId(data.orderId);
        ord.showOrderDetails();
        ord.verifyOrderDetailsOutbox(serviceName, serviceDuration, data.sType);

        back();
        //todo ord.detailsOrderClickBack(); not implemented yet - back button

        log.shouldHaveTitle("Sent orders");
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5107")
    @DisplayName("Orders: inbox titles should be correct after returning from a specific order")
    @Severity(SeverityLevel.NORMAL)
    void ordersInboxCorrectTitlesAfterBack() {
        masterOrderReadyAPI(data);
        topBar.clickMyOrders();
        log.shouldHaveTitle("Received orders");
        ord.tabCurrentOrdersInbox();
        log.shouldHaveTitle("Received orders");
        ord.verifyOrderDataInbox(clientFirstName, serviceName, servicePrice, serviceDuration);
        ord.clickViewDetails();
        log.shouldNotHaveTitle("Received orders");
        ord.clickShowMore();
        ord.verifyOrderDetailsInbox(clientFirstName, data.orderId, serviceName, servicePrice, serviceCurrencyId, serviceDuration, serviceDescription, data.sType, data.payment, data.iBooking);
        ord.detailsOrderClickBack();
        log.shouldHaveTitle("Received orders");
    }

    @Test
    @Link(name = "Issue link", url = "https://redmine.maxi-booking.ru/issues/5025")
    @DisplayName("Orders: order cards should be displayed correctly on any language")
    @Severity(SeverityLevel.CRITICAL)
    void ordersShouldBeVisibleOnAnyLanguage() {
        masterOrderReadyAPI(data);
        topBar.clickMyOrders();
        ord.checkOrderCardDisplayedWithLanguage(defaultLanguage);
        ord.checkOrderCardDisplayedWithLanguage(english);
        ord.checkOrderCardDisplayedWithLanguage(russian);
        ord.checkOrderCardDisplayedWithLanguage(german);
        ord.checkOrderCardDisplayedWithLanguage(french);
        ord.checkOrderCardDisplayedWithLanguage(spanish);
        ord.checkOrderCardDisplayedWithLanguage(arabic);
        ord.checkOrderCardDisplayedWithLanguage(greek);
    }
}