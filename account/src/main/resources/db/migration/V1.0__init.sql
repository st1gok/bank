DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS bank_accounts;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    login   varchar(100)  NOT NULL,
    password_hash   varchar(1000) NOT NULL
);

CREATE TABLE accounts
(
    id SERIAL PRIMARY KEY,
    user_id   BIGINT NOT NULL,
    name   varchar(100) NOT NULL,
    surname  varchar(100) NOT NULL,
    email  varchar(100) NOT NULL,
    birthday  timestamp NOT NULL
);

CREATE TABLE bank_accounts
(
    id SERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    currency varchar(100)  NOT NULL,
    amount numeric NOT NULL
);

INSERT INTO users (login, password_hash)
VALUES ('admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC');

INSERT INTO accounts (user_id, name, surname, birthday,email)
VALUES (1, 'admin', 'admin', '1990-10-01', 'admin@temp.ru');


