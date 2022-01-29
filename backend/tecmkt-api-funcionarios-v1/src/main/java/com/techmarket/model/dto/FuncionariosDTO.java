package com.techmarket.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionariosDTO {
	
	@NotEmpty(message = "Campo nome é obrigatório")
	public String name;
	
	@CPF(message = "Campo CPF está inválido")
	@NotEmpty(message = "Campo CPF é obrigatório")
	private String document;
	
	@NotEmpty(message = "Campo data de nascimento é obrigatório")
	private String birthDate;
	
	@NotEmpty(message = "Campo data de contratação é obrigatório")
	private String hiringDate;
	
	@NotEmpty(message = "Campo Status é obrigatório")
	private String employeeStatus;
	
	@NotEmpty(message = "Campo cargo é obrigatório")
	private String position;
	
	@NotEmpty(message = "Campo setor é obrigatório")
	private String sector;
	
	@NotEmpty(message = "Campo tipo de acesso é obrigatório")
	private String typeOfAccess;

}
