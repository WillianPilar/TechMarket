package com.techmarket.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techmarket.model.Inventory;
import com.techmarket.model.dto.InventoryDTO;
import com.techmarket.service.InventoryService;
import com.techmarket.utils.DateUtils;

@RestController
@RequestMapping("/techmarket/estoque/api/v1")
@CrossOrigin("http://localhost:4200")
public class InventoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private InventoryService service;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Inventory novoProduto(@RequestBody @Valid InventoryDTO produto) throws ParseException {
		
		return service.newProduto(produto);
		
	}
	
	@GetMapping("/search-all")
	@ResponseStatus(HttpStatus.OK)
	public List<Inventory> getAll(){
		
		LOGGER.info("******** 1 - PESQUISANDO TODOS OS PRODUTOS NO ESTOQUE - Iniciando pesquisa");
		List<Inventory> inventory = new ArrayList<>();
		
		try {
			
			inventory = service.getAllInventory();
			LOGGER.info("******** 2 - PESQUISANDO TODOS OS PRODUTOS NO ESTOQUE - Pesquisa realizada com sucesso \n");
			
		} catch (Exception e) {

			LOGGER.error("Erro ao presquisar todos os produtos no estoque: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		
		return inventory;
	}
	
	@GetMapping("/search/{productId}")
	public ResponseEntity<Optional<Inventory>> getById(@PathVariable Long productId){
		
		LOGGER.info("******** 1 - PESQUISANDO PRODUTO POR ID - Iniciando pesquisa \n");
		Optional<Inventory> product = null;
		
		try {
			
			product = service.getById(productId);
			
			if (!product.isPresent()) {
				LOGGER.info("******** 2 - PESQUISANDO PRODUTO POR ID - Não encontrado \n");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			LOGGER.info("******** 2 - PESQUISANDO PRODUTO POR ID - Produto encontardo \n");
			
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar código de barras: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(product);
		
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody @Valid InventoryDTO produtoAtualizado){
		
		try {
			
			Optional<Inventory> product = service.getById(productId);
			
			if (product.isPresent()) {
				product.get().setBarCode(produtoAtualizado.getBarCode());
				product.get().setDtHrAtualizacao(DateUtils.dateNowTimestamp());
				product.get().setDtHrFabricacao(DateUtils.getDateAndHour(produtoAtualizado.getDtHrFabricacao()));
				product.get().setDtValidade(DateUtils.getDate(produtoAtualizado.getDtValidade()));
				product.get().setFuncionarioRecebeu(produtoAtualizado.getFuncionarioRecebeu());
				product.get().setFunciuonarioAtualizou(produtoAtualizado.getFunciuonarioAtualizou());
				product.get().setNotaFiscal(produtoAtualizado.getNotaFiscal());
				product.get().setNrLote(produtoAtualizado.getNrLote());
				product.get().setPrecoAtual(produtoAtualizado.getPreco());
				product.get().setPrecoDeCompra(produtoAtualizado.getPrecoDeCompra());
				product.get().setQuantidade(produtoAtualizado.getQuantidade());
				
				service.updateProduct(product);
			}else {

				LOGGER.error("******** 2 - ATUALIZANDO PRODUTO NO ESTOQUE - Erro ao atualizar, Não encontrado \n");
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			LOGGER.error("Erro ao atualizar produto no estoque: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
		
		try {
			Optional<Inventory> product = service.getById(productId);
			
			if (product.isPresent()) {
				service.delete(productId);
			}else {
				LOGGER.error("******** 2 - DELETANDO PRODUTO DO ESTOQUE - Produto não encontrado \n");
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			LOGGER.error("Erro ao deletar o produto: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok().build();
		
	}

}
