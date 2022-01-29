package com.techmarket.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techmarket.model.Inventory;
import com.techmarket.model.dto.InventoryDTO;

@Service
public interface InventoryService {

	Inventory newProduto(InventoryDTO produto) throws ParseException;

	List<Inventory> getAllInventory();

	Optional<Inventory> getById(Long id);

	void updateProduct(Optional<Inventory> product);

	void delete(Long productId);

}
