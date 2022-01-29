package com.techmarket.service;

import org.springframework.stereotype.Service;

import com.techmarket.model.Matricula;

@Service
public interface MatriculaService {

	Matricula getUltimaMatricula();

	void saveNewMatricula(String name, String matriculaGerada);

}
