DROP TABLE IF EXISTS tecmkt.tb_matriculas;

CREATE TABLE tecmkt.tb_matriculas(
	id					SERIAL		PRIMARY KEY,
	nome				VARCHAR		NOT NULL,
	matricula			VARCHAR		NOT NULL,
	dataDeMatricula		TIMESTAMP	NOT NULL
);