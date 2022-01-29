package com.techmarket.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techmarket.model.Funcionarios;
import com.techmarket.repository.TbFuncionariosRepository;
import com.techmarket.service.FuncionariosService;

@Service
public class FuncionariosServiceImpl implements FuncionariosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionariosServiceImpl.class); 
	
	@Autowired
	private TbFuncionariosRepository repository;
	
	@Override
	public Funcionarios saveEmploye(Funcionarios funcionario) throws SQLException, Exception {
		
		LOGGER.info("******** 2 - CRIAR FUNCIONÁRIO - Iniciado inserção dos dados na tabela");
		return repository.save(funcionario);
		
	}

	@Override
	public List<Funcionarios> getAll(PageRequest pageRequest) throws SQLException, Exception {

		LOGGER.info("******** 2 - PESQUISAR TODOS OS FUNCIONÁRIOS - Pesquisando todos os funcionários");
		Page<Funcionarios> pageFuncionarios = repository.findAll(pageRequest);
		
		List<Funcionarios> funcionarios = new ArrayList<>();
		pageFuncionarios.forEach( funcicionario -> funcionarios.add(funcicionario) );
		
		return funcionarios;
	}

	@Override
	public Optional<Funcionarios> getById(Long id) throws SQLException, Exception {
		
		LOGGER.info("******** 2 - PESQUISANDO FUNCIONÁRIO POR ID - Pesquisando");
		return repository.findById(id);
	
	}

	@Override
	public void update(Optional<Funcionarios> func) throws Exception{
		
		LOGGER.info("******** 3 - ATUALIZANDO FUNCIONÁRIO POR ID - Funcionário existe, atualizando os dados");
		Funcionarios funcionario = new Funcionarios(func.get().getId(),	func.get().getName(),
													func.get().getDocument(), func.get().getMatricula(), 
													func.get().getBirthDate(), func.get().getHiringDate(),
													func.get().getEmployeeStatus(), func.get().getPosition(),
													func.get().getSector(), func.get().getTypeOfAccess());
		
		repository.save(funcionario);
		
	}

	@Override
	public void delete(Long id) throws Exception{
		
		repository.deleteById(id);
		
	}

}
