package com.techmarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techmarket.model.Funcionarios;
import com.techmarket.model.Matricula;
import com.techmarket.model.dto.FuncionariosDTO;
import com.techmarket.service.FuncionariosService;
import com.techmarket.service.MatriculaService;
import com.techmarket.utils.DateUtils;
import com.techmarket.utils.GenerateEnrollmentUtils;

@RestController
@RequestMapping("/techmarket/employees/api/v1")
@CrossOrigin("http://localhost:4200")
public class FuncionariosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionariosController.class);

	@Autowired
	private FuncionariosService funcionariosService;
	
	@Autowired
	private MatriculaService matriculaService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionarios createEmployees(@RequestBody @Valid FuncionariosDTO funcionario) {

		LOGGER.info("******** 1 - CRIAR FUNCIONÁRIO - Iniciado a identificação dos dados e formatação");
		Funcionarios funcionarioReturn = new Funcionarios();

		try {
			
			Matricula ultimaMatricula = matriculaService.getUltimaMatricula();
			String matriculaGerada = GenerateEnrollmentUtils.generateEnrollment(ultimaMatricula);
			
			Funcionarios funcionarioSave = new Funcionarios(funcionario, matriculaGerada);
			funcionarioReturn = funcionariosService.saveEmploye(funcionarioSave);
			LOGGER.info("******** 3 - CRIAR FUNCIONÁRIO - Funcionário salvo! \n");
			
			matriculaService.saveNewMatricula(funcionarioSave.getName(), matriculaGerada);

		} catch (Exception e) {
			LOGGER.error("Erro ao salvar o funcionário: ", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return funcionarioReturn;
	}

	@GetMapping("/search-all")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionarios> searchAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {

		LOGGER.info("******** 1 - PESQUISAR TODOS OS FUNCIONÁRIOS - Iniciando pesquisa");
		List<Funcionarios> allEmployees = new ArrayList<>();

		try {

			allEmployees = funcionariosService.getAll(PageRequest.of(page, size));
			LOGGER.info(
					"******** 3 - PESQUISAR TODOS OS FUNCIONÁRIOS - Pesquisa de todos os funcionários realizada com sucesso \n");

		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar todos os funcionários: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return allEmployees;

	}

	@GetMapping("search/{id}")
	public ResponseEntity<Optional<Funcionarios>> searchById(@PathVariable("id") Long id) {

		LOGGER.info("******** 1 - PESQUISANDO FUNCIONÁRIO POR ID - Iniciando pesquisa");
		Optional<Funcionarios> funcionario = null;

		try {

			funcionario = funcionariosService.getById(id);

			if (!funcionario.isPresent()) {
				LOGGER.info("******** 3 - PESQUISANDO FUNCIONÁRIO POR ID - Funcionário não encontrado \n");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			LOGGER.info("******** 3 - PESQUISANDO FUNCIONÁRIO POR ID - Pesquisa realizada com sucesso \n");

		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar funcionário pelo id: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(funcionario);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id,
			@RequestBody @Valid FuncionariosDTO funcionarioUpdate) {

		LOGGER.info("******** 1 - ATUALIZANDO FUNCIONÁRIO POR ID - Iniciando atualização do funcionário");

		try {

			Optional<Funcionarios> funcionario = funcionariosService.getById(id);
			if (funcionario.isPresent()) {

				funcionario.get().setName(funcionarioUpdate.getName());
				funcionario.get().setTypeOfAccess(funcionarioUpdate.getTypeOfAccess());
				funcionario.get().setPosition(funcionarioUpdate.getPosition());
				funcionario.get().setHiringDate(DateUtils.getDate(funcionarioUpdate.getHiringDate()));
				funcionario.get().setBirthDate(DateUtils.getDate(funcionarioUpdate.getBirthDate()));
				funcionario.get().setDocument(funcionarioUpdate.getDocument());
				funcionario.get().setEmployeeStatus(funcionarioUpdate.getEmployeeStatus());
				funcionario.get().setSector(funcionarioUpdate.getSector());

				funcionariosService.update(funcionario);
				LOGGER.info("******** 4 - ATUALIZANDO FUNCIONÁRIO POR ID - Funcionário atualizado com sucesso \n");

			} else {
				LOGGER.error(
						"******** 3 - ATUALIZANDO FUNCIONÁRIO POR ID - Erro ao atualizar, funcionário não encontrado \n");
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			LOGGER.error("Erro ao atualizar funcionário pelo id: ", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {

		LOGGER.info("******** 1 - DELETANDO FUNCIONÁRIO POR ID - Iniciando a exclusão do funcionário");

		try {

			Optional<Funcionarios> funcionario = funcionariosService.getById(id);
			if (funcionario.isPresent()) {
				
				funcionariosService.delete(id);
				LOGGER.info("******** 3 - DELETANDO FUNCIONÁRIO POR ID - Funcionário deletado \n");
				
			} else {
				
				LOGGER.info("******** 3 - DELETANDO FUNCIONÁRIO POR ID - Erro ao deletar. Funcionário não encontrado \n");
				return ResponseEntity.notFound().build();
				
			}

		} catch (Exception e) {
			LOGGER.error("Erro ao deletar funcionário pelo id: ", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok().build();
	}
	
}
