package api;

import config.TestBase;
import io.restassured.response.Response;

import java.util.ArrayList;

import static helpers.CountryConverter.*;
import static io.restassured.RestAssured.given;

public class Localization extends TestBase {

    public static String[] countryList(String locale) {

        ArrayList cities = given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/countries/?page_size=9999")
                .then()
                .statusCode(200)
                .extract().response().path("results.slug");

        return (String[]) cities.toArray(String[]::new);
    }

    public static String countrySlugToName(String locale, String country) {

        return given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/countries/?search=" + country)
                .then()
                .statusCode(200)
                .extract().response().path("results.name[0]");
    }

    public static String countryNameToSlug(String locale, String country) {

        Response response = given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/countries/?search=" + country)
                .then()
                .statusCode(200)
                .extract().response();

        int i = 0;
        String countryNames = response.path("results.name[" + i + "]");
        while (countryNames != null) {
            countryNames = response.path("results.name[" + i + "]");
            if (countryNames.equals(country)) {
                break;
            }
            i++;
        }

        return response.path("results.slug[" + i + "]");
    }


    public static String[] cityList(String locale, int countryId) {

        ArrayList cities = given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/cities/?country=" + countryId + "&page_size=9999")
                .then()
                .statusCode(200)
                .extract().response().path("results.name");

        return (String[]) cities.toArray(String[]::new);
    }

    public static String getLanguageNameByLocale(String locale, String language) {

        return given()
                .accept("application/json, text/plain, */*")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/languages/" + language + "/")
                .then()
                .statusCode(200)
                .extract().response().path("name").toString();
    }
}
