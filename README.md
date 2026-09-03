# Reader Assistant

Biblioteca virtual para importar livros, mangás e novels em PDF, acompanhar o número de páginas e visualizar o progresso de leitura.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA/Hibernate
- PostgreSQL
- Flyway
- Lombok

## Configuração

Crie um banco PostgreSQL chamado `reader_assistant` e configure as variáveis de ambiente:

```bash
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
```

A aplicação espera o banco em `localhost:5432`.

## Executando o projeto

No diretório do projeto, execute:

```bash
./mvnw spring-boot:run
```

Para compilar:

```bash
./mvnw -DskipTests compile
```

## Modelo principal

- `ReaderEntity`: informações do livro e do arquivo PDF, como nome, tipo, total de páginas e localização do arquivo.
- `ProgressoLivro`: progresso de um usuário em um livro, incluindo página atual, percentual e datas de leitura.
- `TypeReader`: tipos disponíveis: `LIVRO`, `MANGA` e `NOVEL`.

O percentual de leitura é calculado a partir da página atual e do total de páginas, não sendo armazenado separadamente.

## Status

O projeto está em desenvolvimento. As próximas etapas incluem migrations do banco, endpoints REST para livros e progresso, autenticação de usuários e armazenamento dos arquivos PDF.
