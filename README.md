# Título do projeto

VR-BENEFICIOS

## 🚀 Começando

Este é um projeto desafio para vaga de desenvolvedor Java

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html), [Maven](https://maven.apache.org/),[Docker](https://www.docker.com/products/docker-desktop/),
Além disto é necessario ter um editor para avaliar o código como [VSCode](https://code.visualstudio.com/)

### 🎲 Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/RafaelLeiteDeveloper/vr-beneficios.git>

# Acesse a pasta do projeto no terminal/cmd
$ cd vr-beneficios

# Compilar o codigo
$ mvn clean install

# Iniciar o banco de dados
$ docker-compose up -d

# Execute a aplicação em modo de desenvolvimento
$ mvn spring-boot:run

# O servidor inciará na porta:8080 - acesse <http://localhost:8080>
```
## ⚙️ Executando os testes

Explicar como executar os testes automatizados para este sistema.

### 🔩 Documentação

Acessar a documentação

```
http://localhost:8080/swagger-ui/index.html#/
```

### ⌨️ E testes de estilo de codificação

Executar testes

```
# Compilar o codigo
$ mvn clean install

```

## 🛠️ Construído com

Mencione as ferramentas que você usou para criar seu projeto

* [Spring Boot](https://spring.io/projects/spring-boot) - O framework web usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Junit](https://junit.org/junit5/) - Usada para teste
* [Mockito](https://site.mockito.org/) - Usada para teste
* [Mockito](https://flywaydb.org) - flywaydb para versionamento do banco de dados

## ✒️ Autores


* **Rafael Leite** - *Desenvolvedor* - [Rafael Leite](https://www.linkedin.com/in/developerleite/)

