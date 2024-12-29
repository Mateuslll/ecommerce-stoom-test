# **Ecommerce API**

Este projeto é uma API para gerenciamento de produtos, marcas e categorias de um sistema de ecommerce. A API foi desenvolvida com **Spring Boot**, **Java 17**, e **MongoDB** como banco de dados. Abaixo estão as instruções de configuração e execução do projeto.

## **Tecnologias Utilizadas**

- **Java 17**: A versão do Java utilizada para desenvolvimento da aplicação.
- **Spring Boot**: Framework para desenvolvimento da aplicação back-end.
- **Spring Data MongoDB**: Para integração com o banco de dados **MongoDB**.
- **Lombok**: Biblioteca para reduzir o código boilerplate, como getters, setters, toString, etc.
- **Docker**: Utilizado para rodar o banco de dados MongoDB em contêineres.

## **Configuração do Projeto**

## **Passos para Executar o Projeto**

### 1. **Executar o MongoDB no Docker**

Para rodar o MongoDB, execute o seguinte comando na local do projeto, onde o `docker-compose.yml` está configurado:

````

docker-compose up

````

Este comando irá iniciar o MongoDB com a configuração definida no arquivo docker-compose.yml, incluindo as credenciais de acesso. Após isso, o MongoDB estará rodando na porta 27018 localmente.

### 2. **Rodar o Projeto Spring Boot**

Após garantir que o MongoDB está rodando, execute o seguinte comando para rodar o projeto Spring Boot:


````

./gradlew bootRun

````

A aplicação será iniciada e poderá ser acessada em http://localhost:8080.

### 3. **Testando API**


Após iniciar a aplicação, você pode testar os endpoints através do postman.

A collection do Postman com os endpoints da API está disponível no arquivo `Stoom API.postman_collection.json` na raiz do projeto.

### 3. **Mongo Compass**

Passos para Importar uma Collection Usando o MongoDB Compass

Baixe e instale o MongoDB Compass: Se você ainda não tem o MongoDB Compass instalado, pode baixar aqui. 'https://www.mongodb.com/try/download/compass'

Conecte-se ao MongoDB:

Abra o MongoDB Compass.

Conecte-se à instância do MongoDB utilizando a URI de conexão definida no application.properties. O padrão será:


````

mongodb://stoomTest:admin123@localhost:27018/stoomTest?authSource=admin

````

Selecionar o Banco de Dados:

Após conectar-se, selecione o banco de dados onde deseja importar a collection. No seu caso, o banco de dados é stoomTest.

Importar os Dados:

Vá até a collection desejada (por exemplo, category ou brand).

Clique em Add Data e depois em Import File.

Escolha o arquivo JSON ou CSV que contém os dados para a collection, os mesmo se encontrar na raiz do projeto.
- `stoomTest.brand.json`
- `stoomTest.category.json`
- `stoomTest.product.json`

Selecione o tipo de arquivo correto (JSON ou CSV).

Clique em Import para adicionar os dados à collection.

### **application.properties**
```properties
# Nome da aplicação
spring.application.name=ecommerce

# URI de conexão com o MongoDB
spring.data.mongodb.uri=mongodb://stoomTest:admin123@localhost:27018/stoomTest?authSource=admin
```