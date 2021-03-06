CREATE TABLE DEPARTAMENTO(
CODDEPARTAMENTO NUMBER(5),
NOME VARCHAR2(20) NOT NULL,
CONSTRAINT PK_DEPARTAMENTO PRIMARY KEY(CODDEPARTAMENTO)
);

CREATE TABLE FUNCIONARIO(
CODFUNCIONARIO NUMBER(5),
BAIRRO VARCHAR2(30) NOT NULL,
CIDADE VARCHAR2(30) NOT NULL,
ESTADO CHAR(2) NOT NULL,
RUA VARCHAR2(50) NOT NULL,
NUMERO VARCHAR2(5) NOT NULL,
CEP CHAR(8) NOT NULL,
RG NUMBER(9) NOT NULL,
CODDEPARTAMENTO NUMBER(5) NOT NULL,
SEXO CHAR(1) NOT NULL,
DATANASCIMENTO DATE NOT NULL,
EMAIL VARCHAR2(50) NOT NULL,
CPF CHAR(11) NOT NULL UNIQUE,
NOME VARCHAR2(100) NOT NULL,
CONSTRAINT PK_FUNCIONARIO PRIMARY KEY(CODFUNCIONARIO),
CONSTRAINT CK_SEXOF CHECK (SEXO ='M' OR SEXO='F'),
CONSTRAINT FK_FUNCIONARIO_DEPARTAMENTO FOREIGN KEY (CODDEPARTAMENTO)
REFERENCES DEPARTAMENTO(CODDEPARTAMENTO)
);

CREATE TABLE TELEFONEFUNC(
TELEFONE NUMBER(13),
CODFUNCIONARIO NUMBER(5),
CONSTRAINT FK_TELEFONEFUNC_FUNCIONARIO FOREIGN KEY(CODFUNCIONARIO)
REFERENCES FUNCIONARIO(CODFUNCIONARIO),
CONSTRAINT PK_TELEFONEFUNC PRIMARY KEY (TELEFONE,CODFUNCIONARIO)
);

CREATE TABLE USUARIO(
CODFUNCIONARIO NUMBER(5),
SENHA VARCHAR2(16) NOT NULL,
NIVELACESSO CHAR(1) NOT NULL,
CONSTRAINT FK_USUARIO_FUNCIONARIO FOREIGN KEY (CODFUNCIONARIO) 
REFERENCES FUNCIONARIO(CODFUNCIONARIO),
CONSTRAINT PK_USUARIO PRIMARY KEY (CODFUNCIONARIO),
CONSTRAINT CK_USUARIO CHECK (NIVELACESSO>=1 AND NIVELACESSO <=5)
);

CREATE TABLE CLIENTE(
CODCLIENTE NUMBER(5),
CLASSIFICACAO VARCHAR2(20) NOT NULL,
DATANASCIMENTO DATE NOT NULL,
SEXO CHAR(1) NOT NULL,
BAIRRO VARCHAR2(30) NOT NULL,
CIDADE VARCHAR2(30) NOT NULL,
ESTADO CHAR(2) NOT NULL,
RUA VARCHAR2(50) NOT NULL,
NUMERO VARCHAR2(5) NOT NULL,
CEP CHAR(8) NOT NULL,
EMAIL VARCHAR2(50) NOT NULL,
CPF CHAR(11) NOT NULL UNIQUE,
NOME VARCHAR2(100) NOT NULL,
CONSTRAINT PK_CLIENTE PRIMARY KEY (CODCLIENTE),
CONSTRAINT CK_SEXOC CHECK (SEXO='M' OR SEXO='F'),
CONSTRAINT CK_CLASSIFICACAO CHECK (CLASSIFICACAO= '4 - MUITO IMPORTANTE' OR CLASSIFICACAO='3 - IMPORTANTE' OR  CLASSIFICACAO='2 - REGULAR' OR CLASSIFICACAO='1 - NOVO') 
);

CREATE TABLE TELEFONECLI(
TELEFONE NUMBER(13),
CODCLIENTE NUMBER(5),
CONSTRAINT FK_TELEFONECLI_CLIENTE FOREIGN KEY(CODCLIENTE)
REFERENCES CLIENTE(CODCLIENTE),
CONSTRAINT PK_TELEFONECLI PRIMARY KEY (TELEFONE,CODCLIENTE)
);

CREATE TABLE REDESOCIAL(
REDESOCIAL VARCHAR(15),
CODCLIENTE NUMBER(5),
CONSTRAINT FK_REDESOCIAL_CLIENTE FOREIGN KEY(CODCLIENTE)
REFERENCES CLIENTE(CODCLIENTE),
CONSTRAINT PK_REDESOCIAL PRIMARY KEY (REDESOCIAL,CODCLIENTE)
);

CREATE TABLE PRODUTO(
CODPRODUTO NUMBER(5),
NOME VARCHAR(50) NOT NULL,
DESCRICAO VARCHAR2(200),
CONSTRAINT PK_PRODUTO PRIMARY KEY (CODPRODUTO)
);

CREATE TABLE SAC(
PROTOCOLO NUMBER(7),
CODFUNCIONARIO NUMBER(5) NOT NULL,
CODCLIENTE NUMBER(5) NOT NULL,
TIPOLIGACAO VARCHAR2(20) NOT NULL,
CODPRODUTO NUMBER(5),
CONSTRAINT PK_SAC PRIMARY KEY(PROTOCOLO),
CONSTRAINT FK_SAC_FUNCIONARIO FOREIGN KEY(CODFUNCIONARIO)
REFERENCES FUNCIONARIO(CODFUNCIONARIO),
CONSTRAINT FK_SAC_CLIENTE FOREIGN KEY(CODCLIENTE)
REFERENCES CLIENTE(CODCLIENTE),
CONSTRAINT FK_SAC_PRODUTO FOREIGN KEY(CODPRODUTO)
REFERENCES PRODUTO(CODPRODUTO),
CONSTRAINT CK_TIPOLIGACAO CHECK (TIPOLIGACAO = 'SUGESTAO' OR TIPOLIGACAO = 'RECLAMACAO' OR TIPOLIGACAO = 'DUVIDA' OR TIPOLIGACAO = 'ELOGIO')
);

CREATE TABLE ESTADOCHAMADA(
PROTOCOLO NUMBER(7),
CODFUNCIONARIO NUMBER(5),
ESTADOCHAMADA VARCHAR2(10) NOT NULL,
CONSTRAINT FK_ESTADOCHAMADA_FUNCIONARIO FOREIGN KEY(CODFUNCIONARIO)
REFERENCES FUNCIONARIO(CODFUNCIONARIO),
CONSTRAINT FK_ESTADOCHAMADA_SAC FOREIGN KEY (PROTOCOLO)
REFERENCES SAC(PROTOCOLO),
CONSTRAINT PK_ESTADOCHAMADA PRIMARY KEY (PROTOCOLO,CODFUNCIONARIO)
);

CREATE TABLE AVALIACAOCHAMADA(
PROTOCOLO NUMBER(7),
CODCLIENTE NUMBER(5),
AVALIACAO NUMBER(1) NOT NULL,
CONSTRAINT FK_AVALIACAOCHAMADA_CLIENTE FOREIGN KEY(CODCLIENTE)
REFERENCES CLIENTE(CODCLIENTE),
CONSTRAINT FK_AVALIACAOCHAMADA_SAC FOREIGN KEY (PROTOCOLO)
REFERENCES SAC(PROTOCOLO),
CONSTRAINT PK_AVALIACAOCHAMADA PRIMARY KEY (PROTOCOLO,CODCLIENTE),
CONSTRAINT CK_AVALIACAO CHECK (AVALIACAO=1  OR AVALIACAO=2 OR  AVALIACAO=3 OR AVALIACAO=4 OR AVALIACAO=5)
);



CREATE TABLE VENDA(
CODVENDA NUMBER(5),
CODCLIENTE NUMBER(5) NOT NULL,
CODPRODUTO NUMBER(5) NOT NULL,
DATACOMPRA DATE NOT NULL,
ORIGEMVENDA VARCHAR2(12) NOT NULL,
MODOPAGAMENTO VARCHAR(25) NOT NULL,
CONSTRAINT PK_VENDA PRIMARY KEY(CODVENDA),
CONSTRAINT FK_VENDA_CLIENTE FOREIGN KEY(CODCLIENTE)
REFERENCES CLIENTE(CODCLIENTE),
CONSTRAINT FK_VENDA_PRODUTO FOREIGN KEY (CODPRODUTO)
REFERENCES PRODUTO(CODPRODUTO),
CONSTRAINT CK_MODOPAGAMENTO CHECK (MODOPAGAMENTO= 'BOLETO BANCARIO' OR MODOPAGAMENTO='CARTAO DE CREDITO' OR  MODOPAGAMENTO='TEF' OR MODOPAGAMENTO='PAY PAL'),
CONSTRAINT CK_ORIGEMVENDA CHECK (ORIGEMVENDA= 'LOJA FISICA' OR ORIGEMVENDA='E-COMMERCE') 
);

INSERT INTO CLIENTE VALUES
(12345,'4 - MUITO IMPORTANTE','11/11/1980','F','VILA AMERICA','SANTO ANDR�','SP',
'AV.DOM PEDRO I','410','09110000','INGRID@GMAIL.COM','54657168201','INGRID SILVA');
INSERT INTO CLIENTE VALUES
(23456,'2 - REGULAR','10/11/1990','M','PARQUE CENTRAL','SANTO ANDR�','SP',
'AV. DOS ESTADOS','2278','09210580','MATEUS123@GMAIL.COM','13850552861','MATEUS LINSKOV');
INSERT INTO CLIENTE VALUES
(34444,'3 - IMPORTANTE','11/12/1983','F','CENTRO','S�O BERNARDO DO CAMPO','SP',
'AV.BRIGADEIRO FARIA LIMA','870','09720010','MAYARA@HOTMAIL.COM','47824150364','MAYARA SOUZA');
INSERT INTO CLIENTE VALUES
(11111,'4 - MUITO IMPORTANTE','11/10/1984','F','VILA AMERICA','SANTO ANDR�','SP',
'AV.DOM PEDRO I','560','09110000','ISADORA.PINTO@GMAIL.COM','52465616820','ISADORA PINTO DA HORA');

INSERT INTO TELEFONECLI VALUES(0001129958732,12345);
INSERT INTO TELEFONECLI VALUES(0001143576742,23456);
INSERT INTO TELEFONECLI VALUES(0001143368733,34444);
INSERT INTO TELEFONECLI VALUES(0001169696969,11111);

INSERT INTO REDESOCIAL VALUES ('A',12345);
INSERT INTO REDESOCIAL VALUES('B',23456);
INSERT INTO REDESOCIAL VALUES('C',34444);
INSERT INTO REDESOCIAL VALUES('D',11111);

INSERT INTO DEPARTAMENTO VALUES(00010,'RH');
INSERT INTO DEPARTAMENTO VALUES(00020,'FINANCEIRO');
INSERT INTO DEPARTAMENTO VALUES(00030,'JURIDICO');
INSERT INTO DEPARTAMENTO VALUES(00040,'ADMINISTRA��O');
INSERT INTO DEPARTAMENTO VALUES(00050,'FINANCEIRO');
INSERT INTO DEPARTAMENTO VALUES(00060,'VENDAS');
INSERT INTO DEPARTAMENTO VALUES(00070,'OPERACIONAL');

INSERT INTO FUNCIONARIO VALUES
(00001,'BELA VISTA','S�O PAULO','SP',
'AV.PAULISTA','1230','01310100','386661017',00010,'M','08/12/1992','GABRIEL@HOTMAIL.COM','29848932712','GABRIEL');
INSERT INTO FUNCIONARIO VALUES
(00002,'RECANTO VERDE DO SOL','S�O PAULO','SP',
'RUA RICARDO VEIGA','710','08382422','197502234',00020,'M','05/05/1990','RENATO.ENRICO@HOTMAIL.COM','26387646049','RENATO ENRICO BARBOSA');
INSERT INTO FUNCIONARIO VALUES
(00003,'BELA VISTA','S�O PAULO','SP',
'AV.PAULISTA','1330','01310100','225929788',00030,'F','08/07/1990','RAFAELA.RODRIGUES@HOTMAIL.COM','28359925291','RAFAELA RAQUEL RODRIGUES');

INSERT INTO TELEFONEFUNC VALUES(11999958732,00001);
INSERT INTO TELEFONEFUNC VALUES(11993576742,00002);
INSERT INTO TELEFONEFUNC VALUES(11983368733,00003);

INSERT INTO USUARIO VALUES(00001,'1234567',1);
INSERT INTO USUARIO VALUES(00002,'12345678',3);
INSERT INTO USUARIO VALUES(00003,'12345670',5);

INSERT INTO PRODUTO VALUES(00001,'WHEY','SUPLEMENTO');
INSERT INTO PRODUTO VALUES(00002,'CREAFUEL','SUPLEMENTO');
INSERT INTO PRODUTO VALUES(00003,'WHEY BAR','BARRA');
INSERT INTO PRODUTO VALUES(00004,'SNACK BAR','BARRA DE PROTEINA');
INSERT INTO PRODUTO VALUES(00005,'SINEFLEX','SUPLEMENTO');

INSERT INTO SAC VALUES(1234567,00001,12345,'RECLAMACAO',00001);
INSERT INTO SAC VALUES(1234576,00002,23456,'SUGESTAO',00002);
INSERT INTO SAC VALUES(1234765,00003,34444,'ELOGIO',00005);
INSERT INTO SAC VALUES(1237654,00003,11111,'DUVIDA',00004);
INSERT INTO SAC VALUES(1237651,00003,11111,'DUVIDA',00003);

INSERT INTO ESTADOCHAMADA VALUES(1234567,00001,'ABERTA');
INSERT INTO ESTADOCHAMADA VALUES(1234576,00002,'FECHADA');
INSERT INTO ESTADOCHAMADA VALUES(1234765,00003,'ABERTA');
INSERT INTO ESTADOCHAMADA VALUES(1237654,00003,'FECHADA');
INSERT INTO ESTADOCHAMADA VALUES(1237651,00003,'FECHADA');

INSERT INTO AVALIACAOCHAMADA VALUES(1234567,12345,1);
INSERT INTO AVALIACAOCHAMADA VALUES(1234576,23456,2);
INSERT INTO AVALIACAOCHAMADA VALUES(1234765,34444,3);
INSERT INTO AVALIACAOCHAMADA VALUES(1237654,11111,4);
INSERT INTO AVALIACAOCHAMADA VALUES(1237651,11111,5);



INSERT INTO VENDA VALUES(00001,12345,00001,'01/01/2016','LOJA FISICA','CARTAO DE CREDITO');
INSERT INTO VENDA VALUES(00002,23456,00002,'02/02/2016','E-COMMERCE','BOLETO BANCARIO');
INSERT INTO VENDA VALUES(00003,34444,00003,'01/01/2016','E-COMMERCE','PAY PAL');
INSERT INTO VENDA VALUES(00004,11111,00004,'01/01/2016','LOJA FISICA','TEF');