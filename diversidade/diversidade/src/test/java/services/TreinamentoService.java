package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TreinamentoService {
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:9090";

    public void criarTreinamento(String endpoint, String titulo, String descricao, String data, boolean obrigatorio, String token) {
        String url = baseUrl + endpoint;
        
        String jsonBody = "{\"titulo\":\"" + titulo + "\",\"descricao\":\"" + descricao + "\",\"data\":\"" + data + "\",\"obrigatorio\":" + obrigatorio + "}";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(url);
    }

    public void listarTreinamentos(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void obterTreinamentoPorId(int id, String token) {
        String url = baseUrl + "/treinamentos/" + id;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void obterTreinamentoPorId(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void atualizarTreinamento(int id, String titulo, String descricao, String data, boolean obrigatorio, String token) {
        String url = baseUrl + "/treinamentos/" + id;
        
        String jsonBody = "{\"titulo\":\"" + titulo + "\",\"descricao\":\"" + descricao + "\",\"data\":\"" + data + "\",\"obrigatorio\":" + obrigatorio + "}";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(url);
    }

    public void deletarTreinamento(int id, String token) {
        String url = baseUrl + "/treinamentos/" + id;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete(url);
    }

    public void criarTreinamentoComErro(String endpoint, String jsonBody, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(url);
    }

    public void criarTreinamentoMalformado(String endpoint, String bodyContent) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .body(bodyContent)
                .when()
                .post(url);
    }

    public void enviarRequisicaoComTipoConteudoInvalido(String endpoint, String contentType) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", contentType)
                .when()
                .post(url);
    }

    public void enviarRequisicaoSemConteudo(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .when()
                .post(url);
    }
}
