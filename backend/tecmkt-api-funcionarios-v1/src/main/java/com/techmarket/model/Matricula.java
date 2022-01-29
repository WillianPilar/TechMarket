package com.techmarket.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.techmarket.utils.DateUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "tecmkt", name = "tb_matriculas")
@Getter
@Setter
@NoArgsConstructor
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "matricula")
	private String matricula;

	@Column(name = "datadematricula")
	private Timestamp dataDeMatricula;
	
	public Matricula(String name2, String matriculaGerada) {
		this.name = name2;
		this.matricula = matriculaGerada;
		this.dataDeMatricula = DateUtils.dateNowTimestamp();
		
	}
}
