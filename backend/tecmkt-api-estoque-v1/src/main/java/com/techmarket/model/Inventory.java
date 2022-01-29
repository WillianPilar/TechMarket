package com.techmarket.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "tecmkt", name = "tb_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long productId;
	
	@Column(name = "codbarras")
	private String barCode;
		
	@Column(name = "dt_validade")
	private Date dtValidade;
	
	@Column(name = "dt_hr_fabricacao")
	private Timestamp dtHrFabricacao;
	
	@Column(name = "dt_hr_recebimento")
	private Timestamp dtHrRecebimento;
	
	@Column(name = "quantidade")
	private Double quantidade;
	
	@Column(name = "nr_lote")
	private String nrLote;
	
	@Column(name = "preco_atual")
	private Double precoAtual;
	
	@Column(name = "preco_de_compra")
	private Double precoDeCompra;
	
	@Column(name = "preco_de_venda_inicial")
	private Double precoDeVendaInicial;
	
	@Column(name = "nota_fiscal_recebimento")
	private String notaFiscal;
	
	@Column(name = "dt_hr_atualizacao")
	private Timestamp dtHrAtualizacao;
	
	@Column(name = "funcionario_atualizou")
	private String funciuonarioAtualizou;
	
	@Column(name = "funcionario_recebeu")
	private String funcionarioRecebeu;
	

}
