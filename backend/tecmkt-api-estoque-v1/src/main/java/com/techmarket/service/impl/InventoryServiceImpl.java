package com.techmarket.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.model.Inventory;
import com.techmarket.model.dto.InventoryDTO;
import com.techmarket.repository.InventoryRepository;
import com.techmarket.service.InventoryService;
import com.techmarket.utils.DateUtils;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Override
	public Inventory newProduto(InventoryDTO produto) throws ParseException {

		Inventory novoProduto = new Inventory();
		novoProduto.setBarCode(produto.getBarCode());
		novoProduto.setPrecoAtual(produto.getPreco());
		novoProduto.setPrecoDeVendaInicial(produto.getPreco());
		novoProduto.setFuncionarioRecebeu(produto.getFuncionarioRecebeu());
		;
		novoProduto.setNotaFiscal(produto.getNotaFiscal());
		novoProduto.setNrLote(produto.getNrLote());
		novoProduto.setPrecoDeCompra(produto.getPrecoDeCompra());
		novoProduto.setQuantidade(produto.getQuantidade());
		novoProduto.setDtHrFabricacao(DateUtils.getDateAndHour(produto.getDtHrFabricacao()));
		novoProduto.setDtHrRecebimento(DateUtils.dateNowTimestamp());
		novoProduto.setDtValidade(DateUtils.getDate(produto.getDtValidade()));

		repository.save(novoProduto);

		return novoProduto;
	}

	@Override
	public List<Inventory> getAllInventory() {

		return repository.findAll();

	}

	@Override
	public Optional<Inventory> getById(Long id) {

		return repository.findById(id);

	}

	@Override
	public void updateProduct(Optional<Inventory> product) {

		Inventory productSave = new Inventory(product.get().getProductId(), product.get().getBarCode(),
				product.get().getDtValidade(), product.get().getDtHrFabricacao(), product.get().getDtHrRecebimento(),
				product.get().getQuantidade(), product.get().getNrLote(), product.get().getPrecoAtual(),
				product.get().getPrecoDeCompra(), product.get().getPrecoDeVendaInicial(), product.get().getNotaFiscal(),
				product.get().getDtHrAtualizacao(), product.get().getFunciuonarioAtualizou(),
				product.get().getFuncionarioRecebeu());

		repository.save(productSave);

	}

	@Override
	public void delete(Long productId) {
		
		repository.deleteById(productId);
		
	}

}
