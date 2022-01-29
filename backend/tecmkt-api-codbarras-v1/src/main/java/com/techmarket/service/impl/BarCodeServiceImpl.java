package com.techmarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.model.BarCode;
import com.techmarket.repository.BarCodeRepository;
import com.techmarket.service.BarCodeService;

@Service
public class BarCodeServiceImpl implements BarCodeService{

	@Autowired
	private BarCodeRepository repository;
	
	@Override
	public BarCode newBarCode(BarCode barcode) {
		return repository.save(barcode);
	}

	@Override
	public Optional<BarCode> getByBarCode(String barcode) {
		return repository.findById(barcode);
	}

	@Override
	public List<BarCode> getAllBarCode() {
		return repository.findAll();
	}

	@Override
	public void updateBarcode(Optional<BarCode> barCode, String barcodeId) {
		
		BarCode barCodeUpdated = new BarCode(barcodeId, barCode.get().getNome(), barCode.get().getPreco(),
				barCode.get().getMarca(), barCode.get().getCategoria(),
				barCode.get().getSetor(), barCode.get().getPeso(),
				barCode.get().getTipoDePeso(), barCode.get().isPerecivel(),
				barCode.get().getArmazenamento());
		
		repository.save(barCodeUpdated);
		
	}

	@Override
	public void deleteBarCode(String barcodeId) {
		
		repository.deleteById(barcodeId);
		
	}

}
