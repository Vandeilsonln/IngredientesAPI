# **API - Controle de Ingredientes**

##### A presente API foi construída com o intuito de aplicar os conhecimentos em desenvolvimento de **API REST** com **Java** e **Spring Boot**.

##### A aplicação realiza as operações básicas de CRUD para o gerenciamento de ingredientes de uma confeitaria, com informações sobre tipo do produto e preço.
![REST http Verbs](https://www.codeproject.com/KB/webservices/826383/table.png)


### :open_book: **Etapas de desenvolvimento**
:white_check_mark: Implementação dos principais métodos **REST HTTP**, também conhecido como **CRUD**.
- [ ] Implementação de testes unitários
- [ ] Deploy em nuvem no **Heroku**
- [ ] Configuração de conexão com banco de dados local e *in memory* (**H2**).

### :computer: **Tecnologias usadas**

- Apache Maven
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Junit

## :hammer_and_wrench: **Como usar**

##### Para dar início à configuração do projeto, primeiramente é necessário a **construção de uma tabela no MySQL**. A partir dela poderá ser feito o mapeamento com o JPA.
##### Deve-se, portanto, criar uma databse com o nome "**db_vendasbolos**", e dentro dela criar a tabela "**tbl_ingredientes**"
![Tabela de ingredientes do MySQL](https://github.com/Vandeilsonln/IngredientesAPI/blob/master/_images/tbl_ingredientes.png?raw=true)

### Segue abaixo o código SQL para criação da table:
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

##### A seguir, podemos popular a tabela com alguns elementos, com o código abaixo:
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