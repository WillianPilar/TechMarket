package com.techmarket.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techmarket.model.Funcionarios;

@Service
public interface FuncionariosService {

	public Funcionarios saveEmploye(Funcionarios funcionarioSave) throws SQLException, Exception;

	public List<Funcionarios> getAll(PageRequest pageRequest) throws SQLException, Exception;

	public Optional<Funcionarios> getById(Long id) throws SQLException, Exception;

	public void update(Optional<Funcionarios> funcionario) throws Exception;

	public void delete(Long id) throws Exception;
	
}
