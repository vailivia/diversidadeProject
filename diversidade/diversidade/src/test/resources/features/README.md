# Cenários de Teste Gherkin para o Projeto Diversidade

Este diretório contém cenários de teste Gherkin abrangentes para a aplicação de gerenciamento de diversidade e inclusão Diversidade. Os cenários cobrem tanto casos de teste positivos (caminho feliz) quanto negativos (tratamento de erros) para todas as funcionalidades principais.

## Estrutura dos Testes

Os cenários de teste estão organizados nos seguintes arquivos de funcionalidade:

### 1. Gerenciamento de Autenticação (`authentication.feature`)
Testa o sistema de autenticação baseado em JWT incluindo:
- **Cenários positivos**: Login bem-sucedido com credenciais válidas
- **Cenários negativos**: Falha no login com credenciais inválidas, campos vazios, dados ausentes

**Casos de Teste Principais:**
- Login de usuário válido retorna token JWT
- Nome de usuário/senha inválidos retornam mensagem de erro
- Credenciais vazias são tratadas graciosamente
- Nome de usuário ou senha ausentes acionam erro apropriado

### 2. Gerenciamento de Colaboradores (`colaborador-management.feature`)
Testa o gerenciamento de funcionários e métricas de diversidade incluindo:
- **Cenários positivos**: Contagem por gênero, deleção de funcionários
- **Cenários negativos**: Operações com funcionários inexistentes, falhas de autenticação

**Casos de Teste Principais:**
- Contar funcionários por gênero retorna estatísticas precisas
- Deletar funcionário existente é bem-sucedido
- Tratar deleção de funcionário inexistente graciosamente
- Autenticação necessária para todas as operações

### 3. Gerenciamento de Treinamentos (`treinamento-management.feature`)
Testa o gerenciamento de programas de treinamento incluindo:
- **Cenários positivos**: Operações CRUD, associação de funcionários
- **Cenários negativos**: Dados inválidos, recursos inexistentes, erros de validação

**Casos de Teste Principais:**
- Criar programas de treinamento com dados válidos
- Associar funcionários com programas de treinamento
- Atualizar e deletar programas de treinamento existentes
- Tratar dados inválidos e recursos inexistentes

### 4. Gerenciamento de Participações (`participacao-management.feature`)
Testa o acompanhamento de participação em treinamentos incluindo:
- **Cenários positivos**: Criar registros de participação, acompanhar conclusão
- **Cenários negativos**: Associações inválidas, dados ausentes

**Casos de Teste Principais:**
- Criar e listar registros de participação
- Acompanhar participação por funcionário
- Marcar participação como concluída
- Tratar associações inválidas funcionário/treinamento

### 5. Tratamento de Erros e Casos Extremos (`error-handling-and-edge-cases.feature`)
Teste abrangente de tratamento de erros e casos extremos incluindo:
- **Cenários de segurança**: Prevenção de injeção SQL, XSS
- **Validação de dados**: Formatos inválidos, condições de limite
- **Resiliência do sistema**: Alta carga, timeouts, acesso concorrente

**Casos de Teste Principais:**
- Tratamento de JSON malformado
- Prevenção de injeção SQL
- Prevenção de ataques XSS
- Validação e sanitização de entrada
- Tratamento de requisições concorrentes
- Tratamento de timeout do banco de dados

## Cobertura de Testes

### Cenários Positivos (Caminho Feliz)
- ✅ Autenticação bem-sucedida
- ✅ Operações CRUD completas para todas as entidades
- ✅ Validação de dados e lógica de negócio
- ✅ Gerenciamento de associações entre entidades
- ✅ Cálculos estatísticos e relatórios

### Cenários Negativos (Tratamento de Erros)
- ✅ Falhas de autenticação e acesso não autorizado
- ✅ Entrada de dados inválidos e erros de validação
- ✅ Operações com recursos inexistentes
- ✅ Prevenção de vulnerabilidades de segurança
- ✅ Resiliência do sistema e recuperação de erros

## Instruções de Uso

### Pré-requisitos
- Java 21
- Spring Boot 3.2.6
- Maven
- Framework de teste que suporte Gherkin (Cucumber, JBehave, etc.)

### Executando os Testes
```bash
# Executar todos os cenários Gherkin
mvn test -Dtest=*CucumberTest

# Executar funcionalidade específica
mvn test -Dtest=*CucumberTest -Dcucumber.options="--tags @authentication"

# Executar com tags específicas
mvn test -Dtest=*CucumberTest -Dcucumber.options="--tags @positive"
```

### Configuração de Dados de Teste
Cada cenário inclui a configuração necessária de dados de teste nas etapas `Dado`. Os cenários são projetados para serem autocontidos e podem ser executados independentemente.

### Configuração do Ambiente
- **Banco de Dados de Teste**: Banco H2 em memória (configurado em `application-test.properties`)
- **Autenticação**: Tokens JWT simulados para teste
- **URL Base da API**: `http://localhost:8080` (configurável)

## Exemplos de Cenários

### Sucesso na Autenticação
```gherkin
Cenário: Login bem-sucedido com credenciais válidas
  Dado que tenho credenciais de usuário válidas
    | username | password |
    | admin    | admin123 |
  Quando envio uma requisição POST para "/auth/login" com as credenciais
  Então o status da resposta deve ser 200
  E a resposta deve conter um token JWT válido
```

### Validação de Dados
```gherkin
Cenário: Criar programa de treinamento com dados inválidos
  Dado que quero criar um programa de treinamento com dados inválidos
    | titulo | descricao | data       | obrigatorio |
    |        |           | 2024-02-15 | true        |
  Quando envio uma requisição POST para "/treinamentos" com os dados inválidos
  Então o status da resposta deve ser 400
  E a resposta deve indicar erros de validação
```

### Teste de Segurança
```gherkin
Cenário: Lidar com tentativas de injeção SQL em parâmetros de busca
  Dado que quero buscar programas de treinamento
  E uso injeção SQL no parâmetro de busca
    | parameter | value |
    | titulo    | '; DROP TABLE treinamento; -- |
  Quando envio uma requisição GET para "/treinamentos" com o parâmetro malicioso
  Então o status da resposta deve ser 400
  E o banco de dados deve permanecer intacto
```

## Melhores Práticas

1. **Isolamento de Dados**: Cada cenário usa dados de teste independentes
2. **Limpeza**: Os cenários se limpam após a execução
3. **Dados Realistas**: Os dados de teste refletem padrões de uso do mundo real
4. **Cobertura Abrangente**: Cenários positivos e negativos incluídos
5. **Foco em Segurança**: Vulnerabilidades de segurança são explicitamente testadas
6. **Considerações de Performance**: Cenários de alta carga e acesso concorrente incluídos

## Manutenção

- Atualizar cenários quando novas funcionalidades são adicionadas
- Revisar e atualizar dados de teste conforme as regras de negócio mudam
- Adicionar novos casos extremos conforme são descobertos
- Manter cenários de segurança atualizados com ameaças atuais

## Integração com CI/CD

Estes cenários Gherkin são projetados para se integrar com o pipeline CI/CD existente:
- Executar automaticamente em commits de código
- Fornecer relatórios detalhados sobre resultados de teste
- Suportar execução paralela para feedback mais rápido
- Gerar relatórios de teste para stakeholders
