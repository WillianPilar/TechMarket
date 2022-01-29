package com.techmarket.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techmarket.model.dto.FuncionariosDTO;
import com.techmarket.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "tecmkt", name = "tb_funcionarios")
public class Funcionarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "documento")
	private String document;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "datadenascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;
	
	@Column(name = "datadecontratacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date hiringDate;
	
	@Column(name = "funcionarioativo")
	private String employeeStatus;
	
	@Column(name = "cargo")
	private String position;
	
	@Column(name = "setor")
	private String sector;
	
	@Column(name = "acessoaosistema")
	private String typeOfAccess;
	
	public Funcionarios(FuncionariosDTO funcionario, String matriculaGerada) throws ParseException {
		this.name = funcionario.getName();
		this.document = funcionario.getDocument();
		this.matricula = matriculaGerada;
		this.birthDate = DateUtils.getDate(funcionario.getBirthDate());
		this.hiringDate = DateUtils.getDate(funcionario.getHiringDate());
		this.employeeStatus = funcionario.getEmployeeStatus();
		this.position = funcionario.getPosition();
		this.sector = funcionario.getSector();
		this.typeOfAccess = funcionario.getTypeOfAccess();
	}

}
