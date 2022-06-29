CREATE DATABASE "desafio-itau"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
CREATE TABLE public.chaves_pix
(
    id uuid,
    tipo_chave character varying(9) NOT NULL,
    valor_chave character varying(77) NOT NULL,
    tipo_conta character varying(10) NOT NULL,
    num_agencia numeric(4) NOT NULL,
    num_conta numeric(8) NOT NULL,
    nome_correntista character varying(30) NOT NULL,
    sobrenome_correntista character varying(45),
    tipo_pessoa character varying(1) NOT NULL,
    data_criacao date NOT NULL,
    data_atualizacao date,
    data_inativacao date,
    PRIMARY KEY (id)
);

ALTER TABLE public.chaves_pix
    OWNER to postgres;
