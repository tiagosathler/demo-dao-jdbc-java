# Demo DAO JDBC

__Exercício de demonstração de um CRUD usando o padrão DAO com JDBC__

## Introdução

Exercício assistido e desenvolvido durante o Curso de [Java Completo](https://www.udemy.com/course/java-curso-completo/) da plataforma Udemy através do [![Linkedin](https://i.stack.imgur.com/gVE0j.png) Prof. Dr. Nelio Alves](https://br.linkedin.com/in/nelio-alves). O repositório das minhas atividades no curso podem ser acessados [aqui](https://github.com/tiagosathler/udemy-java-complete-course).

O objetivo deste exercício é implementar manualmente um _CRUD_ (create/read/update/delete) completo de um simples banco de dados utilizando o padrão __DAO__ (_Data Acess Object_) e o __JDBC__ (_Java Database Connectivity_).

## Design do exercício

O JDBC é API padrão do Java para acesso a dados. Vamos utilizar o MySQL como banco de dados e sua respectiva biblioteca de driver para o JDBC.

O padrão DAO é surgiu com a necessidade de separarmos a lógica de negócios da lógica de persistência de dados. Este padrão permite que possamos mudar a forma de persistência sem que isso influencie em nada na lógica de negócio, além de tornar as classes mais legíveis (fonte: DevMedia - [_DAO Pattern: Persistência de Dados utilizando o padrão DAO_](https://www.devmedia.com.br/dao-pattern-persistencia-de-dados-utilizando-o-padrao-dao/30999)).

Então as classes DAO são responsáveis por trocar as informações com o SGBD e fornecer operações CRUD e de pesquisas, implementando as _queries_ do DB. Como a lógica de negócio está desacoplada podemos dizer que o DAO está implementado na camada Model. Mas este exercício ainda não é uma API Rest com as camadas Service e Resource ou Controller da arquitetura MSC ou MSR. É apenas uma prática de como se conectar manualmente ao SGBD.

O DB está representado conforme o diagrama relacional a seguir. O script de SQL está disponível [aqui](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/sql/sql_script.sql).

![EER_Diagram](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/sql/eer_diagram.png)

A mesma relação das entidades está representado na forma de um diagrama UML conforme figura a seguir (créditos ao material do curso).

![Entities_UML](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/misc/entities.png)

O exercício implementa o diagrama UML a seguir, na qual pode-se ver o padrão de código Factory como técnica para instanciar os objetos DAO das entidades. Cada uma delas deverá implementar os métodos do CRUD de suas respectivas interfaces.

![Design_UML](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/misc/uml.png)

## Ambiente de desenvolvimento e execução

O exercício foi desenvolvido na IDE [_Eclipse_](https://www.eclipse.org/) versão 2022-14 (4.26.0), Linux Ubuntu 22.04 LTS e Java OpenJDK 11. Mas pode ser executado também em versões superiores.

Para executar este exercício é necessária a biblioteca do driver MySQL para o JDBC. Utilizei o [Connector/J do MySQL](https://dev.mysql.com/downloads/connector/j/) para Ubuntu na versão 8.0.31.

O programa executa algumas operações pré-definidas conforme descrito na classe [_Program_](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/src/application/Program.java). São duas baterias de testes, um para cada entidade _Seller_ e outro para _Department_. São as implementações a seguir:

#### 1. CRUD para _Seller_

1.1. `findById(id)` - busca um _seller_ pelo `id` igual 3 (fixo);
1.2. `findByDepartment(department)` - busca todos os _sellers_ de uma entidade _department_ instanciada;
1.3. `findAll()` - busca por todos os _sellers_;
1.4. `insert(seller)` - insere um novo _seller_ instanciado;
1.5. `update(seller)` - atualizada um _seller_ existente;
1.6. `deleteById(id)` - apaga um _seller_ pelo _id_ solicitado via prompt.

#### 2. CRUD para _Department_

2.1. `findById(id)` - busca um _department_ pelo id = 3 (fixo);
2.2. `findAll()` - busca por todos os _departments_;
2.3. `insert(department)` - insere um novo _department_ instanciado;
2.4. `update(department)` - atualizada um _department_ existente;
2.5. `deleteById(id)` - apaga um _department_ pelo _id_ solicitado via prompt.

Antes de executar é necessário ter o MySQL instalado e rodando na máquina. Execute o [script](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/sql/sql_script.sql) para gerar e popular o banco de dados __coursejdbc__. Além disto, é necessário criar o arquivo __db.properties__ na pasta raiz. Use o arquivo de exemplo [db.properties.example](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/db.properties.example), editando-o com suas informações de _user_ e _password_ de acesso ao seu MySQL, bem como a porta de conexão, que por padrão é a _3306_.

O programa pode ser executado diretamente do console da IDE. Mas para isto é necessário baixar a biblioteca Connector/J e adicioná-la às bibliotecas de usuário no Eclipse.

Para executar diretamente no terminal, utilize o arquivo exportado do exercício `demo-dao-jdbc.jar` da pasta _exported_. A biblioteca Connector/J do MySQL está incluída na exportação. Da pasta raiz do repositório clonado, execute:

```
java -cp exported/demo-dao-jdbc.jar application.Program
```

A figura a seguir mostra a um exemplo de execução do programa. Observe que o programa solicitará qual _id_ deseja apagar em cada bateria de teste. Se houver algum _department_ relacionado a algum _seller_ não será possível apagá-lo, e o programa lançará um _DbException_.

> Lembrando que é necessário ter o Java instalado na máquina na versão 11 ou superior e MySQL.

![Tela_execução](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/misc/tela.png)

## Carga horária

O exercício foi guiado por 26 videos-aulas do curso, cerca de 2h52m de gravações e atividades de desenvolvimento de aproximadamente 8h17m, entre os dias 5 e 6 de dezembro de 2022.

![Wakatime](https://github.com/tiagosathler/demo-dao-jdbc-java/blob/master/misc/wakatime-report.png)

## Conteúdo: Demo DAO JDBC

  1. _Classe Department_
  2. _Classe Seller_
  3. _Interfaces DepartmentDao e SellerDao_
  4. _SellerDaoJDBC e DaoFactory_
  5. _Implementando findById_
  6. _Reutilizando a instanciação_
  7. _Implementando findByDepartment_
  8. _Implementando findAll_
  9. _Implementando insert_
  10. _Implementando update_
  11. _Implementando delete_
  12. _Implementação e teste do DepartmentDao_
