package helpers;

import config.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SubcategoriesById extends TestBase {

    public static int getSubcategory(String locale, int category, int subcategory) {
        Response response = subcategories(locale);
        category++;
        subcategory++;
        if (category > 3) {
            category++;
            if (category == 10) {
                category = 9;
            } else if (category == 9) {
                category = 10;
            }
        }
        int i = 0;
        int categoryCheck;
        int subcategoryCounter = 0;
        while (i < subcategory) {
            categoryCheck = response.path("results.category[" + subcategoryCounter + "]");
            if (categoryCheck == category) {
                i++;
            }
            subcategoryCounter++;
        }
        if (subcategoryCounter > 26) {
            subcategoryCounter = subcategoryCounter + 3;
        }
        return subcategoryCounter;
    }

    public static Response subcategories(String locale) {

        return given()
//                .filter(new AllureRestAssured())
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/professionals/subcategories/?page_size=900")
                .then()
                .statusCode(200)
                .extract().response();
    }
}