package com.techmarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenAndCloseCaixaRequestDTO {
	
	public Long numeroDoCaixa;
	
	public String operador;
	
	public Double fundoDeTroco;

}
