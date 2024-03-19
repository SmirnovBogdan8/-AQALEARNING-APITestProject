package org.example.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private String url;
    private String endPoint;
    private String requestBody;
    private Response response;

    @Дано("Установить URL {string}")
    public void getBaseUrlStep(String baseUrl) {
        this.url = baseUrl;
    }

    @Дано("Передать тело запроса в переменную {string}")
    public void createStringVarStep(String variableName, DocString setBody) {
        this.requestBody = setBody.getContent();
    }

    @Когда("Методом GET получить данные по эндпоинту {string}")
    public void getRequestStep(String endPoint) {
        this.endPoint = endPoint;
        this.response = given()
                .when()
                .get(url + endPoint);
    }

    @Когда("Методом POST получить данные по эндпоинту {string}")
    public void postRequestStep(String endPoint) {
        this.endPoint = endPoint;
        this.response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url + endPoint);
    }

    @Когда("Методом PUT получить данные по эндпоинту {string}")
    public void putRequestStep(String endPoint) {
        this.endPoint = endPoint;
        this.response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url + endPoint);
    }

    @Когда("Методом PATCH получить данные по эндпоинту {string}")
    public void patchRequestStep(String endPoint) {
        this.endPoint = endPoint;
        this.response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url + endPoint);
    }

    @Когда("Методом DELETE получить данные по эндпоинту {string}")
    public void deleteRequestStep(String endPoint) {
        this.endPoint = endPoint;
        this.response = given().when().delete(url + endPoint);
    }

    @Тогда("Статус-код ответа должен быть {int}")
    public void assertCodeStep(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }
}