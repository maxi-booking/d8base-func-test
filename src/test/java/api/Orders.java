package api;

import config.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpec;

public class Orders extends TestBase {

    public static void orderComplete(String locale, String accessToken, int orderId) {

        Map<String, Object> data = new HashMap<>();

        data.put("id", orderId);
        data.put("status", "completed");

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .patch(urlBackend + "/" + locale + "/api/accounts/orders/received/" + orderId + "/")
                .then()
                .statusCode(200);
    }

    public static int sendReview(String locale, String accessToken, int professionalId, String review, int rating) {

        Map<String, Object> data = new HashMap<>();

        data.put("professional", professionalId);
        data.put("description", review);
        data.put("rating", rating);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBackend + "/" + locale + "/api/accounts/reviews/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }
}
