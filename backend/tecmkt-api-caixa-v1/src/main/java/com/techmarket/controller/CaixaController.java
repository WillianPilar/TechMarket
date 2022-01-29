package com.techmarket.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techmarket.controller.exceptions.ApiValidationErrorsReturn;
import com.techmarket.model.Caixa;
import com.techmarket.model.dto.NovoCaixaRequestDTO;
import com.techmarket.model.dto.OpenAndCloseCaixaRequestDTO;
import com.techmarket.model.dto.updateCaixaRequestDTO;
import com.techmarket.service.CaixaService;

@RestController
@RequestMapping("/techmarket/caixa/api/v1")
@CrossOrigin("http://localhost:4200")
public class CaixaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaixaController.class);
	
	@Autowired
	private CaixaService service;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Caixa> createNewCaixa(@RequestBody NovoCaixaRequestDTO caixa) {
		
		Caixa caixaReturn = null;
		try {
			Optional<Caixa> caixaDB = service.getCaixaByNumber(caixa.getNumeroDoCaixa());
			if (caixaDB.isEmpty()) {
				caixaReturn = service.newCaixa(caixa);
			} else {
				return ResponseEntity.badRequest().build();
			}
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(caixaReturn);
		
	}
	
	@PatchMapping("/open")
	public ResponseEntity<?> openCaixa(@RequestBody OpenAndCloseCaixaRequestDTO caixa) {
		
		Caixa caixaReturn;
		Optional<Caixa> caixaBd = service.getCaixaByNumber(caixa.numeroDoCaixa);
		if (caixaBd.isPresent() && caixaBd.get().getStatus().equalsIgnoreCase("F")) {
			
			caixaReturn = service.openCaixa(caixaBd, caixa);
			
		}else if (!caixaBd.get().getStatus().equalsIgnoreCase("F")) {
			
			return ResponseEntity.badRequest().body(new ApiValidationErrorsReturn("Caixa não está fechado"));
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(caixaReturn);
		
	}
	
	@PatchMapping("/close")
	public ResponseEntity<?> closeCaixa(@RequestBody OpenAndCloseCaixaRequestDTO caixa){
		
		Caixa caixaReturn;
		Optional<Caixa> caixaBd = service.getCaixaByNumber(caixa.numeroDoCaixa);
		if (caixaBd.isPresent() && caixaBd.get().getStatus().equalsIgnoreCase("A")) {
			
			caixaReturn = service.closeCaixa(caixaBd, caixa);
			
		}else if (!caixaBd.get().getStatus().equalsIgnoreCase("A")) {
			
			return ResponseEntity.badRequest().body(new ApiValidationErrorsReturn("Caixa não está aberto"));
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(caixaReturn);
		
	}
	
	@GetMapping("/search/{numero}")
	public ResponseEntity<Caixa> searchCaixaByNumber(@PathVariable("numero") Long numero){
		
		Caixa caixaReturn = null;
		try {
			
			Optional<Caixa> caixa = service.getCaixaByNumber(numero);
			if (!caixa.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			caixaReturn = new Caixa(caixa);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(caixaReturn);
		
	}
	
	@GetMapping("/search-all")
	@ResponseStatus(HttpStatus.OK)
	public List<Caixa> searchAllCaixas(){
		
		List<Caixa> caixaList = null;
		try {
			caixaList = service.getAll();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return caixaList;
	
	}
	
	@PutMapping("/update/{numero}")
	public ResponseEntity<?> updateCaixa(@RequestBody updateCaixaRequestDTO caixaUpdate, @PathVariable("numero") Long number){
		
		try {
			
			Optional<Caixa> caixa = service.getCaixaByNumber(number);
			
			if (caixa.isPresent()) {
				
				Caixa caixaToUpdate = new Caixa(caixa);
				caixaToUpdate.setFundoDeTroco(caixaUpdate.getFundoDeTroco());
				caixaToUpdate.setStatus(caixaUpdate.getStatus());
				
				service.update(caixaToUpdate);
				
			}else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete/{numero}")
	public ResponseEntity<?> deleteCaixa(@PathVariable("numero")Long number){
		
		try {
			
			Optional<Caixa> caixa = service.getCaixaByNumber(number);
			if (caixa.isPresent()) {
				
				service.deleteCaixa(number);
				
			}else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok().build();
	}

}