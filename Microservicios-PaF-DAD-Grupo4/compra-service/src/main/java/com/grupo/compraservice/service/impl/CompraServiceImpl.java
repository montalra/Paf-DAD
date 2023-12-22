package com.grupo.compraservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo.compraservice.entity.Compra;
import com.grupo.compraservice.exception.GeneralServiceException;
import com.grupo.compraservice.exception.NoDataServiceException;
import com.grupo.compraservice.exception.ValidateServiceException;
import com.grupo.compraservice.repository.CompraRepository;
import com.grupo.compraservice.service.CompraService;
import com.grupo.compraservice.validator.CompraValidator;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CompraServiceImpl  implements CompraService{

	@Autowired
	private CompraRepository repository;
	@Override
	@Transactional(readOnly = true)
	public List<Compra> findAll(Pageable page) {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Compra findById(Long id) {
		try {
			return repository.findById(id).
					orElseThrow(()->new NoDataServiceException("No Existe un registro con el ID: "+id));
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}
	
	
	@Override
	@Transactional(readOnly  =true)
	public List<Compra> findByNumero(String numero) {
		try {
			return repository.findByNumeroContaining(numero);
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}
	

	@Override
	@Transactional
	public Compra create(Compra compra) {
		try {
			//Validación
			CompraValidator.save(compra);
			if(repository.findByNumero(compra.getNumero())!=null){
				
				throw new ValidateServiceException("ya existe un registro con ese comprobante"+compra.getNumero());
				
			}
			//Guardamos
			compra.setEstado("ACEPTADO");
			return repository.save(compra);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Compra update(Compra compra) {
		try {
			CompraValidator.save(compra);
			Compra registroD=repository.findByNumero(compra.getNumero());
			if (registroD !=null && registroD.getId()!=compra.getId()) {
				throw new ValidateServiceException("ya existe un registro con ese comprobante"+ compra.getNumero());
			}
			
			Compra registro=repository.findById(compra.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ compra.getId()));
			registro.setNumero(compra.getNumero());
			registro.setDescripcion(compra.getDescripcion());
			registro.setCliente(compra.getCliente());
			registro.setItems(compra.getItems());
			
			repository.save(registro);
			return registro;
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public int anular(Long id) {
		try {
			Compra salidaDb= findById(id);
			if(salidaDb==null) {
				
				throw new NoDataServiceException("NO existe un registro con el ID "+ id);
				
				
			}else {
				salidaDb.setEstado("ANULADO");
				repository.save(salidaDb);
				return 1;
			}
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
