package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ErrosService {
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:9090";

    public void enviarJSONMalformado(String endpoint, String malformedJson) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .body(malformedJson)
                .when()
                .post(url);
    }

    public void enviarRequisicaoSemContentType(String endpoint, String body) {
        String url = baseUrl + endpoint;
        
        response = given()
                .body(body)
                .when()
                .post(url);
    }

    public void enviarRequisicaoComContentTypeNaoSuportado(String endpoint, String contentType, String body) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", contentType)
                .body(body)
                .when()
                .post(url);
    }

    public void enviarPayloadGrande(String endpoint, String largeBody) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .body(largeBody)
                .when()
                .post(url);
    }

    public void enviarRequisicaoConcorrente(String endpoint, String body1, String token1, String body2, String token2) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token1)
                .header("Content-Type", "application/json")
                .body(body1)
                .when()
                .put(url);
    }

    public void listarRecursosComTimeout(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void buscarTreinamentoComSQLInjection(String endpoint, String parameterName, String parameterValue) {
        String url = baseUrl + endpoint + "?" + parameterName + "=" + parameterValue;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void criarTreinamentoComXSS(String endpoint, String xssBody, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(xssBody)
                .when()
                .post(url);
    }

    public void criarTreinamentoComCamposObrigatoriosNulos(String endpoint, String body, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(url);
    }

    public void buscarRecursoComIDInvalido(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void enviarRequisicaoSemAutorizacao(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void enviarRequisicaoComTokenExpirado(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void enviarRequisicaoComTokenMalformado(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void enviarRequisicaoComMetodoInvalido(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .patch(url);
    }

    public void acessarEndpointInexistente(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void enviarRequisicaoComParametrosInvalidos(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void testarRequisicaoComAltaCarga(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }
}
