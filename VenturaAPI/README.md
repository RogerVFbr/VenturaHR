# VenturaHR

API para gerenciamento do backend do aplicativo VenturaHR.

## Introdução

Estas instruções possibilitarão ao desenvolvedor executar uma cópia deste projeto 
localmente em seu computador.

## Índice
* [Pré-requisitos](#pre-requisitos)
* [Instalação](#instalacao)
* [Containerização](#containerização-(docker))
* [Endpoints](#endpoints)
* [Deploy (AWS, IaC, Serverless)](#Deploy)
* [Banco de dados (H2 Database)](#banco-de-dados-(h2-database))
* [Testes automatizados](#testes-automatizados)

## Pré-requisitos

Tecnologias que devem estar instaladas e/ou disponíveis:
* [Java JDK 1.8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Versão Java utilizada.
* [Maven 3](https://maven.apache.org/) - Gerenciamento de dependências e build.
* [Spring Boot 2.3](https://spring.io/projects/spring-boot) - Framework Java para a criação dos endpoints.
* [Lombok Plugin](https://projectlombok.org/) - Plugin para redução de verbosidade nos modelos.
* [Docker](https://www.docker.com/) - Containerização.
* [IntelliJ IDEA](https://www.jetbrains.com/) - Ou outra IDE da sua escolha.
* [Git](https://git-scm.com/) - Sistema de versionamento.
* [Serverless Framework](https://www.serverless.com/) - Infraestrutura como código, arquitetura serverless.

## Instalação
Todos os passos a seguir deverão ser executados na prompt de comando do sistema operacional.

Faça uma cópia local deste repositório:

```
git clone https://github.com/RogerVFbr/VenturaHR.git
```

Navege até a pasta do projeto e utilize o **Maven** para fazer o *build*:
```
mvn clean install
```

Ainda na pasta do projeto, execute o *jar* gerado:
```
java -jar docker/dockerizedjavaapi-0.0.1-SNAPSHOT.jar
```

O endereço GET da api estará disponível no URL indicado abaixo. Use-o para testar:
```
http://localhost:8080/api/users/candidatos
```

Pressione CTRL-C (Win) ou COMMAND-C (Mac) no terminal para encerrar a execução.

## Containerização (Docker)

Navegue para o diretório *docker*:

```
cd docker
```

Execute o *build* da imagem:

```
docker build -t venturahr-api .
```

Confirme se a imagem está local:

```
docker images
```

Execute o contêiner:

```
docker run -d -p 8080:8080 --name venturahr-api venturahr-api
```

Confirme a execução do contêiner:

```
docker ps
```

O endereço GET principal da api estará disponível no URL indicado abaixo. Use-o para testar:
```
http://localhost:8080/api/users/admins
http://localhost:8080/api/users/candidatos
http://localhost:8080/api/users/empresas
http://localhost:8080/api/vagas
```

## Endpoints
Documentação UI (Swagger/OpenApi)
```
http://localhost:8080/api/swagger-ui.html
```
Definição OpenApi (JSON)
```
http://localhost:8080/api/api-docs
```
Utilize a interface do Swagger para realizar testes de integração.

## Deploy
1. Instale o *Serverless Framework* caso ainda não tenha feito.
    ```
    npm install -g serverless
    ```
2. Adquira credenciais AWS e registre-as localmente.
3. Instale as dependências *Node* rodando em um terminal na raíz do projeto:
    ```
    npm i
    ```
4. Execute o deploy a partir da pasta raíz do projeto.
   ```
   npm deploy
   ```


## Banco de dados (H2 Database)
O banco de dados SQL [H2](https://www.h2database.com/) foi utilizado neste projeto especialmente
por rodar diretamente na memória do computador sem a necessidade de instalação. Desta forma,
os componentes de repositório podem ser desenvolvidos utilizando sua forma final (JPA/Hibernate),
podendo ser facilmente adaptados a bancos de dados SQL reais. Sua interface de edição pode
ser encontrada no URL indicado abaixo enquanto a API estiver rodando. Para acessá-lo, utilize
o *User Name* **sa** e o *Password* **password**.
```
http://localhost:8080/api/h2-console
```
![H2 Database Screenshot](img/h2_screenshot.png?raw=true)

## Testes automatizados
####Testes unitários:
```
mvn test
```

#### Testes de integração
Utilize a interface do Swagger para realizar testes de integração customizados.
```
http://localhost:8080/api/swagger-ui.html
```

#### Smoke tests (somente IntelliJ):
Utilizar testes contidos na pasta *request_patterns*. Caso for testar remoto, atualize os endereços
em *http-client.env.json*.


## Autor

* **Roger Freret** - [RogerVFbr](https://github.com/RogerVFbr)

