package api;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Registration extends TestBase {

    public static String registration(String firstName, String lastName, String email, String password) {

        Map<String, String> data = new HashMap<>();
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email", email);
        data.put("password", password);
        data.put("password_confirm", password);
        data.put("phone", null);

        Response response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body(data)
                .when()
                .post(urlBase + ":8000/ru/api/accounts/register/")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        return response.path("access_token");
    }

    @Test
    public void registrationTest() {
        registration(userFirstName, userLastName, userEmailRandom, userPasswordRandom);
    }

    public String auth() {

        Map<String, String> data = new HashMap<>();
        data.put("username", "user@example.com");
        data.put("password", "qazxcdew");
        data.put("grant_type", "password");
        data.put("client_id", "FpJXMu69syeuHeUV1tr9yD9hkmCQB4gyCeW5rOlU");
        data.put("client_secret", "GfSHJvGzVLKhvZUKpmJB9ZQ9NMT1sLoxjyv1ljfd7ie5XR707HUq8zUOPH91aplhRpWZI76qIBmNeyIPpLflvy1lW9wrGv50Z0BONLbW9FJSgylGpQY4A0nK2S0dGfhX");

        Response response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body(data)
                .when()
                .post(urlBase + ":8000/api/auth/token/")
                .then()
                .statusCode(200)
                .extract().response();
        return response.path("access_token");
    }
}
