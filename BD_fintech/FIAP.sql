--Cria��o tabelas


CREATE TABLE t_usuario (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR2(50) DEFAULT NULL,
    e_mail VARCHAR2(50) DEFAULT NULL,
    user_name VARCHAR2(50) DEFAULT NULL,
    senha VARCHAR2(50) DEFAULT NULL,
    cpf VARCHAR2(14) DEFAULT NULL,
    telefone VARCHAR2 (50) DEFAULT NULL,
    data_nascimento DATE DEFAULT NULL
);

ALTER TABLE t_usuario
    ADD CONSTRAINT PK_ID_USUARIO
        PRIMARY KEY (id_usuario);

ALTER TABLE t_usuario
    ADD CONSTRAINT UN_USER_NAME
        UNIQUE (USER_NAME);

ALTER TABLE t_usuario
    ADD CONSTRAINT UN_CPF
        UNIQUE (CPF);
        
        ALTER TABLE t_usuario
    ADD CONSTRAINT UN_EMAIL
        UNIQUE (e_mail);


CREATE TABLE t_conta (
    id_conta             INTEGER GENERATED ALWAYS AS IDENTITY,
    id_usuario 		     INTEGER NOT NULL,
    numero_conta         INTEGER NOT NULL,
    agencia              INTEGER NOT NULL,
    banco                INTEGER NOT NULL,
    tipo_conta           VARCHAR2(10) NOT NULL,
    saldo_conta          NUMBER(15, 2) NOT NULL
);
ALTER TABLE t_conta
    ADD CONSTRAINT PK_ID_CONTA
        PRIMARY KEY (id_conta);
ALTER TABLE t_conta
    ADD CONSTRAINT FK_t_CONTA_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario)
        ;
        
     
CREATE TABLE t_endereco (
    id_endereco INTEGER NOT NULL,
    id_usuario 		     INTEGER NOT NULL,
    cep                  VARCHAR2(9) NOT NULL,
    logradouro           VARCHAR2(50) NOT NULL,
    numero               INTEGER NOT NULL,
    complemento          VARCHAR2(10),
    bairro               VARCHAR2(50) NOT NULL,
    cidade               VARCHAR2(50) NOT NULL,
    estado               VARCHAR2(2) NOT NULL
);
ALTER TABLE t_endereco 
    ADD CONSTRAINT PK_id_endereco
        PRIMARY KEY ( id_endereco );
ALTER TABLE t_endereco 
    ADD CONSTRAINT FK_ID_USUARIO_ENDERECO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario);




CREATE TABLE t_investimento (
    id_investimento     INTEGER GENERATED ALWAYS AS IDENTITY,
    id_usuario          INTEGER NOT NULL,
    nome_investimento   VARCHAR2 (50) NOT NULL,
    data_investimento   DATE NOT NULL,
    data_resgate        DATE,
    valor_investido     NUMBER(15, 2) NOT NULL,
    "RENDIMENTO_EM(%)"  NUMBER(6,2) NOT NULL,
    tipo_rendimento     VARCHAR2 (50) NOT NULL,
    valor_rendido       NUMBER(15, 2) NOT NULL
);
ALTER TABLE t_investimento
    ADD CONSTRAINT PK_ID_INVESTIMENTO 
        PRIMARY KEY (id_investimento);
ALTER TABLE t_investimento
    ADD CONSTRAINT FK_ID_USUARIO_INVESTIMENTO_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario);



CREATE TABLE t_meta_financeira (
    id_meta_financeira INTEGER GENERATED ALWAYS AS IDENTITY,
    id_usuario INTEGER NOT NULL,
    nome_meta VARCHAR2 (50) NOT NULL,
    valor_meta NUMBER(15,2) NOT NULL,
    data_meta DATE NOT NULL
);
ALTER TABLE t_meta_financeira
    ADD CONSTRAINT PK_ID_META_FINANCEIRA 
        PRIMARY KEY (ID_META_FINANCEIRA);
ALTER TABLE t_meta_financeira
    ADD CONSTRAINT FK_ID_USUARIO_META_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario);



---------------------------------------------



CREATE TABLE t_despesa (
    id_despesa INTEGER GENERATED ALWAYS AS IDENTITY,
    id_usuario INTEGER NOT NULL,
    nome_despesa VARCHAR2 (50) NOT NULL,
    valor_despesa NUMBER(15,2) NOT NULL,
    data_despesa DATE NOT NULL
);
ALTER TABLE t_despesa
    ADD CONSTRAINT PK_ID_DESPESA 
        PRIMARY KEY (ID_DESPESA);
ALTER TABLE t_despesa
    ADD CONSTRAINT FK_ID_USUARIO_DESPESA_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario);



CREATE TABLE t_receita (
    id_receita INTEGER GENERATED ALWAYS AS IDENTITY,
    id_usuario INTEGER NOT NULL,
    nome_receita VARCHAR2 (50) NOT NULL,
    valor_receita NUMBER(15,2) NOT NULL,
    data_receita DATE NOT NULL
);
ALTER TABLE t_receita
    ADD CONSTRAINT PK_ID_receita 
        PRIMARY KEY (ID_receita);
ALTER TABLE t_receita
    ADD CONSTRAINT FK_ID_USUARIO_receita_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES t_usuario (id_usuario);
        
        

insert into t_usuario (user_name, senha) values ('admin', 'admin');
        
        
drop table T_CONTA CASCADE CONSTRAINTS;
drop table t_despesa CASCADE CONSTRAINTS;
drop table t_endereco CASCADE CONSTRAINTS;
drop table t_investimento CASCADE CONSTRAINTS;
drop table t_meta_financeira CASCADE CONSTRAINTS;
drop table t_receita CASCADE CONSTRAINTS;
drop table t_usuario CASCADE CONSTRAINTS;