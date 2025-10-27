# language: pt
Funcionalidade: Gerenciamento de Colaboradores
Como administrador do sistema
Eu quero gerenciar informações de funcionários e métricas de diversidade
Para que eu possa acompanhar iniciativas de diversidade e inclusão

Contexto:
Dado que a aplicação está rodando
E o banco de dados está acessível
E tenho um token de autenticação válido

Cenário: Contar funcionários por gênero com sucesso
Dado que existem funcionários no sistema com diferentes gêneros
| nome     | genero |
| João     | Masculino |
| Maria    | Feminino |
| Ana      | Feminino |
| Pedro    | Masculino |
Quando envio uma requisição GET para "/colaboradores/countPorGenero"
Então o status da resposta deve ser 200
E a resposta deve conter contagens por gênero
| genero    | count |
| Masculino | 2     |
| Feminino  | 2     |

Cenário: Deletar um funcionário existente com sucesso
Dado que existe um funcionário com ID 1 no sistema
Quando envio uma requisição DELETE para "/colaboradores/1"
Então o status da resposta deve ser 200
E a resposta deve conter "Deletado com sucesso"

Cenário: Falha na deleção de funcionário inexistente
Dado que não existe um funcionário com ID 999 no sistema
Quando envio uma requisição DELETE para "/colaboradores/999"
Então o status da resposta deve ser 200
E a resposta deve conter "Deletado com sucesso"

Cenário: Contar funcionários por gênero quando não existem funcionários
Dado que não existem funcionários no sistema
Quando envio uma requisição GET para "/colaboradores/countPorGenero"
Então o status da resposta deve ser 200
E a resposta deve ser um objeto vazio

Cenário: Contar funcionários por gênero com valores nulos
Dado que existem funcionários com valores de gênero nulos
| nome     | genero |
| João     | null   |
| Maria    | Feminino |
| Ana      | null   |
Quando envio uma requisição GET para "/colaboradores/countPorGenero"
Então o status da resposta deve ser 200
E a resposta deve lidar com valores de gênero nulos apropriadamente

Cenário: Acessar endpoints de colaborador sem autenticação
Dado que não tenho um token de autenticação válido
Quando envio uma requisição GET para "/colaboradores/countPorGenero"
Então o status da resposta deve ser 401
E a resposta deve indicar acesso não autorizado

Cenário: Acessar endpoints de colaborador com token de autenticação inválido
Dado que tenho um token de autenticação inválido "invalid-token"
Quando envio uma requisição GET para "/colaboradores/countPorGenero"
Então o status da resposta deve ser 401
E a resposta deve indicar acesso não autorizado
