create table medicos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    numero varchar(20),
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf char(2) not null,
    cep varchar(9) not null,

    primary key(id)

);