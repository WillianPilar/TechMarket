package com.techmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techmarket.model.BarCode;

@Service
public interface BarCodeService {

	BarCode newBarCode(BarCode barcode);

	Optional<BarCode> getByBarCode(String barcode);

	List<BarCode> getAllBarCode();

	void updateBarcode(Optional<BarCode> barCode, String barcodeId);

	void deleteBarCode(String barcodeId);

}
