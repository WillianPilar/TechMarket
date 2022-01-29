package com.techmarket.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {
	
	private String barCode;
	
	@NotNull(message = "Preço é obrigatório")
	private Double preco;
	
	@NotEmpty(message = "Data de validade é obrigatório")
	private String dtValidade;
	
	@NotEmpty(message = "Data e hora de fabricação é obrigatório")
	private String dtHrFabricacao;
	
	@NotNull(message = "Quantidade é obrigatório")
	private Double quantidade;
	
	private String nrLote;
	
	@NotNull(message = "Preço de compra é obrigatório")
	private Double precoDeCompra;
	
	private String notaFiscal;
	
	private String funciuonarioAtualizou;
	
	private String funcionarioRecebeu;

}
