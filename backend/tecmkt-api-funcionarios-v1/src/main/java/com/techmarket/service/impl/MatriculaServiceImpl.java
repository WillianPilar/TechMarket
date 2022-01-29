package com.techmarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.model.Matricula;
import com.techmarket.repository.TbMatriculasRepository;
import com.techmarket.service.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService{
	
	@Autowired
	private TbMatriculasRepository repository;

	@Override
	public Matricula getUltimaMatricula() {
		
		return repository.getUltimaMatricula();
	}

	@Override
	public void saveNewMatricula(String name, String matriculaGerada) {
		
		Matricula newMatricula = new Matricula(name, matriculaGerada);
		repository.save(newMatricula);
		
	}

}
