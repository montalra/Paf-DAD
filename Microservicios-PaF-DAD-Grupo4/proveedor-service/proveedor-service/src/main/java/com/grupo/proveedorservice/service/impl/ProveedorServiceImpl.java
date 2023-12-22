package com.grupo.proveedorservice.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo.proveedorservice.entity.Proveedor;
import com.grupo.proveedorservice.exceptions.GeneralServiceException;
import com.grupo.proveedorservice.exceptions.NoDataFoundException;
import com.grupo.proveedorservice.exceptions.ValidateServiceException;
import com.grupo.proveedorservice.repository.ProveedorRepository;
import com.grupo.proveedorservice.service.ProveedorService;
import com.grupo.proveedorservice.validator.ProveedorValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired
	private ProveedorRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList(); 
		}catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page); 
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Proveedor findById(int id) {
		try {
			Proveedor registro= repository.findById(id).orElseThrow();
			return registro;
		}catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Proveedor save(Proveedor proveedor) {
		try {
			ProveedorValidator.save(proveedor);
			proveedor.setActivo(true);
			if(repository.findByNombre(proveedor.getNombre())!=null) {
				throw new ValidateServiceException("Ya existe un registro con ese nombre " +proveedor.getNombre());
			}
			Proveedor registro=repository.save(proveedor);
			return registro;
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Proveedor update(Proveedor proveedor) {
		try {
			ProveedorValidator.save(proveedor);
			Proveedor registro=repository.findById(proveedor.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID "));
			Proveedor registroD=repository.findByNombre(proveedor.getNombre());
			if(registroD!=null && registroD.getId()!=proveedor.getId()) {
				throw new ValidateServiceException("Ya existe un registro con ese numero ID " +proveedor.getId());
			}
			registro.setNombre(proveedor.getNombre());
			registro.setRuc(proveedor.getRuc());
			registro.setDireccion(proveedor.getDireccion());
			registro.setTelefono(proveedor.getTelefono());
			registro.setCorreo(proveedor.getCorreo());
			repository.save(registro);
			return proveedor;
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Proveedor registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			repository.delete(registro);
			
		}catch (ValidateServiceException  | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
			
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
