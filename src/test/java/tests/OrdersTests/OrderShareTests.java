package tests.OrdersTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServiceBooking.booking;
import static api.ServicePublish.*;
import static api.ServicePublish.servicePrices;
import static com.codeborne.selenide.Selenide.clipboard;

public class OrderShareTests extends TestBase {

    @Test
    @Feature("Orders")
    @Owner("Egor Khlebnikov")
    @Story("Share order")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Share order to other person")
    void t00001() { //todo
        String accessToken = registration(userFirstName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, online, instantBooking);
        int professionalLocationId = professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        booking(accessToken, serviceId, locationsId, online, forMyself, dateTime[0]);

        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();

        setRandomData();
        serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, client, instantBooking);
        serviceLocations(accessToken, serviceId, professionalLocationId, serviceLocationRandomDistance);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        booking(accessToken, serviceId, locationsId, client, forMyself, dateTime[1]);

        setRandomData();
        serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, professional, instantBooking);
        professionalLocations(accessToken, professionalId, userCountry, userCity, serviceAddress, unitsKilometers);
        int serviceLocationId = serviceLocations(accessToken, serviceId, professionalLocationId);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
        booking(accessToken, serviceId, serviceLocationId, professional, forMyself, dateTime[2]);

        topBar.clickMyOrders();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(3);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String onlineServiceShareLink = clipboard().getText();
        ord.shareOrderClickStop();
        ord.detailsOrderClickBack();

        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(2);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String clientServiceShareLink = clipboard().getText();
        ord.shareOrderClickStop();
        ord.detailsOrderClickBack();


        ord.tabCurrentOrdersInbox();
        ord.viewDetailsInbox(1);
        ord.clickShareOrder();
        ord.shareOrderClickCopy();
        String professionalServiceShareLink = clipboard().getText();
    }
}
