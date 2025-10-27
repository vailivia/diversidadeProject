package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import services.ParticipacaoService;

public class ParticipacaoSteps {
    ParticipacaoService participacaoService = new ParticipacaoService();
    @Dado("que existe um funcionário com ID {int}")
    public void queExisteUmFuncionárioComID(int arg0) {
    }

    @E("existe um programa de treinamento com ID {int}")
    public void existeUmProgramaDeTreinamentoComID(int arg0) {
    }

    @E("quero criar um registro de participação")
    public void queroCriarUmRegistroDeParticipação() {
    }

    @Quando("envio uma requisição POST para {string} com os dados de participação")
    public void envioUmaRequisiçãoPOSTParaComOsDadosDeParticipação(String arg0) {
    }

    @E("a resposta deve conter os detalhes da participação criada")
    public void aRespostaDeveConterOsDetalhesDaParticipaçãoCriada() {
    }

    @E("a participação deve ter um ID válido")
    public void aParticipaçãoDeveTerUmIDVálido() {
    }

    @Dado("que existem participações em treinamento no sistema")
    public void queExistemParticipaçõesEmTreinamentoNoSistema() {
    }

    @E("a resposta deve conter uma lista de participações")
    public void aRespostaDeveConterUmaListaDeParticipações() {
    }

    @E("a lista deve conter {int} registros de participação")
    public void aListaDeveConterRegistrosDeParticipação(int arg0) {
    }

    @Dado("que existem participações em treinamento para o funcionário ID {int}")
    public void queExistemParticipaçõesEmTreinamentoParaOFuncionárioID(int arg0) {
    }

    @E("existem participações para outros funcionários")
    public void existemParticipaçõesParaOutrosFuncionários() {
    }

    @E("a resposta deve conter apenas participações do funcionário {int}")
    public void aRespostaDeveConterApenasParticipaçõesDoFuncionário(int arg0) {
    }

    @Dado("que existe um registro de participação com ID {int}")
    public void queExisteUmRegistroDeParticipaçãoComID(int arg0) {
    }

    @E("quero marcá-la como concluída")
    public void queroMarcáLaComoConcluída() {
    }

    @Quando("envio uma requisição PUT para {string} com os dados atualizados")
    public void envioUmaRequisiçãoPUTParaComOsDadosAtualizados(String arg0) {
    }

    @E("a resposta deve conter os detalhes da participação atualizada")
    public void aRespostaDeveConterOsDetalhesDaParticipaçãoAtualizada() {
    }

    @E("a participação deve estar marcada como concluída")
    public void aParticipaçãoDeveEstarMarcadaComoConcluída() {
    }

    @Dado("que não existe um funcionário com ID {int}")
    public void queNãoExisteUmFuncionárioComID(int arg0) {
    }

    @E("a resposta deve ser uma lista vazia")
    public void aRespostaDeveSerUmaListaVazia() {
    }

    @Dado("que não existem registros de participação no sistema")
    public void queNãoExistemRegistrosDeParticipaçãoNoSistema() {
    }

    @Quando("envio uma requisição POST para {string} com dados de participação")
    public void envioUmaRequisiçãoPOSTParaComDadosDeParticipação(String arg0) {
    }

    @E("a resposta deve indicar erros de validação")
    public void aRespostaDeveIndicarErrosDeValidação() {
    }

    @E("não existe um programa de treinamento com ID {int}")
    public void nãoExisteUmProgramaDeTreinamentoComID(int arg0) {
    }

    @Dado("que quero criar um registro de participação com dados ausentes")
    public void queQueroCriarUmRegistroDeParticipaçãoComDadosAusentes() {
    }

    @Quando("envio uma requisição POST para {string} com os dados incompletos")
    public void envioUmaRequisiçãoPOSTParaComOsDadosIncompletos(String arg0) {
    }

    @Dado("que não existe um registro de participação com ID {int}")
    public void queNãoExisteUmRegistroDeParticipaçãoComID(int arg0) {
    }

    @Quando("envio uma requisição PUT para {string} com dados atualizados")
    public void envioUmaRequisiçãoPUTParaComDadosAtualizados(String arg0) {
    }

    @Dado("que existem múltiplas participações com diferentes status de conclusão")
    public void queExistemMúltiplasParticipaçõesComDiferentesStatusDeConclusão() {
    }

    @E("a resposta deve conter {int} registros de participação")
    public void aRespostaDeveConterRegistrosDeParticipação(int arg0) {
    }

    @E("posso calcular que {int}% das participações estão concluídas")
    public void possoCalcularQueDasParticipaçõesEstãoConcluídas(int arg0) {
    }
}
