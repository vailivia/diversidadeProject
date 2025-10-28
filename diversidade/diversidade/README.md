# Projeto - Diversidade

Microsserviço Spring Boot para gestão de iniciativas de diversidade e inclusão corporativa, incluindo programas de treinamento e acompanhamento de métricas.

## Como executar localmente com Docker

### Pré-requisitos
- Docker e Docker Compose instalados
- Portas 8080 e 1521 disponíveis

### Passos para execução

1. **Clone o repositório:**
```bash
git clone https://github.com/vailivia/diversidadeProject.git
cd diversidade/diversidade
```

2. **Execute com Docker Compose:**
```bash
docker-compose up -d
```

3. **Acesse a aplicação:**
- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Banco Oracle: `localhost:1521`

### Estrutura dos containers
- **app**: Aplicação Spring Boot (porta 8080)
- **oracle**: Banco de dados Oracle XE (porta 1521)
- **Volumes**: Persistência de dados do Oracle
- **Rede**: Comunicação entre containers

## Pipeline CI/CD

### Ferramentas utilizadas
- **GitHub Actions**: Automação de CI/CD
- **Azure Web Apps**: Plataforma de deploy
- **Maven**: Build e gerenciamento de dependências
- **Docker**: Containerização da aplicação

### Etapas do pipeline

#### 1. Build e Testes
- **Trigger**: Push para branches `main`, `develop` ou `feature/workflow`
- **Ambiente**: Ubuntu Latest
- **Java**: Versão 21 (Eclipse Temurin)
- **Etapas**:
  - Checkout do código
  - Setup do JDK 21
  - Build com Maven (`mvn clean package -DskipTests`)
  - Execução de testes (`mvn test`)
  - Upload de artefatos de build

#### 2. Deploy Staging
- **Trigger**: Push para branch `develop` ou `feature/workflow`
- **Dependência**: Job de build deve ser bem-sucedido
- **Deploy**: Azure Web App (diversidadeProject-staging)
- **Artefatos**: Download do JAR gerado no build

#### 3. Deploy Produção
- **Trigger**: Push para branch `feature/workflow`
- **Dependência**: Job de build deve ser bem-sucedido
- **Deploy**: Azure Web App (diversidadeProject)
- **Artefatos**: Download do JAR gerado no build

### Configuração de Secrets
- `AZURE_WEBAPP_PUBLISH_PROFILE_STAGING`: Perfil de publicação do Azure para staging
- `AZURE_WEBAPP_PUBLISH_PROFILE_PROD`: Perfil de publicação do Azure para produção

## Containerização

### Dockerfile

```dockerfile
# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY diversidade/diversidade/pom.xml .
COPY diversidade/diversidade/src ./src
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user
RUN useradd -m -u 1001 appuser
USER appuser

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Estratégias adotadas

#### Multi-stage Build
- **Build Stage**: Utiliza JDK 21 para compilação e empacotamento
- **Run Stage**: Utiliza JRE 21 para execução, reduzindo tamanho da imagem final
- **Separação de responsabilidades**: Build e runtime em containers diferentes

#### Segurança
- **Usuário não-root**: Criação de usuário específico (`appuser`) para execução
- **UID fixo**: UID 1001 para consistência entre ambientes
- **Princípio do menor privilégio**: Aplicação executa sem privilégios administrativos

#### Otimizações
- **Imagem base**: Eclipse Temurin (distribuição otimizada do OpenJDK)
- **Camadas otimizadas**: Separação entre dependências e código da aplicação
- **Porta exposta**: 9090 (configurada no application.properties)

### Docker Compose

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
      - SPRING_DATASOURCE_USERNAME=rm558929
      - SPRING_DATASOURCE_PASSWORD=030306
    depends_on:
      oracle:
        condition: service_healthy
    networks:
      - diversidade-network
    restart: on-failure

  oracle:
    image: gvenzl/oracle-xe:21
    ports:
      - "1521:1521"
    environment:
      - ORACLE_PASSWORD=030306
      - ORACLE_CHARACTERSET=AL32UTF8
      - ORACLE_NLS_CHARACTERSET=AL32UTF8
      - ORACLE_NLS_NCHAR_CHARACTERSET=AL16UTF16
      - ORACLE_ALLOW_REMOTE=true
      - ORACLE_DISABLE_ASYNCH_IO=true
    volumes:
      - oracle-data:/opt/oracle/oradata
      - ./init-scripts:/docker-entrypoint-initdb.d/startup
    networks:
      - diversidade-network
    healthcheck:
      test: ["CMD", "sqlplus", "-L", "system/030306@//localhost:1521/XE", "AS", "SYSDBA"]
      interval: 30s
      timeout: 10s
      retries: 10
      start_period: 120s
    restart: on-failure
    shm_size: '6gb'
```



## Tecnologias utilizadas

### Backend
- **Java 21**: Linguagem de programação
- **Spring Boot 3.2.6**: Framework principal
- **Spring Data JPA**: Persistência de dados
- **Spring Security**: Autenticação e autorização
- **Spring Validation**: Validação de dados

### Banco de Dados
- **Oracle Database XE 21**: Banco de dados principal
- **H2 Database**: Banco de dados para testes
- **Flyway**: Migração de banco de dados
- **HikariCP**: Pool de conexões

### Segurança
- **JWT (JSON Web Token)**: Autenticação stateless
- **JJWT 0.11.5**: Biblioteca para manipulação de JWT
- **BCrypt**: Criptografia de senhas

### Documentação e API
- **SpringDoc OpenAPI 2.3.0**: Documentação automática da API
- **Swagger UI**: Interface de documentação interativa
- **RESTful API**: Arquitetura de API

### Containerização e Deploy
- **Docker**: Containerização da aplicação
- **Docker Compose**: Orquestração de containers
- **Eclipse Temurin**: Imagem base Java
- **Azure Web Apps**: Plataforma de deploy

### CI/CD
- **GitHub Actions**: Automação de CI/CD
- **Maven**: Build e gerenciamento de dependências
- **Azure DevOps**: Deploy automatizado

### Desenvolvimento
- **Lombok**: Redução de boilerplate
- **Spring Boot DevTools**: Ferramentas de desenvolvimento
- **Maven Compiler Plugin**: Compilação Java

### Testes
- **JUnit 5**: Framework de testes
- **Spring Boot Test**: Testes de integração
- **H2 Database**: Banco de dados para testes

A
### Endpoints Principais

#### Treinamentos
- `GET /api/trainings` - Listar todos os treinamentos
- `GET /api/trainings/{id}` - Buscar treinamento por ID
- `POST /api/trainings` - Criar novo treinamento
- `PUT /api/trainings/{id}` - Atualizar treinamento
- `DELETE /api/trainings/{id}` - Deletar treinamento

#### Métricas
- `GET /api/metrics` - Listar todas as métricas
- `GET /api/metrics/{id}` - Buscar métrica por ID
- `POST /api/metrics` - Criar nova métrica
- `PUT /api/metrics/{id}` - Atualizar métrica
- `DELETE /api/metrics/{id}` - Deletar métrica

#### Colaboradores
- `GET /api/colaboradores` - Listar colaboradores
- `POST /api/colaboradores` - Criar colaborador
- `PUT /api/colaboradores/{id}` - Atualizar colaborador

#### Autenticação
- `POST /api/auth/login` - Login de usuário
- `POST /api/auth/register` - Registro de usuário

## Desenvolvimento

### Executar Testes
```bash
./mvnw test
```

### Migrações de Banco
O projeto utiliza Flyway para migrações de banco de dados. Os scripts estão localizados em `src/main/resources/db/migration/`.

### Build Local
```bash
./mvnw clean package
```

## Contribuição

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes. 