import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class TestsApiSite {
    @Test
    void checkPageListUserAPI(){
        given()
                .when()
                        .get("https://reqres.in/api/users?page=2")
                .then()
                        .body("page", is(2))
                        .body("support.url", is("https://reqres.in/#support-heading"));
    }
    @Test
    void checkCreateUserAPI(){
        String body = " { \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"552\", \"createdAt\": \"2023-04-21T11:44:02.077Z\" } ";

        given()
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .body("job", is("leader"))
                        .body("name", is("morpheus"));
    }
    @Test
    void checkUpdUser(){
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
    void checkWitchSchemasInfoUser(){
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
    void checkLoginUser(){
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

}
