API Desafio PicPay

    API RESTful desenvolvida como parte do desafio técnico do PicPay, focada em transações financeiras entre carteiras digitais.

📑 Descrição

Esta API simula um sistema de carteiras digitais e transações entre usuários, permitindo:

    Cadastro e login de usuários que estejam testando a aplicação (Spring Security-oAuth2-JWT)
    Gerenciamento de carteiras digitais (criar, listar, atualizar, deletar).
    Operações (atômicas) de transações entre carteiras.
    Boas práticas de arquitetura, como SOLID, e documentação com OpenAPI.    

A API segue a arquitetura REST e foi construída usando Spring Boot com persistência de dados em PostgreSQL.
🛠 Tecnologias Utilizadas

    Java 17
    Spring Boot 2.7.0
        Spring Security
        Spring Data JDBC
        Spring OpenAPI (Swagger)
    PostgreSQL 13
    JWT para autenticação
    Docker (opcional)

🚀 Endpoints

*** Nos terminais(CLI) talvez seja necessário incluir um argumento --verify=no, para passar do certificado SSL.
*** No POSTMAN, INSOMNIA e outros, não é necessário fazer nada.

Autenticação

    Cadastrar Usuário:
        POST /signup

    json

{
  "username": "your_username",
  "password": "your_password"
}

Fazer Login:

    POST /authenticate

json

    {
      "username": "your_username",
      "password": "your_password"
    }

Carteiras (Wallets)

    Criar Carteira:
        POST /wallets

    json

    {
      "fullName": "João Silva",
      "cpf": "12345678901",
      "email": "joao@example.com",
      "password": "senha123",
      "type": 1,
      "balance": 1000.00
    }

    Listar Carteiras:
        GET /wallets

    Atualizar Carteira:
        PUT /wallets/{id}

    Deletar Carteira:
        DELETE /wallets/{id}

    Deletar Todas as Carteiras:
        DELETE /wallets/all

Transações (Transactions)

    Criar Transação:
        POST /transactions

    json

    {
      "payer": 1,
      "payee": 2,
      "value": 200.00
    }

    Listar Transações:
        GET /transactions

⚙️ Pré-requisitos

    Java 17 ou superior
    Maven 3.6+ ou Gradle
    PostgreSQL 13 ou superior

Como Rodar o Projeto Localmente

    Clonar o repositório:

    bash

git clone https://github.com/eulerv/api-desafio-picpay.git

Configurar o banco de dados:

    Certifique-se de que o PostgreSQL esteja rodando e crie um banco de dados para a aplicação.
    Ajuste as configurações no arquivo application.properties(pode usar o application.properties.example para ter noção):

properties

spring.datasource.url=jdbc:postgresql://localhost:5432/seu_bd
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

Acessar a documentação Swagger:

    Acesse a documentação gerada automaticamente em:

    http://localhost:8080/swagger-ui/index.html

🐳 Rodando com Docker

    Construir a imagem Docker:

    bash

docker build -t api-desafio-picpay .

Rodar o container:

bash

    docker run -p 8080:8080 api-desafio-picpay

🛡 Autenticação com JWT

Para acessar os endpoints é necessário:

    Cadastrar-se em /signup.

    Fazer login em /authenticate para obter o token JWT.

    Enviar o token nas requisições autenticadas como Bearer Token no header:

    bash

    Authorization: Bearer <seu_token>

📄 Licença

Este projeto está licenciado sob a MIT License.
🧑‍💻 Autor eulerv

Desenvolvido por J. Euler Vicente - LinkedIn | GitHub
🌐 Links Importantes

    Swagger UI rodando no Render - https://api-desafio-picpay.onrender.com/swagger-ui/index.html
    Deploy no Render - https://api-desafio-picpay.onrender.com/
    Portfólio - https://portfolio-v2-zeta-lime.vercel.app/apiPicpay

📈 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou enviar um pull request.
