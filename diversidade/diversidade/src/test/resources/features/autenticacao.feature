# language: pt
Funcionalidade: Gerenciamento de Autenticação
Como administrador do sistema
Eu quero autenticar usuários
Para que apenas usuários autorizados possam acessar o sistema

Contexto:
Dado que a aplicação está rodando
E o banco de dados está acessível

Cenário: Login bem-sucedido com credenciais válidas
Dado que tenho credenciais de usuário válidas
| username | password |
| admin    | admin123 |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 200
E a resposta deve conter um token JWT válido
E o token deve ser uma string não vazia

Cenário: Falha no login com nome de usuário inválido
Dado que tenho credenciais de usuário inválidas
| username | password |
| invalid  | admin123 |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 500
E a resposta deve conter "Usuário ou senha inválidos"

Cenário: Falha no login com senha inválida
Dado que tenho credenciais de usuário inválidas
| username | password |
| admin    | wrongpass |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 500
E a resposta deve conter "Usuário ou senha inválidos"

Cenário: Falha no login com credenciais vazias
Dado que tenho credenciais de usuário vazias
| username | password |
|          |         |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 500
E a resposta deve conter "Usuário ou senha inválidos"

Cenário: Falha no login com nome de usuário ausente
Dado que tenho credenciais com nome de usuário ausente
| username | password |
|          | admin123 |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 500
E a resposta deve conter "Usuário ou senha inválidos"

Cenário: Falha no login com senha ausente
Dado que tenho credenciais com senha ausente
| username | password |
| admin    |         |
Quando envio uma requisição POST para "/auth/login" com as credenciais
Então o status da resposta deve ser 500
E a resposta deve conter "Usuário ou senha inválidos"
