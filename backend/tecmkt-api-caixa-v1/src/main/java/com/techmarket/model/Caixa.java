package com.techmarket.model;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "tecmkt", name = "tb_caixa")
@Getter
@Setter
@NoArgsConstructor
public class Caixa {
	
	@Id
	@Column(name = "numero")
	private Long numeroDoCaixa;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "dt_hr_ultima_abertura")
	private Timestamp dtHrAbertura;
	
	@Column(name = "dt_hr_ultimo_fechamento")
	private Timestamp dtHrFechamento;
	
	@Column(name = "operador_ativo")
	private String operador;
	
	@Column(name = "fundo_de_troco_atual")
	private Double fundoDeTroco; 
	
	public Caixa(Optional<Caixa> caixaBd) {

		this.dtHrAbertura = caixaBd.get().getDtHrAbertura();
		this.dtHrFechamento = caixaBd.get().getDtHrFechamento();
		this.fundoDeTroco = caixaBd.get().getFundoDeTroco();
		this.numeroDoCaixa = caixaBd.get().getNumeroDoCaixa();
		this.operador = caixaBd.get().getOperador();
		this.status = caixaBd.get().getStatus();
		
	}

}
