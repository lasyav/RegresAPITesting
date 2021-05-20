import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class testUsers {

    String postBody = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";

    @BeforeMethod
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testGetUsers() {
        given()
                .get("/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPostUsers() {
        String Name = given()
                .contentType(ContentType.JSON)
                .body(postBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .path("name");
        assertEquals(Name,"morpheus");
    }
}
