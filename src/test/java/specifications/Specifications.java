package specifications;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specifications extends TestBase {
    public static RequestSpecification requestSpec = with()
            .log().all()
            .filter(new AllureRestAssured())
            .contentType("application/json")
            .accept("application/json, text/plain, */*");
}
