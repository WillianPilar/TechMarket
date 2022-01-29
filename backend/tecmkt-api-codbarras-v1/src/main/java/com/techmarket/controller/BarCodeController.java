package com.techmarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techmarket.model.BarCode;
import com.techmarket.service.BarCodeService;

@RestController
@RequestMapping("/techmarket/barcode/api/v1")
@CrossOrigin("http://localhost:4200")
public class BarCodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BarCodeController.class);

	@Autowired
	private BarCodeService service;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public BarCode createBarCode(@RequestBody @Valid BarCode barcode) {

		LOGGER.info("******** 1 - CRIANDO NOVO CÓDIGO DE BARRAS - Iniciando \n");
		return service.newBarCode(barcode);

	}

	@GetMapping("/search/{barcode}")
	public ResponseEntity<Optional<BarCode>> searchByBarCode(@PathVariable String barcode) {

		LOGGER.info("******** 1 - PESQUISANDO CÓDIGO DE BARRAS - Iniciando pesquisa");
		Optional<BarCode> barcodeReturn = null;

		try {

			barcodeReturn = service.getByBarCode(barcode);

			if (!barcodeReturn.isPresent()) {
				LOGGER.info("******** 2 - PESQUISANDO CÓDIGO DE BARRAS - Não encontrado \n");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			LOGGER.info("******** 2 - PESQUISANDO CÓDIGO DE BARRAS - Código de barras encontrado \n");

		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar código de barras: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(barcodeReturn);
	}

	@GetMapping("/search-all")
	@ResponseStatus(HttpStatus.OK)
	public List<BarCode> searchAllBarCode() {

		LOGGER.info("******** 1 - PESQUISANDO TODOS OS CÓDIGOS DE BARRAS - Iniciando pesquisa");
		List<BarCode> listBarCode = new ArrayList<>();

		try {

			listBarCode = service.getAllBarCode();
			LOGGER.info("******** 2 - PESQUISANDO TODOS OS CÓDIGOS DE BARRAS - Pesquisa realizada com sucesso \n");

		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar todos os códigos de barras: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return listBarCode;

	}

	@PutMapping("/update/{barcodeId}")
	public ResponseEntity<?> updateBarCode(@RequestBody @Valid BarCode barcodeRequest, @PathVariable String barcodeId) {

		LOGGER.info("******** 1 - ATUALIZANDO CÓDIGO DE BARRAS - Iniciando atualização");

		try {

			Optional<BarCode> barCode = service.getByBarCode(barcodeId);

			if (barCode.isPresent()) {

				barCode.get().setNome(barcodeRequest.getNome());
				barCode.get().setPreco(barcodeRequest.getPreco());
				barCode.get().setMarca(barcodeRequest.getMarca());
				barCode.get().setCategoria(barcodeRequest.getCategoria());
				barCode.get().setSetor(barcodeRequest.getSetor());
				barCode.get().setPeso(barcodeRequest.getPeso());
				barCode.get().setTipoDePeso(barcodeRequest.getTipoDePeso());
				barCode.get().setPerecivel(barcodeRequest.isPerecivel());
				barCode.get().setArmazenamento(barcodeRequest.getArmazenamento());

				service.updateBarcode(barCode, barcodeId);
				LOGGER.info("******** 2 - ATUALIZANDO CÓDIGO DE BARRAS - Atualização efetuada com sucesso \n");

			} else {

				LOGGER.error("******** 2 - ATUALIZANDO CÓDIGO DE BARRAS - Erro ao atualizar, Não encontrado \n");
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			LOGGER.error("Erro ao atualizar código de barras: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/delete/{barcodeId}")
	public ResponseEntity<?> deleteBarCode(@PathVariable String barcodeId){
		
		try {
			LOGGER.info("******** 1 - DELETANDO CÓDIGO DE BARRAS - Iniciando a exclusão do código de barras");
			Optional<BarCode> barCode = service.getByBarCode(barcodeId);
			
			if (barCode.isPresent()) {
				
				service.deleteBarCode(barcodeId);
				LOGGER.info("******** 2 - DELETANDO CÓDIGO DE BARRAS - Código de barras deletado \n");
				
			} else {
				
				LOGGER.error("******** 2 - DELETANDO CÓDIGO DE BARRAS - Código de barras não encontrado \n");
				return ResponseEntity.notFound().build();
				
			}
			
		} catch (Exception e) {
			LOGGER.error("Erro ao deletar código de barras: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok().build();
		
	}

}
