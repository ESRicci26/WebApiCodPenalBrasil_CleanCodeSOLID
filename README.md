# CodigoPenalBrasil

Aplicação **WEB + API REST** em **Java 11 / Spring Boot / Maven / Spring JDBC (`JdbcTemplate`)**, com frontend em **Thymeleaf** (layout responsivo com Bootstrap 5), consumindo o banco **MySQL** `codigopenalbrasil` (tabela `codigopenal`).

Baseado na aplicação Java Swing original (`Maven-Swing-CODIGOPENALBRASIL`), reescrita seguindo **Clean Code**, **SOLID** e organizada em camadas inspiradas em **Clean Architecture / Arquitetura Hexagonal**.

Pacote base: `com.javaricci`

## Arquitetura em camadas

```
com.javaricci
├── dominio                                → Núcleo de negócio (sem dependência de frameworks)
│   ├── modelo/CodigoPenal.java            → Entidade de domínio
│   ├── excecao/                           → Exceções de domínio
│   └── portas/
│       ├── entrada/                       → Interfaces dos Casos de Uso (Input Ports)
│       └── saida/                         → Interface do Repositório (Output Port)
│
├── aplicacao/casosdeuso                   → Implementação dos Casos de Uso (1 classe por operação)
│   ├── ListarCodigosPenaisCasoDeUso       (Lista)
│   ├── BuscarCodigoPenalPorIdCasoDeUso
│   ├── CadastrarCodigoPenalCasoDeUso      (Insert)
│   ├── AlterarCodigoPenalCasoDeUso        (Update)
│   ├── ExcluirCodigoPenalCasoDeUso        (Delete)
│   ├── BuscarCodigoPenalPorArtigoCasoDeUso
│   └── BuscarCodigoPenalPorTipoLeiCasoDeUso
│
├── infraestrutura                         → Adaptadores (detalhes técnicos)
│   ├── persistencia/                      → Adaptador de saída: JdbcTemplate + MySQL
│   │   ├── CodigoPenalRepositorioJdbc.java   (Insert/Update/Delete/Lista dedicados)
│   │   └── CodigoPenalMapeadorDeLinha.java   (RowMapper)
│   └── web/
│       ├── controlador/
│       │   ├── CodigoPenalRestControlador.java  → API REST (/api/codigospenais)
│       │   └── CodigoPenalWebControlador.java   → MVC/Thymeleaf (/codigospenais)
│       ├── dto/                            → DTOs de requisição/resposta + mapeador
│       └── manipuladorexcecao/             → Tratamento global de exceções (REST e Web)
│
└── configuracao                            → Configurações Spring (MVC, etc.)
```

Esse desenho segue o **Princípio de Inversão de Dependência (DIP)**: o domínio define as *portas* (interfaces) e a infraestrutura (JDBC, REST, Web) as implementa/consome — o núcleo de negócio nunca depende de detalhes técnicos.

## Pré-requisitos

- Java 11+
- Maven 3.6+
- MySQL 8 com o banco `codigopenalbrasil` e a tabela `codigopenal` já populados (ver `scripts/schema-mysql.sql` como referência de estrutura)

## Configuração

Edite `src/main/resources/application.properties` com as credenciais do seu MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/codigopenalbrasil?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
```

## Como executar

```bash
mvn spring-boot:run
```

ou gerando o JAR:

```bash
mvn clean package
java -jar target/CodigoPenalBrasil.jar
```

A aplicação sobe em `http://localhost:8080`.

- **Frontend (Thymeleaf):** http://localhost:8080/codigospenais
- **API REST:** http://localhost:8080/api/codigospenais

## Endpoints da API REST

| Método   | Endpoint                               | Descrição                                  |
|----------|-----------------------------------------|---------------------------------------------|
| `GET`    | `/api/codigospenais`                    | Lista todos os artigos                      |
| `GET`    | `/api/codigospenais/{id}`               | Busca um artigo pelo id                     |
| `GET`    | `/api/codigospenais/pesquisar?termo=`   | Pesquisa por artigo/descrição               |
| `GET`    | `/api/codigospenais/tipo-lei?tipoLei=`  | Pesquisa por tipo de lei                    |
| `POST`   | `/api/codigospenais`                    | Cadastra um novo artigo (Insert)            |
| `PUT`    | `/api/codigospenais/{id}`               | Altera um artigo existente (Update)         |
| `DELETE` | `/api/codigospenais/{id}`               | Exclui um artigo (Delete)                   |

### Exemplo de corpo (POST/PUT)

```json
{
  "artigo": "Art. 121",
  "descricaoArtigo": "Matar alguem: Pena - reclusao, de seis a vinte anos.",
  "tituloLei": "Decreto-Lei no 2.848, de 7 de dezembro de 1940",
  "tipoLei": "Codigo Penal"
}
```

## Rotas Web (Thymeleaf)

| Método | Rota                              | Descrição                     |
|--------|-------------------------------------|--------------------------------|
| GET    | `/codigospenais`                    | Listagem + pesquisa            |
| GET    | `/codigospenais/{id}`               | Detalhe do artigo              |
| GET    | `/codigospenais/novo`               | Formulário de cadastro         |
| POST   | `/codigospenais`                    | Processa o cadastro            |
| GET    | `/codigospenais/{id}/editar`        | Formulário de edição           |
| POST   | `/codigospenais/{id}`               | Processa a alteração           |
| POST   | `/codigospenais/{id}/excluir`       | Processa a exclusão            |

## Observações

- O Lombok está incluso no `pom.xml` como dependência opcional (não é usado nas entidades principais para manter o código explícito e didático, mas pode ser adotado livremente).
- O tratamento de erros retorna respostas JSON padronizadas na API (`ManipuladorGlobalDeExcecoes`) e páginas amigáveis no frontend (`ManipuladorWebDeExcecoes`).
