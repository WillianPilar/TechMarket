package com.techmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.model.Caixa;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

}
