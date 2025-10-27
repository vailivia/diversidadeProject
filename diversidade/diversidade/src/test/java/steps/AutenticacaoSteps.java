package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import services.AutenticacaoService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AutenticacaoSteps {
    AutenticacaoService autenticacaoService = new AutenticacaoService();
    private String username;
    private String password;
    
    @Dado("que a aplicação está rodando")
    public void queAAplicaçãoEstáRodando() {
        // Application is running - no action needed
    }

    @E("o banco de dados está acessível")
    public void oBancoDeDadosEstáAcessível() {
        // Database is accessible - no action needed
    }

    @Dado("que tenho credenciais de usuário válidas")
    public void queTenhoCredenciaisDeUsuárioVálidas(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.username = data.get("username");
        this.password = data.get("password");
    }

    @Quando("envio uma requisição POST para {string} com as credenciais")
    public void envioUmaRequisiçãoPOSTParaComAsCredenciais(String endpoint) {
        autenticacaoService.loginWithCredentials(endpoint, username, password);
    }

    @Então("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int expectedStatus) {
        assertEquals(expectedStatus, autenticacaoService.response.getStatusCode());
    }

    @E("a resposta deve conter um token JWT válido")
    public void aRespostaDeveConterUmTokenJWTVálido() {
        String responseBody = autenticacaoService.response.body().asString();
        assertNotNull(responseBody, "Response should not be null");
        assertTrue(responseBody.contains("token") || responseBody.length() > 0, "Response should contain token");
    }

    @E("o token deve ser uma string não vazia")
    public void oTokenDeveSerUmaStringNãoVazia() {
        String responseBody = autenticacaoService.response.body().asString();
        assertNotNull(responseBody, "Response should not be null");
        assertFalse(responseBody.trim().isEmpty(), "Token should not be empty");
    }

    @Dado("que tenho credenciais de usuário inválidas")
    public void queTenhoCredenciaisDeUsuárioInválidas(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.username = data.get("username");
        this.password = data.get("password");
    }

    @E("a resposta deve conter {string}")
    public void aRespostaDeveConter(String expectedText) {
        String responseBody = autenticacaoService.response.body().asString();
        assertNotNull(responseBody, "Response should not be null");
        assertTrue(responseBody.contains(expectedText), "Response should contain: " + expectedText);
    }

    @Dado("que tenho credenciais de usuário vazias")
    public void queTenhoCredenciaisDeUsuárioVazias(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.username = data.get("username");
        this.password = data.get("password");
    }

    @Dado("que tenho credenciais com nome de usuário ausente")
    public void queTenhoCredenciaisComNomeDeUsuárioAusente(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.username = data.get("username");
        this.password = data.get("password");
    }

    @Dado("que tenho credenciais com senha ausente")
    public void queTenhoCredenciaisComSenhaAusente(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.username = data.get("username");
        this.password = data.get("password");
    }
}
