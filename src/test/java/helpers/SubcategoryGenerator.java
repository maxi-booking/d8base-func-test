package helpers;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import java.util.Locale;

import static config.TestBase.urlBackend;
import static config.TestData.xTimeZone;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

public class SubcategoryGenerator {
    public static int getCategoryCount(Response categories) {
        return categories.path("count");
    }

    public static int getCategoryIdBySubcategoryId(Response subcategories, int subcategoryId) {
        int i = 0;
        int id;
        while (true) {
            id = subcategories.path("results.id[" + i + "]");
            if (subcategoryId == id) {
                break;
            }
            i++;
        }
        return subcategories.path("results.category[" + i + "]");
    }

    public static int getCategoryOrderById(Response categories, int categoryId) {
        int i = 0;
        int id;
        while (true) {
            id = categories.path("results.id[" + i + "]");
            if (categoryId == id) {
                break;
            }
            i++;
        }
        return i;
    }

    public static int getCategoryId(Response categories, int category) {
        int i = 0;
        while (i < category) {
            i++;
        }
        return categories.path("results.id[" + i + "]");
    }

    public static String getCategoryNameByOrder(Response categories, int categoryOrder) {
        int i = 0;
        while (i < categoryOrder) {
            i++;
        }
        return categories.path("results.name[" + i + "]");
    }

    public static String getCategoryNameById(Response categories, int categoryId) {
        return categories.path("results.name[" + categoryId + "]");
    }

    public static int getSubcategoryCount(Response subcategories) {
        return subcategories.path("count");
    }

    public static int getSubcategoryId(Response subcategories, int subcategory) {
        int i = 0;
        while (i < subcategory) {
            i++;
        }
        return subcategories.path("results.id[" + i + "]");
    }

    public static String getSubcategoryName(Response subcategories, int subcategory) {
        int i = 0;
        while (i < subcategory) {
            i++;
        }
        return subcategories.path("results.name[" + i + "]");
    }

    public static int getSubcategoryOrderWithinCategory(Response subcategories, int subcategory) {
        int i = 0;
        int counter = 0;
        int category = subcategories.path("results.category[" + subcategory + "]");
        int currentCategory;
        while (i <= subcategory) {
            currentCategory = subcategories.path("results.category[" + i + "]");
            if (currentCategory == category) {
                counter++;
            }
            i++;
        }
        counter--;
        return counter;
    }

    public static int getSubcategoryTotalOrderById(Response subcategories, int subcategoryId) {
        int total = subcategories.path("count");
        int i = 0;
        int id;
        while (true) {
            if (i == total) {
                System.out.println("Invalid service ID: " + subcategoryId);
                throw new IllegalArgumentException();
            }
            id = subcategories.path("results.id[" + i + "]");
            if (subcategoryId == id) {
                break;
            }
            i++;
        }
        return i;
    }

    public static Response getCategories(String locale) {

        return given()
                .accept("application/json")
                .header("Referer", urlBackend + "/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBackend + "/" + locale + "/api/professionals/categories/?page_size=900")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public static Response getSubcategories(String locale) {

        return given()
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