CREATE DATABASE financeiro;

CREATE TABLE estado (
	uf VARCHAR(10) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL
);

CREATE TABLE cidade (
	id_ibge INTEGER PRIMARY KEY,
	uf VARCHAR(10) NOT NULL REFERENCES estado(uf), 
	nome VARCHAR(50) NOT NULL
);

CREATE TABLE pessoa (
	id_pessoa SERIAL PRIMARY KEY,
	id_ibge INTEGER NOT NULL REFERENCES cidade(id_ibge),
	nome VARCHAR(100) NOT NULL
); 

CREATE TABLE usuario (
	id_pessoa INTEGER PRIMARY KEY REFERENCES pessoa(id_pessoa),
	login VARCHAR(100) NOT NULL,
	senha VARCHAR(30) NOT NULL,
	adm BOOLEAN NOT NULL
);

CREATE TABLE cliente (
	id_pessoa INTEGER PRIMARY KEY REFERENCES pessoa(id_pessoa), 
	bairro VARCHAR(70),
	cep VARCHAR(10),
	fone VARCHAR(15),
	email VARCHAR(70),
	excluido BOOLEAN DEFAULT FALSE
);

CREATE TABLE primeiro_nivel (
	id INTEGER PRIMARY KEY,
	descricao VARCHAR(512)
);

CREATE TABLE conta(
	id_conta SERIAL PRIMARY KEY,
	id_primeiro_nivel INTEGER REFERENCES primeiro_nivel(id),
	id_segundo_nivel INTEGER NOT NULL,
	descricao VARCHAR(512)
);

CREATE TABLE movimentacao (
	id_movimentacao serial PRIMARY KEY,
	id_pessoa INTEGER NOT NULL REFERENCES cliente(id_pessoa),
	id_conta INTEGER REFERENCES conta(id_conta),
	data_movimentacao DATE NOT NULL DEFAULT CURRENT_DATE,
	valor_movimentacao NUMERIC(9,2) DEFAULT 0,
	descricao VARCHAR(512),
	excluido BOOLEAN DEFAULT FALSE
);

CREATE TABLE movimento_alteracao (
	id_alteracao SERIAL PRIMARY KEY,
	id_movimentacao INTEGER NOT NULL REFERENCES movimentacao(id_movimentacao),
	id_pessoa INTEGER NOT NULL REFERENCES usuario(id_pessoa),
	tipo_alteracao VARCHAR(100),
	data_alteracao DATE NOT NULL DEFAULT CURRENT_DATE
);

INSERT INTO primeiro_nivel (id, descricao) VALUES
(1, 'Crédito'),
(2, 'Débito');