CREATE DATABASE desafio-itau
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
CREATE TABLE "public.chaves_pix" (
	"id" serial NOT NULL,
	"tipo_chave" serial(9) NOT NULL,
	"valor_chave" serial(77) NOT NULL,
	"tipo_conta" serial(10) NOT NULL,
	"num_agencia" serial(4) NOT NULL,
	"num_conta" serial(8) NOT NULL,
	"nome_correntista" serial(30) NOT NULL,
	"sobrenome_correntista" serial(45),
	"tipo_pessoa" serial(1) NOT NULL,
	"data_criacao" serial NOT NULL,
	"data_atualizacao" serial NOT NULL,
	CONSTRAINT "chaves_pix_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);
