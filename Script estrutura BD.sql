create table estado (uf varchar(10) not null primary key,
		     nome varchar(60) not null)	;



create table cidade (
id_ibge varchar(10) not null primary key,
uf varchar(10) references estado(uf), 
nome varchar(50) not null);




create table pessoa (id_pessoa integer not null primary key,
id_ibge varchar(10) references cidade(id_ibge),
nome varchar(100) not null); 



create table usuario (id_pessoa integer not null primary key references pessoa(id_pessoa),
login varchar(100) not null,
senha varchar(30),
adm boolean not null
);


create table cliente (
id_pessoa integer not null primary key references pessoa(id_pessoa), 
bairro varchar(70),
cep varchar(10),
fone varchar(15),
email varchar(70)
);


create table movimentacao (
id_movimentacao integer not null primary key,
id_pessoa integer not null references cliente(id_pessoa),
id_conta integer references conta(id_conta),
data_movimentacao date,
valor_movimentacao numeric(9,2),
descricao varchar(500),
excluido boolean not null);



create table movimento_alteracao (
id_alteracao integer not null primary key,
id_movimentacao integer not null references movimentacao(id_movimentacao),
id_pessoa integer not null references usuario(id_pessoa),
tipo_alteracao varchar(100),
data_alteracao date);





create table primeiro_nivel (
id integer not null primary key,
descricao varchar(500));



create table conta(
id_conta integer not null primary key,
id_primeiro_nivel integer references primeiro_nivel(id),
id_segundo_nivel varchar(500));



