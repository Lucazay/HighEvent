    create table estado (
    idestado serial primary key,
    nomeestado varchar(100) not null,
    siglaestado varchar(2) not null,
    situacao varchar(1) not null
);

insert into estado (nomeestado, siglaestado, situacao) values ('São Paulo', 'SP', 'A');

create table cidade(
	idcidade serial primary key,
	nomecidade varchar(100) not null,
	situacao varchar(1) not null,
	idestado int not null,
	constraint fk_estado foreign key (idestado) references estado(idestado)
);

insert into cidade(nomecidade, situacao, idestado) values ('Fernandópolis', 'A', 1);
insert into cidade(nomecidade, situacao, idestado) values ('Jales', 'A', 1);

create table funcao(
	idfuncao serial primary key,
	descricao varchar(100) not null
);

insert into funcao (descricao) values ('Apresentação teste');

create table instituicao (
	idinstituicao serial primary key,
	nomeinstituicao varchar (100) not null,
	cnpj varchar (14) not null,
	situacao varchar (1) not null
);
	
drop table instituicao;

insert into instituicao (nomeinstituicao, cnpj,situacao) values ('fef','12345678910110','A');

create table curso (
	idcurso serial primary key,
	nomecurso varchar (100) not null,
	situacao varchar (1) not null,
	idinstituicao int not null,
	constraint fk_instituicao foreign key (idinstituicao) references instituicao (idinstituicao)
);

drop table curso;

insert into curso (nomecurso,situacao,idinstituicao) values ('matematica','A','1')

create table tipotrabalho (
	idtipotrabalho serial primary key,
	descricao varchar (100) not null,
	situacao varchar (1) not null
);

insert into tipotrabalho (descricao, situacao) values ('shazam','I')

create table pessoa (
    idpessoa serial primary key,
    nomerazaopessoa varchar(100),
    rglepessoa varchar(15),
    cpfcnpjpessoa varchar(18),
    datanascfundpessoa date,
    telefonepessoa varchar(14),
    idcidade int,
    logradouropessoa varchar(50),
    numlogradouropessoa varchar(5),
    bairropessoa varchar(100),
    ceppessoa varchar(10),
    emailpessoa varchar(50),
    login varchar(25),
    senha varchar(25),
    foto text,
    constraint fk_cidade foreign key (idcidade) references cidade
);

insert into pessoa(nomerazaopessoa, rglepessoa, cpfcnpjpessoa, datanascfundpessoa, telefonepessoa, idcidade, logradouropessoa,
numlogradouropessoa, bairropessoa, ceppessoa, emailpessoa, login, senha, foto)  values 
('João da Silva', '51.248.798-56', '452.982.002-98', '09/10/2002', '(17)99109-1892', 2, 'Rua Bahia', '2513', 'Centro', '17.782-000', 
'joazinho@hotmail.com', 'Josilva', '123456', null);

create table administrador (
        idadministrador serial primary key,
        idpessoa int unique,
        situacao varchar(1),
        permitelogin varchar(1),
        constraint fk_administrador_pessoa foreign key (idpessoa) references pessoa
);

insert into administrador (idpessoa,situacao,permitelogin)values (1,'A','S');

create table organizador (
        idorganizador serial primary key,
        idpessoa int unique,
        situacao varchar(1),
        permitelogin varchar(1),
        constraint fk_organizador_pessoa foreign key (idpessoa) references pessoa
);

insert into organizador (idpessoa,situacao,permitelogin)values (6,'A','S');

create table patrocinador (
        idpatrocinador serial primary key,
        idpessoa int unique,
        situacao varchar(1),
        permitelogin varchar(1),
        constraint fk_patrocinador_pessoa foreign key (idpessoa) references pessoa
);

insert into patrocinador (idpessoa,situacao,permitelogin)values (7,'A','S');

create table fornecedor (
        idfornecedor serial primary key,
        idpessoa int unique,
        situacao varchar(1),
        permitelogin varchar(1),
        idtipotrabalho int not null,
        constraint fk_fornecedor_pessoa foreign key (idpessoa) references pessoa,
	constraint fk_tipotrabalho_fornecedor foreign key (idtipotrabalho) references tipotrabalho(idtipotrabalho)
);

insert into fornecedor (idpessoa,situacao,permitelogin)values (8,'A','S', 1);

create table participante (
        idparticipante serial primary key,
        sexoparticipante varchar(10),
        idpessoa int unique,
        situacao varchar(1),
        permitelogin varchar(1),
        constraint fk_participante_pessoa foreign key (idpessoa) references pessoa
);

insert into participante (idpessoa,sexoparticipante,situacao,permitelogin)values (9,'Masculino','A','S');

create table tipoatividade (
	idtipoatividade serial primary key,
	descricao varchar (100) not null,
	situacao varchar (1) not null
);

insert into tipoatividade (descricao, situacao) values ('Atividade 1','A');

create or replace view usuario as 
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
a.idadministrador as id, 'Administrador' as tipo
from pessoa p, administrador a
where a.idpessoa = p.idpessoa and a.situacao = 'A' and a.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
o.idorganizador as id, 'Organizador' as tipo
from pessoa p, organizador o
where o.idpessoa = p.idpessoa and o.situacao = 'A' and o.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
f.idfornecedor as id, 'Fornecedor' as tipo
from pessoa p, fornecedor f
where f.idpessoa = p.idpessoa and f.situacao = 'A' and f.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
d.idpatrocinador as id, 'Patrocinador' as tipo
from pessoa p, patrocinador d
where d.idpessoa = p.idpessoa and d.situacao = 'A' and d.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
t.idparticipante as id, 'Participante' as tipo
from pessoa p, participante t
where t.idpessoa = p.idpessoa and t.situacao = 'A' and t.permitelogin = 'S'