package helpers;

import config.TestBase;
import io.restassured.response.Response;

import static helpers.CountryConverter.*;
import static io.restassured.RestAssured.given;

public class CityIdByName extends TestBase {

    public static int getCityId(String locale, String city, String country) {
        Response response = cityIdByName(locale, city, country);
        int i = 0;
        String cityNames = response.path("results.name[" + i + "]");
        while (cityNames != null) {
            cityNames = response.path("results.name[" + i + "]");
            if (cityNames.equals(city)) {
                break;
            }
            i++;
        }
        return response.path("results.id[" + i + "]");
    }

    public static Response cityIdByName(String locale, String city, String country) {

        return given()
//                .filter(new AllureRestAssured())
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/en/api/location/cities/?by_name=" + city + "&country=" + getCountryId(locale, country))
                .then()
                .statusCode(200)
                .extract().response();
    }

/*
    public static String getCityId(String city) {
        return cityData.get(city);
    }

    private static final Map<String, String> cityData = new HashMap<>();
    static {
        cityData.put("Kabul", "1138958");
        cityData.put("Toronto", "6167865");
        cityData.put("Helsinki", "658225");
        cityData.put("Paris", "2988507");
        cityData.put("Berlin", "2950159");
        cityData.put("Moscow", "524901");
    }*/
}