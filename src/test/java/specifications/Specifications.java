package specifications;

import config.TestBase;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specifications extends TestBase {
    public static RequestSpecification requestSpec = with()
//            .filter(withCustomTemplates())
            .contentType("application/json")
            .accept("application/json, text/plain, */*");
}
