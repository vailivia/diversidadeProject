# Corporate Inclusion and Diversity Backend

Este é o backend do sistema de Gestão de Inclusão e Diversidade Corporativa, desenvolvido com Spring Boot 3.2.3 e Java 21.

## Requisitos

- Java 21
- Maven 3.9+
- Docker e Docker Compose (para execução em container)
- Oracle Database (para ambiente de produção)

## Configuração do Ambiente

### Variáveis de Ambiente

O projeto utiliza as seguintes variáveis de ambiente:

```properties
# Database
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@localhost:1521:XE
SPRING_DATASOURCE_USERNAME=diversidade
SPRING_DATASOURCE_PASSWORD=diversidade123

# Security
BASIC_AUTH_USERNAME=admin
BASIC_AUTH_PASSWORD=admin123
```

### Executando com Docker

1. Construa e inicie os containers:
```bash
docker-compose up -d
```

2. A aplicação estará disponível em: http://localhost:9090

3. Documentação Swagger: http://localhost:9090/swagger-ui.html

### Executando Localmente

1. Clone o repositório
2. Configure o Oracle Database
3. Execute o projeto:
```bash
./mvnw spring-boot:run
```

## Endpoints da API

### Colaboradores

- `GET /colaboradores` - Lista todos os colaboradores
- `POST /colaboradores` - Cria um novo colaborador
- `PUT /colaboradores/{id}` - Atualiza um colaborador existente
- `DELETE /colaboradores/{id}` - Remove um colaborador

### Estatísticas de Diversidade

- `GET /colaboradores/estatisticas` - Retorna estatísticas de diversidade
- `GET /colaboradores/treinamento` - Lista colaboradores que completaram o treinamento

## Segurança

A API utiliza autenticação básica. Use as seguintes credenciais:

- Usuário: admin
- Senha: admin123

## Migrações do Banco de Dados

O projeto utiliza Flyway para gerenciar as migrações do banco de dados. As migrações estão localizadas em `src/main/resources/db/migration`.

## Documentação

A documentação completa da API está disponível através do Swagger UI em:
http://localhost:9090/swagger-ui.html

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Crie um Pull Request 