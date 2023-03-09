package tests_api;

import io.restassured.RestAssured;
import io.restassured.http.*;
import org.apache.http.protocol.*;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.*;

public abstract class BaseApiTest {

    private static final String TOKEN = "1e5c35f94b1a30f113ab2a0dbf7cf6b212e36a0ff7d8c58d12ce2a1fc2ec8491";
    protected static final String BASE_URL = "https://api.qase.io/v1";

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .header("Token", TOKEN)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }
}
