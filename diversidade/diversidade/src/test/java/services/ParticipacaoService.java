package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ParticipacaoService {
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:9090";

    public void criarParticipacao(String endpoint, int colaboradorId, int treinamentoId, boolean concluido, String token) {
        String url = baseUrl + endpoint;
        
        String jsonBody = "{\"colaborador_id\":" + colaboradorId + ",\"treinamento_id\":" + treinamentoId + ",\"concluido\":" + concluido + "}";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(url);
    }

    public void listarParticipacoes(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void listarParticipacoesPorColaborador(int colaboradorId, String token) {
        String url = baseUrl + "/participacoes/colaborador/" + colaboradorId;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void listarParticipacoesPorColaborador(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void atualizarParticipacao(int participacaoId, int colaboradorId, int treinamentoId, boolean concluido, String token) {
        String url = baseUrl + "/participacoes/" + participacaoId;
        
        String jsonBody = "{\"colaborador_id\":" + colaboradorId + ",\"treinamento_id\":" + treinamentoId + ",\"concluido\":" + concluido + "}";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(url);
    }

    public void atualizarParticipacao(String endpoint, int colaboradorId, int treinamentoId, boolean concluido, String token) {
        String url = baseUrl + endpoint;
        
        String jsonBody = "{\"colaborador_id\":" + colaboradorId + ",\"treinamento_id\":" + treinamentoId + ",\"concluido\":" + concluido + "}";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(url);
    }

    public void listarParticipacoesWithoutAuth(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }
}
