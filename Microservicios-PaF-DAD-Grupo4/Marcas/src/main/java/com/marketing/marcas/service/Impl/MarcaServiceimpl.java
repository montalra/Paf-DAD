package com.marketing.marcas.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.marketing.marcas.entity.Marcas;
import com.marketing.marcas.exception.GeneralServiceException;
import com.marketing.marcas.exception.NoDataServiceException;
import com.marketing.marcas.exception.ValidateServiceException;
import com.marketing.marcas.repository.MarcaRepository;
import com.marketing.marcas.service.MarcaService;
import com.marketing.marcas.validator.MarcaVlidator;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class MarcaServiceimpl implements MarcaService{
	@Autowired
	private MarcaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Marcas> findAll(Pageable page) {
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
	public Marcas findById(Integer id) {
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
	@Transactional
	public List<Marcas> findByNombremarca(String Nombremarca) {
		try {
			return repository.findByNombremarcaContaining(Nombremarca);
			
		} catch (NoDataServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public Marcas create(Marcas marca) {
		try {
			//Validación
			MarcaVlidator.save(marca);
			if(repository.findByNombremarca(marca.getNombremarca())!=null){
				
				throw new ValidateServiceException("ya existe un registro con ese comprobante"+marca.getNombremarca());
				
			}
			//Guardamos
			marca.setEstado("ACEPTADO");
			return repository.save(marca);
		} catch (ValidateServiceException | NoDataServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Marcas update(Marcas marca) {
		try {
			MarcaVlidator.save(marca);
			Marcas registroD=repository.findByNombremarca(marca.getNombremarca());
			if (registroD !=null && registroD.getId()!=marca.getId()) {
				throw new ValidateServiceException("ya existe un registro con ese comprobante"+ marca.getNombremarca());
			}
			
			Marcas registro=repository.findById(marca.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ marca.getId()));
			registro.setNombremarca(marca.getNombremarca());
			registro.setItems(marca.getItems());
			
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
	public int anular(int id) {
		try {
			Marcas salidaDb= findById(id);
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
