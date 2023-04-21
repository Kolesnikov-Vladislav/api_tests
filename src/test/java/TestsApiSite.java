import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
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
}
