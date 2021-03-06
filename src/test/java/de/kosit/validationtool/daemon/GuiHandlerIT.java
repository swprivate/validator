package de.kosit.validationtool.daemon;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GuiHandlerIT extends BaseIT {

    @Test
    public void checkGui() {
        given().when().get("/").then().statusCode(200).and().contentType(ContentType.HTML);
        given().when().get("/README.md").then().statusCode(200).and().contentType("text/markdown");
        given().when().get("/unknown.md").then().statusCode(404).and().contentType(ContentType.TEXT);
    }
}
