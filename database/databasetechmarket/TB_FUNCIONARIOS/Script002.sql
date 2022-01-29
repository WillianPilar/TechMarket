DROP TABLE IF EXISTS tecmkt.tb_funcionarios;

CREATE TABLE tecmkt.tb_funcionarios(
	id					SERIAL		PRIMARY KEY,
	nome				VARCHAR		NOT NULL,
	documento			VARCHAR		NOT NULL,
	matricula			VARCHAR(6)	NOT NULL,
	dataDeNascimento	DATE		NOT NULL,
	dataDeContratacao	DATE		NOT NULL,
	funcionarioAtivo	VARCHAR		NOT NULL,
	cargo				VARCHAR		NOT NULL,
	setor				VARCHAR		NOT NULL,
	acessoAoSistema		VARCHAR		NOT NULL
);