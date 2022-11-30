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
	situacao varchar (1) not null,
        foto text
);
	
drop table instituicao;

insert into instituicao (nomeinstituicao, cnpj,situacao, foto) values ('fef','12345678910110','A', null);

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
a.idadministrador as id, 'Administrador' as tipo, p.foto
from pessoa p, administrador a
where a.idpessoa = p.idpessoa and a.situacao = 'A' and a.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
o.idorganizador as id, 'Organizador' as tipo, p.foto
from pessoa p, organizador o
where o.idpessoa = p.idpessoa and o.situacao = 'A' and o.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
f.idfornecedor as id, 'Fornecedor' as tipo, p.foto
from pessoa p, fornecedor f
where f.idpessoa = p.idpessoa and f.situacao = 'A' and f.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
d.idpatrocinador as id, 'Patrocinador' as tipo, p.foto
from pessoa p, patrocinador d
where d.idpessoa = p.idpessoa and d.situacao = 'A' and d.permitelogin = 'S'
union
select p.idpessoa, p.nomerazaopessoa, p.cpfcnpjpessoa, p.login, p.senha, 
t.idparticipante as id, 'Participante' as tipo, p.foto
from pessoa p, participante t
where t.idpessoa = p.idpessoa and t.situacao = 'A' and t.permitelogin = 'S'

create table evento (
    idevento serial primary key,
    nomeevento varchar (100),
    valorevento numeric,
    valoreventoprazo numeric,
    datainicioevento date,
    dataterminoevento date,
    informacaoevento varchar (100),
    situacaoevento  varchar (1),
    saldocaixa numeric,
    situacaocaixa varchar (25),
    foto text,
    idcurso int not null,
    constraint fk_curso_evento foreign key (idcurso) references curso(idcurso)
);

insert into evento (nomeevento, valorevento, valoreventoprazo, datainicioevento, dataterminoevento, informacaoevento, 
situacaoevento, saldocaixa, situacaocaixa, foto, idcurso) values ('Corrida Acadêmica', 50.00, 75.00, '24/09/2022', '24/10/2022', 
'Evento Encerrado', 'A', 1500.00, 'Saldo Positivo', null, 1);

create table organizadorevento(
	idorganizadorevento serial not null primary key,
	idorganizador integer not null,
	idevento integer not null,
	idfuncao integer not null,
	constraint fk_id_organizador foreign key(idorganizador) references organizador(idorganizador),
	constraint fk_id_evento foreign key(idevento) references evento(idevento),
	constraint fk_id_funcao foreign key(idfuncao) references funcao(idfuncao)
);

insert into organizadorevento(idorganizador, idevento, idfuncao) values (1, 1, 1);

create table doacao(
	iddoacao serial primary key,
	idpatrocinador integer,
	valordoacao numeric,
	datadoacao date,
	descricao varchar(200),
	situacao varchar(1),
	constraint fk_patrocinador foreign key(idpatrocinador) references patrocinador(idpatrocinador)
);

insert into doacao(idpatrocinador, valordoacao, datadoacao, descricao, situacao) values
(1, 1500.00, '20/11/2022', 'Depósito Teste', 'A');

create table despesa(
	iddespesa serial primary key,
	idfornecedor integer,
	valordespesa numeric,
	vencimentodespesa date,
	pagamentodespesa date,
	descricao varchar(150),
	situacao varchar(1),
	constraint fk_fornecedor foreign key(idfornecedor) references fornecedor(idfornecedor)
);

insert into despesa (idfornecedor, valordespesa, vencimentodespesa, pagamentodespesa, descricao, situacao) values
(1, 152.20, '22/11/2022', '20/11/2022', 'Estrutura', 'A');

create table atividadeevento(
	idatividadeevento serial primary key,
	cargahoraria numeric,
	nomeatividade varchar(150),
	resumo varchar(200),
	dataatividade date,
	horaatividade numeric,
	idevento integer,
	idtipoatividade integer,
	constraint fk_tipoatividade foreign key (idtipoatividade) references tipoatividade(idtipoatividade),
	constraint fk_atividadeevento foreign key (idevento) references evento (idevento)
);

insert into atividadeevento (cargahoraria, nomeatividade, resumo, dataatividade, horaatividade, idevento,idtipoatividade) values
(100.0, 'Extensão', 'Evento de Extensão', '25/11/2022', '30', 1,1);

create table inscricao(
	idinscricao serial primary key,
	nropulseira varchar(50),
	chaveqrcode varchar(100),
	datainscricao date,
	situacao varchar(1),
	valorinscricao numeric,
	nroparcelas integer,
	parcelaspagas numeric,
	parcelaspagar numeric,
	parcelascanceladas numeric,
	parcelasestorno numeric,
	idevento integer,
	idparticipante integer,
	constraint fk_inscricao_evento foreign key (idevento) references evento(idevento),
	constraint fk_participante_inscricao foreign key (idparticipante) references participante(idparticipante)
);

insert into inscricao (nropulseira, chaveqrcode, datainscricao, situacao, valorinscricao, nroparcelas ,
parcelaspagas, parcelaspagar, parcelascanceladas, parcelasestorno, idevento, idparticipante) values
('1478523599', 'S7GS8EHR8H8237R1BBHSBCA7W7', '24.11.2022', 'A', 150.00, 10, 2.0, 8.0, 0.0, 1.0, 1, 1);

create table estorno(
	idestorno serial primary key,
	valorestorno numeric,
	datapagamento date,
	descricao varchar(100),
	dataestorno date,
	situacao varchar(1)
)

insert into estorno (idestorno, valorestorno, datapagamento, descricao, dataestorno, situacao) values
(1, 152.20, '22/11/2022', 'Estrutura', '20/11/2022', 'A');

create table parcelamento(
	idparcelamento serial primary key,
	nroparcela integer,
	valorparcela numeric,
	datavencimento date,
	datapagamento date,
	situacao varchar(1),
	descricao varchar
);

insert into parcelamento(idparcelamento,nroparcela, valorparcela, datavencimento, datapagamento, situacao, descricao)
values (1,1,50.00,'30/11/2022','29/11/2022','A','Parcela teste');

select * from parcelamento