# SceneManager

API para gerenciamento do estado das cenas salvas em banco.

## Introdução

Estas instruções possibilitarão ao avaliador executar uma cópia deste projeto 
localmente em seu computador.

## Índice
* [Pré-requisitos](#pre-requisitos)
* [Instalação](#instalacao)
* [Containerização](#containerização-(docker))
* [Documentação dos endpoints (Swagger)](#documentação-dos-endpoints-(swagger))
* [Teste dos endpoints utilizando CURL](#teste-dos-endpoints-utilizando-curl)
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
* [CURL](https://curl.haxx.se/) - Comunicação HTTP através de linha de comando.

## Instalação
*(Em construção)*

## Containerização (Docker)

Navegue para o diretório *docker*:

```
cd docker
```

Execute o *build* da imagem:

```
docker build -t java-api .
```

Confirme se a imagem está local:

```
docker images
```

Execute o contêiner:

```
docker run -d -p 8080:8080 --name javaapi java-api
```

Confirme a execução do contêiner:

```
docker ps
```

O endereço GET principal da api estará disponível no URL indicado abaixo. Use-o para testar:
```
http://localhost:8080/api/scenes
```

## Teste dos endpoints utilizando CURL
*(Em construção)*

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
*(Em construção)*
```
mvn test
```

## Autor

* **Roger Freret** - [RogerVFbr](https://github.com/RogerVFbr)

