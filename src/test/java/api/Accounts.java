package api;

import config.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpec;

public class Accounts extends TestBase {

    public static int saveProfessional(String accessToken, int professionalId) {

        Map<String, Object> data = new HashMap<>();

        data.put("professional", professionalId);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBackend + "/ru/api/accounts/saved-professionals/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }
}
