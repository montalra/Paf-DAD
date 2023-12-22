package com.grupo.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo.productservice.entity.Producto;
import com.grupo.productservice.exception.GeneralServiceException;
import com.grupo.productservice.exception.NoDataServiceException;
import com.grupo.productservice.exception.ValidateServiceException;
import com.grupo.productservice.repository.ProductoRepository;
import com.grupo.productservice.service.ProductoService;
import com.grupo.productservice.validator.ProductoValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repository;

	@Override
	@Transactional(readOnly  =true)
	public List<Producto> findAll(Pageable page) {
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
	public Producto findById(int id) {
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
	public List<Producto> findByNombre(String nombre, Pageable page) {
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
	public Producto save(Producto producto) {
		try {
			ProductoValidator.save(producto);
			if(repository.findByNombre(producto.getNombre())!=null){
				
				throw new ValidateServiceException("ya existe un registro con el nombre"+producto.getNombre());
				
			}
			producto.setActivo(true);
			Producto registro=repository.save(producto);
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
	public Producto update(Producto producto) {
		try {
			ProductoValidator.save(producto);
			Producto registroD=repository.findByNombre(producto.getNombre());
			if (registroD !=null && registroD.getId()!=producto.getId()) {
				throw new ValidateServiceException("ya existe un registro con ese nombre"+ producto.getNombre());
			}
			
			Producto registro=repository.findById(producto.getId()).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ producto.getId()));
			registro.setNombre(producto.getNombre());
			registro.setPrecio(producto.getPrecio());
			registro.setDescripcion(producto.getDescripcion());
			registro.setStock(producto.getStock());
			
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
			Producto registro =repository.findById(id).orElseThrow(()->new NoDataServiceException("NO existe un registro con el ID "+ id));
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
