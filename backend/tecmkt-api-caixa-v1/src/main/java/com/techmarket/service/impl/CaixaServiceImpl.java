package com.techmarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.model.Caixa;
import com.techmarket.model.dto.NovoCaixaRequestDTO;
import com.techmarket.model.dto.OpenAndCloseCaixaRequestDTO;
import com.techmarket.repository.CaixaRepository;
import com.techmarket.service.CaixaService;
import com.techmarket.utils.DateUtils;

@Service
public class CaixaServiceImpl implements CaixaService {

	@Autowired
	private CaixaRepository repository;
	
	private static final String ABERTO = "A";
	private static final String FECHADO = "F";
	
	@Override
	public Caixa newCaixa(NovoCaixaRequestDTO caixa) {

		Caixa caixaSave = new Caixa();
		caixaSave.setNumeroDoCaixa(caixa.getNumeroDoCaixa());
		caixaSave.setStatus(caixa.getStatus());
		
		return repository.save(caixaSave);
	}

	@Override
	public Optional<Caixa> getCaixaByNumber(Long numeroDoCaixa) {

		return repository.findById(numeroDoCaixa);
	
	}

	@Override
	public Caixa openCaixa(Optional<Caixa> caixaBd, OpenAndCloseCaixaRequestDTO caixa) {
		
		Caixa caixaReturn = new Caixa(caixaBd);
		caixaReturn.setDtHrAbertura(DateUtils.dateNowTimestamp());
		caixaReturn.setFundoDeTroco(caixa.getFundoDeTroco());
		caixaReturn.setOperador(caixa.operador);
		caixaReturn.setStatus(ABERTO);
		
		return repository.save(caixaReturn);
	}

	@Override
	public Caixa closeCaixa(Optional<Caixa> caixaBd, OpenAndCloseCaixaRequestDTO caixa) {
		
		Caixa caixaReturn = new Caixa(caixaBd);
		caixaReturn.setDtHrFechamento(DateUtils.dateNowTimestamp());
		caixaReturn.setFundoDeTroco(caixa.getFundoDeTroco());
		caixaReturn.setOperador(caixa.operador);
		caixaReturn.setStatus(FECHADO);
		
		return repository.save(caixaReturn);
	}

	@Override
	public List<Caixa> getAll() {
		return repository.findAll();
	}

	@Override
	public void update(Caixa caixaToUpdate) {
		
		repository.save(caixaToUpdate);
		
	}

	@Override
	public void deleteCaixa(Long number) {

		repository.deleteById(number);
		
	}

}
