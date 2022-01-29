package com.techmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.model.BarCode;

@Repository
public interface BarCodeRepository extends JpaRepository<BarCode, String> {
	
}
