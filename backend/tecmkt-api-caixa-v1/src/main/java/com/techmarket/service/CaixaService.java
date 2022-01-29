package com.techmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techmarket.model.Caixa;
import com.techmarket.model.dto.NovoCaixaRequestDTO;
import com.techmarket.model.dto.OpenAndCloseCaixaRequestDTO;

@Service
public interface CaixaService {

	Caixa newCaixa(NovoCaixaRequestDTO caixa);

	Optional<Caixa> getCaixaByNumber(Long numeroDoCaixa);

	Caixa openCaixa(Optional<Caixa> caixaBd, OpenAndCloseCaixaRequestDTO caixa);

	Caixa closeCaixa(Optional<Caixa> caixaBd, OpenAndCloseCaixaRequestDTO caixa);

	List<Caixa> getAll();

	void update(Caixa caixaToUpdate);

	void deleteCaixa(Long number);

}
