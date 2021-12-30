package helpers;

import config.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class API extends TestBase {

    public static void registration(String firstName, String lastName, String email, String password) {
                given()
                        .contentType("application/json")
                        .accept("application/json, text/plain, */*")
                        .body("{\"first_name\":\"" + firstName + "\",\"" +
                                "last_name\":\"" + lastName + "\",\"" +
                                "email\":\"" + email + "\",\"" +
                                "password\":\"" + password + "\",\"" +
                                "password_confirm\":\"" + password + "\",\"" +
                                "phone\":" + "null" + "}")
                        .when()
                        .post(urlBase + ":8000/ru/api/accounts/register/")
                        .then()
                        .statusCode(201);
    }

    @Test
    public void regt() {
        Response response =
                given()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body("{\"first_name\":\"" + userFirstName + "\",\"" +
                        "last_name\":\"" + userLastName + "\",\"" +
                        "email\":\"" + userEmailRandom + "\",\"" +
                        "password\":\"" + userPasswordRandom + "\",\"" +
                        "password_confirm\":\"" + userPasswordRandom + "\",\"" +
                        "phone\":" + "null" + "}")
                .when()
                .post(urlBase + ":8000/ru/api/accounts/register/")
                .then()
                .statusCode(201)
                .extract()
                .response();
        String accessToken = response.path("token.access_token");
        String refreshToken = response.path("token.refresh_token");
        System.out.println(response.asString());
        System.out.println(response.path("token.access_token").toString());
        System.out.println(response.path("token.refresh_token").toString());
        System.out.println("accessToken = " + accessToken);
        System.out.println("refreshToken = " + refreshToken);
//        String accessToken = response.path("token","access_token");
//        String refreshToken = response.path("token", "refresh_token");
//        System.out.println("\n access_token: " + accessToken);
//        System.out.println("\n refresh_token: " + refreshToken);
    }

    @Test
    public void authorization() {
        given()
                .contentType("application/json")
                .accept("application/json, text/plain, */*")
                .body("{\"username\":\"Email@email.gg\",\"" +
                        "password\":\"qazxcdew\",\"" +
                        "grant_type\":\"password\",\"" +
                        "client_id\":\"FpJXMu69syeuHeUV1tr9yD9hkmCQB4gyCeW5rOlU\",\"" +
                        "client_secret\":\"GfSHJvGzVLKhvZUKpmJB9ZQ9NMT1sLoxjyv1ljfd7ie5XR707HUq8zUOPH91aplhRpWZI76qIBmNeyIPpLflvy1lW9wrGv50Z0BONLbW9FJSgylGpQY4A0nK2S0dGfhX\"}")
                .when()
                .post(urlBase + ":8000/ru/api/accounts/register/")
                .then()
                .statusCode(201);
    }
}
