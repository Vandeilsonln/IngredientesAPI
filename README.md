# <center>**API - Controle de Ingredientes**</center>

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
## <center>:open_book: **1. Etapas de desenvolvimento**</center>
:white_check_mark: Implementação dos principais métodos **REST HTTP**, também conhecido como **CRUD**.

:white_check_mark: Configuração de conexão com banco de dados local e *in memory* (**H2**).
- [ ] Implementação de testes unitários
- [ ] Deploy em nuvem no **Heroku**

## <center>:building_construction: **2 - Estrutura do projeto**</center>

![Estrutura do projeto](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/estrutura.png?raw=true)

### **2.1 Controller**

#### Camada responsável por orquestrar o fluxo da aplicação ao fazer o mapeamento e o direcionamento dos *requests* para a camada de serviços.

![Camada de Controller](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Controller.png?raw=true)

### **2.2 Entity**

#### Camada responsável por fazer o **ORM** *(Object Relational Mapper)* da tabela "*tbl_ingredientes*" para que se comporte como uma classe comum em Java.

![Camada de Controller](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Entity.png?raw=true)

### **2.3 Service**

#### Camada onde serão inseridas as regras de negócio, e onde injetamos o repository para que seja possível a chamada dos métodos que farão a persistência.

![Camada Service](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Service2.png?raw=true)

### **2.4 Repository**

#### Nesta camada criamos uma interface que extende a interface *JpaRepository* do *Spring Data JPA*. É através dela que iremos usar a camada de persistência para gravar e recuperar dados, fazendo uma ponte com o banco de dados.

![Camada Repository](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/Repository.png?raw=true)

## <center>:hammer_and_wrench: **3 - Rodando a aplicação com o profile correto**</center>

#### Foram configurados **2 profiles** para podermos testar a aplicação: 
![Profiles](https://github.com/Vandeilsonln/IngredientesAPI/blob/remotedb/_images/Profiles.png?raw=true)
 - O profile "**prod**" vai subir a aplicação com o banco de dados **MySQL**. Para tanto, é necessário que você tenha o mesmo instalado em sua máquina, com o database e a tabela criadas com os campos corretos. Mas não se preocupe, o código para a criação de tudo será fornecido logo abaixo! :relaxed:
 - O profile "**dev**" vai subir a aplicação com o banco **H2 *in-memory***. Dessa forma, é possível testar os métodos sem inteferir no banco de produção, além de não ser necessário nenhum software adicional instalado.

#### A forma de escolher qual o profile será o ativo na hora de rodar a aplicação é selecionando o *configuration*, conforme imagem abaixo:
![Selecionando Profile](https://github.com/Vandeilsonln/IngredientesAPI/blob/remotedb/_images/selectProfile.PNG?raw=true)

#### E super importante, para informar ao Spring Boot qual desses profiles será o ativo em cada *configuration*, é necessário inserir uma *variável de ambiente* (Environment Variable), conforme imagem abaixo. 
![Setando environment variables](https://github.com/Vandeilsonln/IngredientesAPI/blob/remotedb/_images/envvar.PNG?raw=true)
### **3.1 - Rodando com o profile "*prod*" (MySQL)**
<details>

<summary>Clique para visualizar a configuração</summary>

#### Primeiramente é necessário a **construção de uma tabela no MySQL**. A partir dela poderá ser feito o mapeamento com o JPA.
#### Deve-se, portanto, criar uma databse com o nome "**db_vendasbolos**", e dentro dela criar a tabela "**tbl_ingredientes**"
![Tabela de ingredientes do MySQL](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/tbl_ingredientes.png?raw=true)


#### Segue abaixo o código SQL para criação da database e da tabela:

```sql
CREATE database db_vendasbolos;

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

 <summary>A seguir, podemos popular a tabela com alguns registros, com o código abaixo: (Clique para Expandir)</summary>

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

### **3.1.1 - Configurando as propriedades**
#### Uma vez criado a database, devemos configurá-lo no arquivo "**application-prod.properties**", tomando os devidos cuidados com os atributos de ***url*, *username* e *password***, para que a conexão aconteça corretamente.

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/db_vendasbolos?useSSL=false
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```
</details>

### **3.2 - Rodando com o profile *"dev" (H2)***
<details>
<summary>Clique aqui para visualizar a configuração</summary>

#### Subindo a aplicação com esse profile, basta acessar o arquivo **application-dev.properties** e colocar os atributos abaixo:
```properties
# in-memory H2 Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2

spring.datasource.url=jdbc:h2:mem:db_vendasbolos
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```
</details>

## <center>**4 - Testando a API com o Postman**</center>

## **4.1 - Método GET** 
#### **getIngredientes** - Retorna todos as tuplas presentes na tabela
```http
http://localhost:8080/api/v1/ingrediente/all
```
---
#### **getById** - Retorna pelo Id fornecido
```http
http://localhost:8080/api/v1/ingrediente/byId/2
```
---
#### **getByDescricao** - Retorna pela descrição
```http
http://localhost:8080/api/v1/ingrediente/byDescription/Ovos
```
---
## **4.2 - Método POST**
#### **createIngrediente** - Cadastra uma tupla no banco.
```http
http://localhost:8080/api/v1/ingrediente/addSingle
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
{   
    "descricao": "Teste POST ingrediente",
    "preco": 24.5,
    "volumePeso": 1,
    "type": "kg"
}
```
</details>

---
#### **createAllIngredientes** - Cadastra uma lista de tuplas no banco.
```http
http://localhost:8080/api/v1/ingrediente/addMany
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
[
{   
    "descricao": "Ingrediente 1 da lista",
    "preco": 14.5,
    "volumePeso": 400,
    "type": "ml"
},
{
    "descricao": "Ingrediente 2 da lista",
    "preco": 5,
    "volumePeso": 2,
    "type": "l"
}
]
```
</details>

---
## **4.3 - Método PUT**
#### **updateIngrediente**
```
http://localhost:8080/api/v1/ingrediente/update/11
```

<details>

<summary>Use o JSON abaixo como exemplo: (clique para expandir</summary>

```json
{   
    "descricao": "Chocolate nobre meio amargo",
    "preco": 40,
    "volumePeso": 1,
    "type": "kg"
}
```
</details>

---
## **4.4 - Método DELETE**
#### **deleteById**
```
http://localhost:8080/api/v1/ingrediente/delete/11
```