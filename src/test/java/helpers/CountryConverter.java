package helpers;

import config.TestBase;
import io.restassured.response.Response;

import static api.Localization.countryNameToSlug;
import static io.restassured.RestAssured.given;

public class CountryConverter extends TestBase {

    private static int i(Response response, String country) {
        int i = 0;
        String countryNames = response.path("results.slug[" + i + "]");
        while (countryNames != null) {
            countryNames = response.path("results.slug[" + i + "]");
            if (countryNames.equals(country)) {
                break;
            }
            i++;
        }
        return i;
    }

    public static int getCountryId(String locale, String country) {
        Response response = countryIdByName(locale, country);
        country = countryNameToSlug(locale, country);
        return response.path("results.id[" + i(response, country) + "]");
    }

    public static Response countryIdByName(String locale, String country) {
        country = countryNameToSlug(locale, country);

        return given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/countries/?search=" + country)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public static String getCountryCode(String locale, String country) {
        country = countryNameToSlug(locale, country);
        Response response = given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/location/countries/?search=" + country)
                .then()
                .statusCode(200)
                .extract().response();
        return response.path("results.phone[" + i(response, country) + "]");
    }
}