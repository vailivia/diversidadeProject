package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import services.ColaboradorGestaoService;

public class ColaboradorGestaoSteps {
    ColaboradorGestaoService colaboradorGestaoService = new ColaboradorGestaoService();
    @E("tenho um token de autenticação válido")
    public void tenhoUmTokenDeAutenticaçãoVálido() {
    }

    @Dado("que existem funcionários no sistema com diferentes gêneros")
    public void queExistemFuncionáriosNoSistemaComDiferentesGêneros() {
    }

    @Quando("envio uma requisição GET para {string}")
    public void envioUmaRequisiçãoGETPara(String arg0) {
    }

    @E("a resposta deve conter contagens por gênero")
    public void aRespostaDeveConterContagensPorGênero() {
    }

    @Dado("que existe um funcionário com ID {int} no sistema")
    public void queExisteUmFuncionárioComIDNoSistema(int arg0) {
    }

    @Quando("envio uma requisição DELETE para {string}")
    public void envioUmaRequisiçãoDELETEPara(String arg0) {
    }

    @Dado("que não existe um funcionário com ID {int} no sistema")
    public void queNãoExisteUmFuncionárioComIDNoSistema(int arg0) {
    }

    @Dado("que não existem funcionários no sistema")
    public void queNãoExistemFuncionáriosNoSistema() {
    }

    @E("a resposta deve ser um objeto vazio")
    public void aRespostaDeveSerUmObjetoVazio() {
    }

    @Dado("que existem funcionários com valores de gênero nulos")
    public void queExistemFuncionáriosComValoresDeGêneroNulos() {
    }

    @E("a resposta deve lidar com valores de gênero nulos apropriadamente")
    public void aRespostaDeveLidarComValoresDeGêneroNulosApropriadamente() {
    }

    @Dado("que não tenho um token de autenticação válido")
    public void queNãoTenhoUmTokenDeAutenticaçãoVálido() {
    }

    @E("a resposta deve indicar acesso não autorizado")
    public void aRespostaDeveIndicarAcessoNãoAutorizado() {
    }

    @Dado("que tenho um token de autenticação inválido {string}")
    public void queTenhoUmTokenDeAutenticaçãoInválido(String arg0) {
    }
}
