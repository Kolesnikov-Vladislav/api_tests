package tests;

import io.restassured.http.ContentType;
import model.lombok.BodyCreateUserWitchLombok;
import model.lombok.ResponseCreateUserWitchLombok;
import model.pojo.BodyCreateUserWitchPojo;
import model.pojo.ResponseCreateUserWitchPojo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static spec.CreateUserSpec.requestCreateUserSpec;
import static spec.CreateUserSpec.responseCreateUserSpec;

public class TestsApiSite {
    @Test
    void checkPageListUserAPI() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("page", is(2))
                .body("support.url", is("https://reqres.in/#support-heading"));
    }

    @Test
    void checkUpdUser() {
        String body = " {\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "} ";

        given()
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(201)
                .body("email", is("eve.holt@reqres.in"));
    }

    @Test
    void checkWitchSchemasInfoUser() {
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"))
                .body(matchesJsonSchemaInClasspath("shemas.json"));
    }

    @Test
    @Tag("critical")
    void checkLoginUser() {
        String bodyloginUser = " { \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" } ";
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(bodyloginUser)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void checkCreateUserAPIWitchLombok() {
        BodyCreateUserWitchLombok bodyCreateUserWitchLombok = new BodyCreateUserWitchLombok();
        bodyCreateUserWitchLombok.setName("morpheus");
        bodyCreateUserWitchLombok.setJob("leader");
        ResponseCreateUserWitchLombok responseCreateUserWitchLombok = step("Создаём запрос с следующими данными", () ->
                given(requestCreateUserSpec)
                .body(bodyCreateUserWitchLombok)
                .when()
                .post()
                .then()
                .spec(responseCreateUserSpec)
                .extract().as(ResponseCreateUserWitchLombok.class));
        step("Ожидаемый результат: ключ name соответствет указанному ключу в теле запроса", () ->
                Assertions.assertThat(responseCreateUserWitchLombok.getName()).isEqualTo("morpheus"));
    }

    @Test
    void checkCreateUserAPIWitchPojo() {
        BodyCreateUserWitchPojo bodyCreateUserWitchPojo = new BodyCreateUserWitchPojo();
        bodyCreateUserWitchPojo.setName("morpheus");
        bodyCreateUserWitchPojo.setJob("leader");
        ResponseCreateUserWitchPojo responseCreateUserWitchPojo = given()
                .log().uri()
                .body(bodyCreateUserWitchPojo)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().as(ResponseCreateUserWitchPojo.class);
        Assertions.assertThat(responseCreateUserWitchPojo.getName()).isEqualTo("morpheus");
    }
}
