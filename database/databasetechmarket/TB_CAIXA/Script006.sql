DROP TABLE IF EXISTS tecmkt.tb_caixa;

CREATE TABLE tecmkt.tb_caixa(
	numero						BIGINT		PRIMARY KEY,
	status						VARCHAR		NOT NULL,
	dt_hr_ultima_abertura		TIMESTAMP	,
	dt_hr_ultimo_fechamento		TIMESTAMP	,
	operador_ativo				VARCHAR		,
	fundo_de_troco_atual		NUMERIC		
		
);