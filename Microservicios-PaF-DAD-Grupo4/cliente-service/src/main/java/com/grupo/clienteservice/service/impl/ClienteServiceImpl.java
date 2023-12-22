package com.grupo.clienteservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo.clienteservice.entity.Cliente;
import com.grupo.clienteservice.exception.GeneralServiceException;
import com.grupo.clienteservice.exception.NoDataServiceException;
import com.grupo.clienteservice.exception.ValidateServiceException;
import com.grupo.clienteservice.repository.ClienteRepository;
import com.grupo.clienteservice.service.ClienteService;
import com.grupo.clienteservice.validator.ClienteValidator;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ClienteServiceImpl  implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	@Transactional(readOnly  =true)
	public List<Cliente> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
			
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
	public Cliente findById(int id) {
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
	public List<Cliente> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);
			
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
	public Cliente save(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			if(repository.findByNombre(cliente.getNombre())!=null){
				
				throw new ValidateServiceException("ya existe un registro con el nombre"+cliente.getNombre());
				
			}
			cliente.setActivo(true);
			Cliente registro=repository.save(cliente);
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
	public Cliente update(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			Cliente registroD=repository.findByNombre(cliente.getNombre());
			if (registroD !=null && registroD.getId()!=cliente.getId()) {
				throw new ValidateServiceException("ya existe un registro con el nombre"+ cliente.getNombre());
			}
			
			Cliente registro=repository.findById(cliente.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ cliente.getId()));
			registro.setNombre(cliente.getNombre());
			registro.setNumerodocumento(cliente.getNumerodocumento());
			registro.setTelefono(cliente.getTelefono());
			registro.setEmail(cliente.getEmail());
			
			
			
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
	public void delete(int id) {
		try {
			Cliente registro =repository.findById(id).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ id));
			repository.delete(registro);
			
		} catch (NoDataServiceException|ValidateServiceException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage());
		}
		
	}

}
