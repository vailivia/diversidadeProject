# language: pt
Funcionalidade: Tratamento de Erros e Casos Extremos
  Como usuário do sistema
  Eu quero que o sistema trate erros graciosamente
  Para que a aplicação permaneça estável e forneça feedback significativo

  Contexto:
    Dado que a aplicação está rodando
    E o banco de dados está acessível

  Cenário: Lidar com JSON malformado no corpo da requisição
    Dado que tenho dados JSON malformados
    Quando envio uma requisição POST para "/treinamentos" com JSON malformado
    Então o status da resposta deve ser 400
    E a resposta deve indicar erro de parsing JSON

  Cenário: Lidar com requisição sem cabeçalho Content-Type
    Dado que tenho dados de treinamento válidos
    E não incluo o cabeçalho Content-Type
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve indicar erro de tipo de conteúdo

  Cenário: Lidar com requisição com Content-Type não suportado
    Dado que tenho dados de treinamento válidos
    E defino Content-Type como "application/xml"
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 415
    E a resposta deve indicar tipo de mídia não suportado

  Cenário: Lidar com requisição com payload extremamente grande
    Dado que tenho dados de treinamento com descrição extremamente grande
      | titulo | descricao | data       | obrigatorio |
      | Test   | [10000 character string] | 2024-02-15 | true |
    Quando envio uma requisição POST para "/treinamentos" com os dados grandes
    Então o status da resposta deve ser 413
    E a resposta deve indicar payload muito grande

  Cenário: Lidar com requisições concorrentes para o mesmo recurso
    Dado que existe um programa de treinamento com ID 1
    E múltiplos usuários estão tentando atualizá-lo simultaneamente
    Quando o Usuário A envia uma requisição PUT para "/treinamentos/1" com dados A
    E o Usuário B envia uma requisição PUT para "/treinamentos/1" com dados B
    Então ambas as requisições devem ser processadas
    E o estado final deve ser consistente

  Cenário: Lidar com timeout de conexão do banco de dados
    Dado que a conexão do banco de dados está com problemas de timeout
    Quando envio uma requisição GET para "/treinamentos"
    Então o status da resposta deve ser 500
    E a resposta deve indicar erro de conexão com o banco de dados

  Cenário: Lidar com formato de data inválido nos dados de treinamento
    Dado que tenho dados de treinamento com formato de data inválido
      | titulo | descricao | data       | obrigatorio |
      | Test   | Test desc | invalid-date | true     |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve indicar erro de formato de data

  Cenário: Lidar com tentativas de injeção SQL em parâmetros de busca
    Dado que quero buscar programas de treinamento
    E uso injeção SQL no parâmetro de busca
      | parameter | value |
      | titulo    | '; DROP TABLE treinamento; -- |
    Quando envio uma requisição GET para "/treinamentos" com o parâmetro malicioso
    Então o status da resposta deve ser 400
    E a resposta deve indicar erro de validação de parâmetro
    E o banco de dados deve permanecer intacto

  Cenário: Lidar com tentativas de XSS em campos de texto
    Dado que tenho dados de treinamento com payload XSS
      | titulo | descricao | data       | obrigatorio |
      | <script>alert('XSS')</script> | Test desc | 2024-02-15 | true |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 200
    E a resposta deve conter dados sanitizados
    E as tags script devem estar escapadas

  Cenário: Lidar com valores nulos em campos obrigatórios
    Dado que tenho dados de treinamento com campos obrigatórios nulos
      | titulo | descricao | data       | obrigatorio |
      | null   | null      | null       | true        |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve indicar erros de validação para campos obrigatórios

  Cenário: Lidar com valores de string vazia em campos obrigatórios
    Dado que tenho dados de treinamento com valores de string vazia
      | titulo | descricao | data       | obrigatorio |
      | ""     | ""        | 2024-02-15 | true        |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve indicar erros de validação para campos vazios

  Cenário: Lidar com valores de string extremamente longos
    Dado que tenho dados de treinamento com strings extremamente longas
      | titulo | descricao | data       | obrigatorio |
      | [500 character string] | [2000 character string] | 2024-02-15 | true |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve indicar erro de validação de comprimento do campo

  Cenário: Lidar com caracteres especiais em campos de texto
    Dado que tenho dados de treinamento com caracteres especiais
      | titulo | descricao | data       | obrigatorio |
      | Test@#$%^&*() | Description with émojis 🎯 | 2024-02-15 | true |
    Quando envio uma requisição POST para "/treinamentos" com os dados
    Então o status da resposta deve ser 200
    E a resposta deve conter os dados com caracteres especiais preservados

  Cenário: Lidar com valores de ID negativos
    Dado que quero acessar um recurso com ID negativo
    Quando envio uma requisição GET para "/treinamentos/-1"
    Então o status da resposta deve ser 400
    E a resposta deve indicar formato de ID inválido

  Cenário: Lidar com valores de ID não numéricos
    Dado que quero acessar um recurso com ID não numérico
    Quando envio uma requisição GET para "/treinamentos/abc"
    Então o status da resposta deve ser 400
    E a resposta deve indicar formato de ID inválido

  Cenário: Lidar com requisição sem cabeçalhos obrigatórios
    Dado que tenho credenciais de autenticação válidas
    E não incluo o cabeçalho Authorization
    Quando envio uma requisição GET para "/colaboradores/countPorGenero"
    Então o status da resposta deve ser 401
    E a resposta deve indicar autorização ausente

  Cenário: Lidar com token JWT expirado
    Dado que tenho um token JWT expirado
    Quando envio uma requisição GET para "/colaboradores/countPorGenero" com o token expirado
    Então o status da resposta deve ser 401
    E a resposta deve indicar token expirado

  Cenário: Lidar com token JWT malformado
    Dado que tenho um token JWT malformado "malformed.token.here"
    Quando envio uma requisição GET para "/colaboradores/countPorGenero" com o token malformado
    Então o status da resposta deve ser 401
    E a resposta deve indicar token inválido

  Cenário: Lidar com requisição com método HTTP inválido
    Dado que quero acessar o endpoint de lista de treinamentos
    Quando envio uma requisição PATCH para "/treinamentos"
    Então o status da resposta deve ser 405
    E a resposta deve indicar método não permitido

  Cenário: Lidar com requisição para endpoint inexistente
    Dado que quero acessar um endpoint inexistente
    Quando envio uma requisição GET para "/non-existent-endpoint"
    Então o status da resposta deve ser 404
    E a resposta deve indicar endpoint não encontrado

  Cenário: Lidar com requisição com parâmetros de consulta inválidos
    Dado que quero acessar a lista de treinamentos com parâmetros inválidos
    Quando envio uma requisição GET para "/treinamentos?invalidParam=value&anotherInvalid=test"
    Então o status da resposta deve ser 400
    E a resposta deve indicar parâmetros inválidos

  Cenário: Lidar com sistema sob alta carga
    Dado que o sistema está sob alta carga
    E existem 1000 requisições concorrentes
    Quando envio uma requisição GET para "/treinamentos"
    Então a resposta deve ser processada dentro de limites de tempo aceitáveis
    E o status da resposta deve ser 200 ou 503
