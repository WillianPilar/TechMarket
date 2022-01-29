DROP TABLE IF EXISTS tecmkt.tb_cod_barras;

CREATE TABLE tecmkt.tb_cod_barras(
	codbarras		VARCHAR		PRIMARY KEY,
	nome			VARCHAR		NOT NULL,
	preco			NUMERIC		NOT NULL,
	marca			VARCHAR		NOT NULL,
	categoria		VARCHAR		NOT NULL,
	setor			VARCHAR		NOT NULL,
	peso			NUMERIC				,
	tipodepeso		VARCHAR				,
	perecivel		BOOLEAN		NOT NULL,
	armazenamento	VARCHAR		NOT NULL
	
);