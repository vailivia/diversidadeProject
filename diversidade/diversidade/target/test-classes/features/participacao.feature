# language: pt
Funcionalidade: Gerenciamento de Participações
  Como administrador de treinamentos
  Eu quero acompanhar a participação dos funcionários nos programas de treinamento
  Para que eu possa monitorar a conclusão e eficácia dos treinamentos

  Contexto:
    Dado que a aplicação está rodando
    E o banco de dados está acessível
    E tenho um token de autenticação válido

  Cenário: Criar um registro de participação em treinamento com sucesso
    Dado que existe um funcionário com ID 1
    E existe um programa de treinamento com ID 1
    E quero criar um registro de participação
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | false     |
    Quando envio uma requisição POST para "/participacoes" com os dados de participação
    Então o status da resposta deve ser 200
    E a resposta deve conter os detalhes da participação criada
    E a participação deve ter um ID válido

  Cenário: Listar todas as participações em treinamento com sucesso
    Dado que existem participações em treinamento no sistema
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | true      |
      | 2              | 1              | false     |
      | 1              | 2              | true      |
    Quando envio uma requisição GET para "/participacoes"
    Então o status da resposta deve ser 200
    E a resposta deve conter uma lista de participações
    E a lista deve conter 3 registros de participação

  Cenário: Listar participações por funcionário com sucesso
    Dado que existem participações em treinamento para o funcionário ID 1
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | true      |
      | 1              | 2              | false     |
      | 1              | 3              | true      |
    E existem participações para outros funcionários
      | colaborador_id | treinamento_id | concluido |
      | 2              | 1              | false     |
    Quando envio uma requisição GET para "/participacoes/colaborador/1"
    Então o status da resposta deve ser 200
    E a resposta deve conter apenas participações do funcionário 1
    E a lista deve conter 3 registros de participação

  Cenário: Marcar participação como concluída com sucesso
    Dado que existe um registro de participação com ID 1
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | false     |
    E quero marcá-la como concluída
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | true      |
    Quando envio uma requisição PUT para "/participacoes/1" com os dados atualizados
    Então o status da resposta deve ser 200
    E a resposta deve conter os detalhes da participação atualizada
    E a participação deve estar marcada como concluída

  Cenário: Listar participações para funcionário inexistente
    Dado que não existe um funcionário com ID 999
    Quando envio uma requisição GET para "/participacoes/colaborador/999"
    Então o status da resposta deve ser 200
    E a resposta deve ser uma lista vazia

  Cenário: Listar todas as participações quando nenhuma existe
    Dado que não existem registros de participação no sistema
    Quando envio uma requisição GET para "/participacoes"
    Então o status da resposta deve ser 200
    E a resposta deve ser uma lista vazia

  Cenário: Criar participação com funcionário inexistente
    Dado que não existe um funcionário com ID 999
    E existe um programa de treinamento com ID 1
    Quando envio uma requisição POST para "/participacoes" com dados de participação
      | colaborador_id | treinamento_id | concluido |
      | 999            | 1              | false     |
    Então o status da resposta deve ser 400
    E a resposta deve indicar erros de validação

  Cenário: Criar participação com programa de treinamento inexistente
    Dado que existe um funcionário com ID 1
    E não existe um programa de treinamento com ID 999
    Quando envio uma requisição POST para "/participacoes" com dados de participação
      | colaborador_id | treinamento_id | concluido |
      | 1              | 999            | false     |
    Então o status da resposta deve ser 400
    E a resposta deve indicar erros de validação

  Cenário: Criar participação com campos obrigatórios ausentes
    Dado que quero criar um registro de participação com dados ausentes
      | colaborador_id | treinamento_id | concluido |
      |                | 1              | false     |
    Quando envio uma requisição POST para "/participacoes" com os dados incompletos
    Então o status da resposta deve ser 400
    E a resposta deve indicar erros de validação

  Cenário: Atualizar registro de participação inexistente
    Dado que não existe um registro de participação com ID 999
    Quando envio uma requisição PUT para "/participacoes/999" com dados atualizados
    Então o status da resposta deve ser 404

  Cenário: Acessar endpoints de participação sem autenticação
    Dado que não tenho um token de autenticação válido
    Quando envio uma requisição GET para "/participacoes"
    Então o status da resposta deve ser 401
    E a resposta deve indicar acesso não autorizado

  Cenário: Acompanhar taxa de conclusão de participações
    Dado que existem múltiplas participações com diferentes status de conclusão
      | colaborador_id | treinamento_id | concluido |
      | 1              | 1              | true      |
      | 2              | 1              | false     |
      | 3              | 1              | true      |
      | 4              | 1              | false     |
    Quando envio uma requisição GET para "/participacoes"
    Então o status da resposta deve ser 200
    E a resposta deve conter 4 registros de participação
    E posso calcular que 50% das participações estão concluídas
