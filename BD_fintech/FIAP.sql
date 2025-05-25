--Cria��o tabelas


CREATE TABLE t_usuario (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR2(50) DEFAULT NULL,
    e_mail VARCHAR2(50) DEFAULT NULL,
    user_name VARCHAR2(50) DEFAULT NULL,
    senha VARCHAR2(50) DEFAULT NULL,
    genero varchar (20) DEFAULT NULL,
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




CREATE TABLE t_transacao (
     id_transacao INTEGER GENERATED ALWAYS AS IDENTITY,
     id_usuario INTEGER NOT NULL,
     tipo_transacao VARCHAR2 (50) NOT NULL,
     descricao_transacao VARCHAR2 (50) NOT NULL,
     categoria_transacao VARCHAR2 (50) NOT NULL,
     valor_transacao NUMBER(15,2) NOT NULL,
     data_transacao DATE NOT NULL
);
ALTER TABLE t_transacao
    ADD CONSTRAINT PK_ID_TRANSACAO
        PRIMARY KEY (ID_TRANSACAO);
ALTER TABLE t_transacao
    ADD CONSTRAINT FK_ID_USUARIO_TRANSACAO_USUARIO
        FOREIGN KEY (id_usuario)
            REFERENCES t_usuario (id_usuario);






---------------------------------------------



insert into t_usuario (user_name, senha) values ('admin', 'admin');
        
SELECT * FROM t_usuario WHERE USER_NAME = 'admin' AND SENHA = 'admin';


drop table t_investimento CASCADE CONSTRAINTS;
drop table t_meta_financeira CASCADE CONSTRAINTS;
drop table t_transacao CASCADE CONSTRAINTS;
drop table t_usuario CASCADE CONSTRAINTS;