package org.example.restAssuredTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReqResTest {
    private static final String URL = "https://reqres.in/";
    @Test
    @Order(1)
    public void getListUsersTest() {
        String endPoint = "api/users?page=2";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Order(2)
    public void getSingleUserTest() {
        String endPoint = "api/users/2";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Order(3)
    public void getSUNotFoundTest() {
        String endPoint = "api/users/23";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 404);
    }

    @Test
    @Order(4)
    public void getListResourceTest() {
        String endPoint = "api/unknown";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Order(5)
    public void getSingleResourceTest() {
        String endPoint = "api/unknown/2";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Order(6)
    public void getSRNotfound() {
        String endPoint = "api/unknown/23";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 404);
    }

    @Test
    @Order(7)
    public void postCreateTest() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        String endPoint = "api/users";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + endPoint)
                .then()
                .statusCode(201);
    }

    @Test
    @Order(8)
    public void putUpdateTest() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        String endPoint = "api/users/2";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(URL + endPoint)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(9)
    public void patchUpdateTest() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        String endPoint = "api/users/2";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + endPoint)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(10)
    public void deleteTest() {
        String endPoint = "api/users/2";
        given()
                .when()
                .delete(URL + endPoint)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(11)
    public void postRegisterSuccessfulTest() {
        String requestBody = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        String endPoint = "api/register";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + endPoint)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(12)
    public void postRegisterUnsuccessfulTest() {
        String requestBody = "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
        String endPoint = "api/register";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + endPoint)
                .then()
                .statusCode(400);
    }


    @Test
    @Order(13)
    public void postLoginSuccessfulTest() {
        String requestBody = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        String endPoint = "api/login";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + endPoint)
                .then()
                .statusCode(200);
    }


    @Test
    @Order(14)
    public void postLoginUnsuccessfulTest() {
        String requestBody = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        String endPoint = "api/login";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + endPoint)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(15)
    public void getDelayedResponseTest() {
        String endPoint = "api/users?delay=3";
        Response response =  given()
                .when()
                .get(URL + endPoint);
        assertEquals(response.getStatusCode(), 200);
    }
}