package tests_api;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import jdk.jfr.Description;
import models.Defect;
import org.testng.annotations.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DefectApiTests extends BaseApiTest{

    private final static String PROJECT_TITLE = "QASE for defects";
    private final static String PROJECT_CODE = "QAFD";
    private final static int DEFECT_ID = 1;

    @BeforeClass(alwaysRun = true)
    public void createProject() {
        given()
                .body(String.format("{\"code\": \"%s\", \"title\": \"%s\"}", PROJECT_CODE, PROJECT_TITLE))
                .when().log().all()
                .post("/project")
                .then().log().all();
    }

    @BeforeClass(alwaysRun = true)
    public void newDefect() {
        Defect defect = Defect.builder()
                .setTitle("Defect title")
                .setActualResult("is not working")
                .setSeverity(3)
                .build();
        given()
                .pathParam("code", PROJECT_CODE)
                .body(defect, ObjectMapperType.GSON)
                .when().log().all()
                .post("/defect/{code}")
                .then().log().all();
    }

    @AfterClass(alwaysRun = true)
    public void deleteProject() {
        given()
                .pathParam("code", PROJECT_CODE)
                .body(String.format("{\"code\": \"%s\"}", PROJECT_CODE))
                .when().log().all()
                .delete("/project/{code}")
                .then().log().all();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to create a new defect")
    @Test(groups = {"smoke", "api"})
    public void createNewDefect() {
        Defect defect = Defect.builder()
                .setTitle("Defect title")
                .setActualResult("is not working")
                .setSeverity(3)
                .build();

        given()
                .pathParam("code", PROJECT_CODE)
                .body(defect, ObjectMapperType.GSON)
                .when().log().all()
                .post("/defect/{code}")
                .then().log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("checking if it's possible to get defect data")
    @Test(groups = {"regression", "api"})
    public void getSpecificDefect() {
        given()
                .pathParam("code", PROJECT_CODE)
                .pathParam("id", DEFECT_ID)
                .when().log().all()
                .get("/defect/{code}/{id}")
                .then().log().all()
                .statusCode(200)
                .body("status", equalTo(true),
                        "result.id", equalTo(1),
                        "result.title", equalTo("Defect title"),
                        "result.actual_result", equalTo("is not working"),
                        "result.severity", equalTo("major"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to update defect's fields")
    @Test(groups = {"regression", "api"})
    public void updateDefectField() {
        Defect defect = Defect.builder()
                .setTitle("new defect title")
                .setActualResult("is still not working")
                .setSeverity(2)
                .build();

        given()
                .pathParam("code", PROJECT_CODE)
                .pathParam("id", DEFECT_ID)
                .body(defect, ObjectMapperType.GSON)
                .when().log().all()
                .patch("/defect/{code}/{id}")
                .then().log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to delete a defect")
    @Test(groups = {"smoke", "api"})
    public void deleteDefect() {
        Defect defect = Defect.builder()
                .setTitle("Defect title")
                .setActualResult("is not working")
                .setSeverity(3)
                .build();

        String defectId = given()
                .pathParam("code", PROJECT_CODE)
                .body(defect, ObjectMapperType.GSON)
                .when().log().all()
                .post("/defect/{code}")
                .then().log().all()
                .extract().path("result.id").toString();

        int defectIdToInt = Integer.parseInt(defectId);

        given()
                .pathParam("code", PROJECT_CODE)
                .pathParam("id", defectIdToInt)
                .when().log().all()
                .delete("/defect/{code}/{id}")
                .then().log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }
}
