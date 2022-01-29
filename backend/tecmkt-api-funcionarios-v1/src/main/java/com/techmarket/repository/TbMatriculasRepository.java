package com.techmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techmarket.model.Matricula;

@Repository
public interface TbMatriculasRepository extends JpaRepository<Matricula, Long>{

	@Query(value = "select * from tecmkt.tb_matriculas where datadematricula = (select max(datadematricula) from tecmkt.tb_matriculas)", nativeQuery = true)
	Matricula getUltimaMatricula();

}
