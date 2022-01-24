package api;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.*;

import static helpers.CityIdByName.getCityId;
import static helpers.CountryConverter.getCountryCode;
import static helpers.CountryIdByName.getCountryId;
import static io.restassured.RestAssured.given;

public class Registration extends TestBase {

    /**
     * Create user via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * first name, email, password
     * <br />
     * first name, last name, email, password
     * <br />
     * first name, last name, email, password, country, code
     * </p>
     */
    public static String registration(String firstName, String email, String password) {

        Map<String, Object> data = new HashMap<>();
        data.put("first_name", firstName);
        data.put("email", email);
        data.put("password", password);
        data.put("password_confirm", password);

        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/register/")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response().path("token.access_token");
    }

    /**
     * Create user via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * first name, email, password
     * <br />
     * first name, last name, email, password
     * <br />
     * first name, last name, email, password, country, code
     * </p>
     */
    public static String registration(String firstName, String lastName, String email, String password) {

        Map<String, Object> data = new HashMap<>();
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email", email);
        data.put("password", password);
        data.put("password_confirm", password);

        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/register/")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response().path("token.access_token");
    }

    /**
     * Create user via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * first name, email, password
     * <br />
     * first name, last name, email, password
     * <br />
     * first name, last name, email, password, country, code
     * </p>
     */
    public static String registration(String firstName, String lastName, String email, String password, String country, String phone) {

        Map<String, Object> data = new HashMap<>();
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email", email);
        data.put("password", password);
        data.put("password_confirm", password);
        data.put("phone", getCountryCode(country) + phone);

        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/register/")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response().path("token.access_token");
    }

    public static int locations(String accessToken, String country, String city) {

        Map<String, Object> data = new HashMap<>();
        data.put("country", getCountryId(country));
        data.put("city", getCityId(city, country));

        return given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/locations/")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response().path("id");
    }
}