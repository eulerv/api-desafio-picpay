DROP TABLE IF EXISTS transactions, wallets, users;

CREATE TABLE
  IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
  );


CREATE TABLE
  IF NOT EXISTS wallets (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    full_name VARCHAR(100),
    cpf BIGINT,
    email VARCHAR(100),
    "password" VARCHAR(100),
    "type" INT,
    balance DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES users (id) -- Chave estrangeira para a tabela de usuários
  );


CREATE UNIQUE INDEX IF NOT EXISTS cpf_index ON wallets (cpf);

CREATE UNIQUE INDEX IF NOT EXISTS email_index ON wallets (email);

CREATE TABLE
  IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    payer INT,
    payee INT,
    "value" DECIMAL(10, 2),
    created_at TIMESTAMP,
    FOREIGN KEY (payer) REFERENCES wallets (id),
    FOREIGN KEY (payee) REFERENCES wallets (id)
  );