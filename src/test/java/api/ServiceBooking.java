package api;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static helpers.CityIdByName.getCityId;
import static helpers.CountryIdByName.getCountryId;
import static helpers.DateTimeFormatter.getDateTime;
import static helpers.SubcategoriesById.getSubcategory;
import static io.restassured.RestAssured.given;

public class ServiceBooking extends TestBase {

    public static  void booking(String accessToken, int serviceId, int locationsId, int serviceLocation, Boolean isAnotherPerson, String dateTime) {

        String source;

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", dateTime);
        data.put("is_another_person", isAnotherPerson);
        data.put("first_name", "");
        data.put("last_name", "");
        data.put("email", "");
        data.put("phone", "");
        data.put("note", "");
        if (isAnotherPerson) {
            source = "online";
        } else {
            source = "manual";
        }
        data.put("source", source);

        given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .log().all()
                .statusCode(201);
    }

    public static  void booking(String accessToken, int serviceId, int locationsId, int serviceLocation, Boolean isAnotherPerson) {

        String source;

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", getDateTime());
        data.put("is_another_person", isAnotherPerson);
        data.put("first_name", "");
        data.put("last_name", "");
        data.put("email", "");
        data.put("phone", "");
        data.put("note", "");
        if (isAnotherPerson) {
            source = "online";
        } else {
            source = "manual";
        }
        data.put("source", source);

        given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .log().all()
                .statusCode(201);
    }
}