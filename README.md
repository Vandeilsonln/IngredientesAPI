# **API - Controle de Ingredientes**

#### A presente API foi construída com o intuito de aplicar os conhecimentos em desenvolvimento de **API REST** com **Java** e **Spring Boot**.

#### A aplicação realiza as operações básicas de CRUD para o gerenciamento de ingredientes de uma confeitaria, com informações sobre tipo do produto e preço.
![REST http Verbs](https://www.codeproject.com/KB/webservices/826383/table.png)


### :computer: **Tecnologias usadas**

- Apache Maven
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Junit
## :open_book: **1. Etapas de desenvolvimento**
:white_check_mark: Implementação dos principais métodos **REST HTTP**, também conhecido como **CRUD**.
- [ ] Configuração de conexão com banco de dados local e *in memory* (**H2**).
- [ ] Implementação de testes unitários
- [ ] Deploy em nuvem no **Heroku**


## :hammer_and_wrench: **2 - Configurações iniciais**

### **2.1 - Criar o banco e a table no MySQL**

#### Para dar início à configuração do projeto, primeiramente é necessário a **construção de uma tabela no MySQL**. A partir dela poderá ser feito o mapeamento com o JPA.
#### Deve-se, portanto, criar uma databse com o nome "**db_vendasbolos**", e dentro dela criar a tabela "**tbl_ingredientes**"
![Tabela de ingredientes do MySQL](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/tbl_ingredientes.png?raw=true)


#### Segue abaixo o código SQL para criação da table:

```sql
CREATE TABLE IF NOT EXISTS tbl_ingredientes (
id_ingrediente INT AUTO_INCREMENT,
descricao varchar(50) not null,
preco int not null,
volume_peso int not null,
unidade_medida varchar(8) not null,

CONSTRAINT ingredientes_id_pk primary key(id_ingrediente),
CONSTRAINT ingredientes_descricao_UN UNIQUE(descricao),
CONSTRAINT ingredientes_unidade_medida CHECK(unidade_medida in ('kg', 'g', 'ml', 'l'))
);
```


<details>

 <summary>A seguir, podemos popular a tabela com alguns elementos, com o código abaixo: (Clique para Expandir)</summary>

```sql
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Leite condensado', 4.5, 395, 'g');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Creme de Leite', 2.7, 200, 'g');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Leite', 3.2, 1, 'l');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Manteiga', 5, 500, 'g');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Farinha de trigo', 4.6, 1, 'kg');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Cacau em pó', 20, 200, 'g');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Ovos', 0.4, 50, 'g');
INSERT INTO tbl_ingredientes (descricao, preco, volume_peso, unidade_medida) VALUES('Chantily', 11, 1, 'l');
```
</details>

### **2.2 - Configurando as propriedades**
#### Uma vez criado a database, devemos configurá-lo no arquivo "**application.properties**", tomando os devidos cuidados com os atributos de ***url*, *username* e *password***, para que a conexão aconteça corretamente.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_vendasbolos?useSSL=false
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

## :building_construction: **3- Estrutura do projeto**

![Estrutura do projeto](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/estrutura.png?raw=true)

### **3.1 Controller**

#### Camada responsável por orquestrar o fluxo da aplicação ao fazer o mapeamento e o direcionamento dos *requests* para a camada de serviços.

![Camada de Controller](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Controller.png?raw=true)

### **3.2 Entity**

#### Camada responsável por fazer o **ORM** *(Object Relational Mapper)* da tabela "*tbl_ingredientes*" para que se comporte como uma classe comum em Java.

![Camada de Controller](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Entity.png?raw=true)

### **3.3 Service**

#### Camada onde serão inseridas as regras de negócio, e onde injetamos o repository para que seja possível a chamada dos métodos que farão a persistência

![Camada Service](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Service.png?raw=true)

### **3.4 Repository**

#### Nesta camada criamos uma interface que extende a interface *JpaRepository* do *Spring Data JPA*. É através dela que iremos usar a camada de persistência para gravar e recuperar dados, fazendo uma ponte com o banco de dados.

![Camada Repository](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Repository.png?raw=true)

## **4 - Testando a API com o Postman**

## **4.1 - Método GET** 
#### **getIngredientes** - Retorna todos as tuplas presentes na tabela
```http
http://localhost:8080/api/v1/ingrediente/all
```
#### **getById** - Retorna pelo Id fornecido
```http
http://localhost:8080/api/v1/ingrediente/byId/2
```

#### **getByDescricao** - Retorna pela descrição
```http
http://localhost:8080/api/v1/ingrediente/byDescription/Ovos
```

## **4.2 - Método POST**
#### **createIngrediente** - Cadastra uma tupla no banco.
```http
http://localhost:8080/api/v1/ingrediente/addSingle
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
{   
    "id_ingrediente": 9,
    "descricao": "Teste POST ingrediente",
    "preco": 24.5,
    "volumePeso": 1,
    "type": "kg"
}
```
</details>

#### **createAllIngredientes** - Cadastra uma lista de tuplas no banco.
```http
http://localhost:8080/api/v1/ingrediente/addMany
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
[
{   
    "id_ingrediente": 10,
    "descricao": "Ingrediente 1 da lista",
    "preco": 14.5,
    "volumePeso": 400,
    "type": "ml"
},
{
    "id_ingrediente": 11,
    "descricao": "Ingrediente 2 da lista",
    "preco": 5,
    "volumePeso": 2,
    "type": "l"
}
]
```
</details>

## **4.3 - Método PUT**
#### **updateIngrediente**
```
http://localhost:8080/api/v1/ingrediente/update/11
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
{   
    "id_ingrediente": 11,
    "descricao": "Chocolate nobre meio amargo",
    "preco": 40,
    "volumePeso": 1,
    "type": "kg"
}
```
</details>

## **4.4 - Método DELETE**
#### **deleteById**
```
http://localhost:8080/api/v1/ingrediente/delete/11
```