package tests;

import allure.Layer;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Report.Report.filters;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Layer("api")
@Owner("EpicFate")
public class demoqa_com_API_Tests {

    @Test
    @Feature("Book Store API")
    @Story("BookStore")
    @DisplayName("BookStore (GET) test")
    @Link(url = "https://demoqa.com/swagger/#/BookStore", name = "https://demoqa.com/swagger/#/BookStore")
    void BookStoreGetApiTest() {
        given()
                .when()
                .filter(filters().customTemplates())
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .log().body()
                .body("books", is(hasSize(8)));
    }

    @Test
    @Feature("Book Store API")
    @Story("Account")
    @DisplayName("Account GenerateToken (post) test")
    @Link(url = "https://demoqa.com/swagger/#/BookStore", name = "https://demoqa.com/swagger/#/BookStore")
    void AccountGenerateTokenPostTest() {
        String data = "{\n" +
                "  \"userName\": \"EpicFate1\",\n" +
                "  \"password\": \"Aa12343string!\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .filter(filters().customTemplates())
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .statusCode(200)
                .log().body()
                .body("status", is("Success"));
    }
}
