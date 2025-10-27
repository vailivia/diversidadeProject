package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ColaboradorGestaoService {
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:9090";

    public void countPorGenero(String token) {
        String url = baseUrl + "/colaboradores/countPorGenero";
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void countPorGenero(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void countPorGeneroWithoutAuth(String endpoint) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }

    public void deletarColaborador(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete(url);
    }

    public void deletarColaborador(int id, String token) {
        String url = baseUrl + "/colaboradores/" + id;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .delete(url);
    }

    public void countPorGeneroInvalidToken(String endpoint, String token) {
        String url = baseUrl + endpoint;
        
        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(url);
    }
}
