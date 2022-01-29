package com.techmarket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "tecmkt", name = "tb_cod_barras")
public class BarCode {
	
	@Id
	@Column(name = "codbarras")
	@JsonProperty("barCode")
	@NotEmpty(message = "Código barras é obrigatório")
	private String codBarras;
	
	@Column(name = "nome")
	@JsonProperty("name")
	@NotEmpty(message = "Nome do produto é obigatório")
	private String nome;
	
	@Column(name = "preco")
	@JsonProperty("price")
	@NotNull(message = "Preço do produto é obigatório")
	private Double preco;
	
	@Column(name = "marca")
	@JsonProperty("brand")
	@NotEmpty(message = "Marca do produto é obrigatório")
	private String marca;
	
	@Column(name = "categoria")
	@JsonProperty("category")
	@NotEmpty(message = "Categotia do produto é obrigatório")
	private String categoria;
	
	@Column(name = "setor")
	@JsonProperty("sector")
	@NotEmpty(message = "Setor do produto é obrigatório")
	private String setor;

	@Column(name = "peso")
	@JsonProperty("weight")
	private double peso;
	
	@Column(name = "tipodepeso")
	@JsonProperty("weightType")
	private String tipoDePeso;
	
	@Column(name = "perecivel")
	@JsonProperty("perishable")
	@NotNull(message = "Produto perecível é obrigatório")
	private boolean perecivel;
	
	@Column(name = "armazenamento")
	@JsonProperty("typeOfStorage")
	@NotEmpty(message = "Tipo de armazenamento do produto é obrigatório")
	private String armazenamento;
	
	
}
