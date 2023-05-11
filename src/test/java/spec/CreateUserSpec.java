package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;
import static utils.AppearanceAllure.customTemplates;

public class CreateUserSpec {
    public static RequestSpecification requestCreateUserSpec = with()
            .filter(customTemplates())
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/users");

    public static ResponseSpecification responseCreateUserSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody("name", notNullValue())
            .build();

}
