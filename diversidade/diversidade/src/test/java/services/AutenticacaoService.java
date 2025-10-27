package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AutenticacaoService {
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:9090";

    public void login(String username, String password) {
        String url = baseUrl + "/auth/login";
        
        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        
        response = given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(url);
    }

    public void loginWithCredentials(String endpoint, String username, String password) {
        String url = baseUrl + endpoint;
        
        String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        
        response = given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(url);
    }
}
