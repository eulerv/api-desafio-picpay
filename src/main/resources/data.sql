-- POSTGRESQL
INSERT INTO wallets (ID, FULL_NAME, CPF, EMAIL, "password", "type", BALANCE) VALUES (3, 'João - User', 12345678900, 'joao@test.com', '123456', 1, 1000.00);
INSERT INTO wallets (ID, FULL_NAME, CPF, EMAIL, "password", "type", BALANCE) VALUES (4, 'Maria - Lojista', 12345678901, 'maria@test.com', '123456', 2, 1000.00);

INSERT INTO users (username, "password") VALUES ('user', '$2a$10$DA0FzDXFG7KLkhip9rPTyeJl1/Kw1u9yN9pZFmrztvyhlUBzDV/Qu');