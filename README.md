# Forum-Hub API

Descrição do Projeto
O Forum-Hub é uma API RESTful desenvolvida em Java com Spring Boot, projetada para ser o backend de um sistema de fórum de discussão. Ela gerencia tópicos, usuários, cursos e fornece um sistema robusto de autenticação e autorização via JWT (JSON Web Tokens).

Este projeto é focado em boas práticas de desenvolvimento de API, incluindo validação de dados, segurança, persistência em banco de dados e padronização de respostas.

# Funcionalidades
Autenticação de Usuário: Login seguro e geração de token JWT.

# Gestão de Tópicos:

Criação de novos tópicos (com título, mensagem, autor e curso).

Listagem de todos os tópicos ou por critérios.

Visualização de um tópico específico.

Atualização de tópicos existentes.

Exclusão lógica de tópicos.

Validações: Regras de negócio para garantir que tópicos duplicados (mesmo título e mensagem) não sejam criados.

# Tecnologias Utilizadas
O projeto foi desenvolvido majoritariamente com as seguintes tecnologias:

Linguagem: Java 17+

Framework: Spring Boot 3

Banco de Dados: (Assumir que usa um SGBD Relacional)

Possível: PostgreSQL, MySQL ou H2 (para desenvolvimento).

Persistência: Spring Data JPA

Segurança: Spring Security + JWT

Ferramenta de Build: Maven (ou Gradle)

Documentação: (Sugestão: Adicionar Swagger/OpenAPI, se implementado)

# Pré-requisitos
Para rodar este projeto localmente, você precisará ter instalado:

Java Development Kit (JDK) (Versão 17 ou superior)

Uma ferramenta de build (Maven ou Gradle)

Um sistema de gerenciamento de banco de dados (Ex: PostgreSQL)

# Instalação e Configuração
Siga os passos abaixo para configurar e executar o projeto:

1. Clonar o Repositório
Bash

git clone https://github.com/HugoLMonteiro/Forum-Hub.git
cd Forum-Hub
2. Configuração do Banco de Dados
Crie um banco de dados no seu SGBD (exemplo: forumhub_db).

Edite o arquivo de propriedades da aplicação (geralmente src/main/resources/application.properties ou application.yml) para configurar o acesso ao seu banco de dados.

Exemplo para application.properties (PostgreSQL):

Properties

# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do JPA (para criação automática de tabelas)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Executar a Aplicação
Usando Maven
Bash

# Compilar o projeto
./mvnw clean package

# Executar a aplicação
java -jar target/forumhub-0.0.1-SNAPSHOT.jar 
# (O nome do arquivo .jar pode variar)
Usando sua IDE (IntelliJ, Eclipse)
Importe o projeto como um projeto Maven.

Execute a classe principal da aplicação (aquela com @SpringBootApplication).

A aplicação estará acessível em http://localhost:8080 (porta padrão do Spring Boot).

# Endpoints da API
A API expõe os seguintes endpoints principais (a estrutura exata pode variar):

Método	Endpoint	Descrição
POST	/login	Autentica o usuário e retorna o token JWT.
POST	/topicos	Cria um novo tópico (requer token JWT).
GET	/topicos	Lista todos os tópicos (pode aceitar paginação e filtros).
GET	/topicos/{id}	Busca um tópico pelo ID.
PUT	/topicos/{id}	Atualiza um tópico existente (requer token JWT).
DELETE	/topicos/{id}	Exclui um tópico (exclusão lógica, requer token JWT).

# Contribuindo

Contribuições são bem-vindas! 
Se você tiver alguma sugestão ou encontrar um bug, sinta-se à vontade para abrir uma issue ou enviar um pull request.

README criado com base na estrutura e linguagem predominante do repositório (Java).
