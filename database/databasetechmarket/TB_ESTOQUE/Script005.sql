DROP TABLE IF EXISTS tecmkt.tb_estoque;

CREATE TABLE tecmkt.tb_estoque(
	id_produto					SERIAL		PRIMARY KEY,
	codbarras					VARCHAR		NOT NULL,
	dt_validade					DATE		NOT NULL,
	dt_hr_fabricacao			TIMESTAMP	NOT NULL,
	dt_hr_recebimento			TIMESTAMP	NOT NULL,
	quantidade					BIGINT		NOT NULL,
	nr_lote						VARCHAR				,	
	preco_atual					NUMERIC		NOT NULL,
	preco_de_compra				NUMERIC		NOT NULL,
	preco_de_venda_inicial		NUMERIC		NOT NULL,
	nota_fiscal_recebimento		VARCHAR				,
	dt_hr_atualizacao			TIMESTAMP			,
	funcionario_atualizou		VARCHAR				,
	funcionario_recebeu			VARCHAR		NOT NULL,
	CONSTRAINT fk_codbarras FOREIGN KEY (codbarras) REFERENCES tecmkt.tb_cod_barras (codbarras)
	
	
	
);