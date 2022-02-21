# SCS - Sistema de controle de senhas

API para controle de senhas que serão geradas por usuários "normais" e que serão chamadas por usuários "gerentes".

Todas as funcionalidades serão acessadas utilizando endpoints REST, variando entre métodos GET e POST.

# Banco de Dados

Para armazenamento dos dados foi utilizando banco de dados PostgreSQL (14.2). Segue abaixo as tabelas construídas.:

## SENHA

Conjunto de entidades representando todas as senhas que foram geradas pelos usuários, bem como as que já foram "chamadas" pelos usuários gerentes.

```
create table
```

Exemplo:

| id | senha | data_hora_senha_gerada | data_hora_senha_atendimento | tipo | status |
| -- | ----- | ---------------------- | --------------------------- | ---- | ------ |
| 1 | N0001 | 2022-02-18 17:00:00 | 2022-02-18 17:00:15 | NORMAL | CONCLUIDO |
| 2 | N0002 | 2022-02-18 17:01:00 | 2022-02-18 17:01:10 | NORMAL | CONCLUIDO |
| 3 | N0003 | 2022-02-18 17:02:00 | null | NORMAL | PENDENTE |
| 4 | P0001 | 2022-02-18 17:02:10 | 2022-02-18 17:02:15 | PREFERENCIAL | CONCLUIDO |
| 5 | N0004 | 2022-02-18 17:03:00 | null | NORMAL | PENDENTE |

## SENHA_CONTROLE

Tabela de controle dos contadores das senhas normais e preferenciais. A existencia dessa tabela possibilita que os contadores sejam reinicializados, além de servir de referencia para a geração da próxima senha. Isso evita que uma nova geração de senha envolva a consulta de todas as senhas já geradas para o calculo do próximo número.

```
create table
```

Exemplo:

| id | senha_preferencial | senha_normal |
| -- | ------------------ | ------------ |
| 1 | 35 | 7 |

* Nesta tabela deve existir apenas UMA linha.
** A criação das tabelas é feita de forma automática ao subir a aplicação (spring.jpa.hibernate.ddl-auto=update)


