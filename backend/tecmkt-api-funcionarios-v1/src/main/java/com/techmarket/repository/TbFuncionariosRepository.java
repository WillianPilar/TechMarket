package com.techmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.model.Funcionarios;

@Repository
public interface TbFuncionariosRepository extends JpaRepository<Funcionarios, Long> {

}
