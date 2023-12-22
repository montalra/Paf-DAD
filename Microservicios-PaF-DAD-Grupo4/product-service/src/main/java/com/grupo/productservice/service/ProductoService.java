package com.grupo.productservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.grupo.productservice.entity.Producto;


public interface ProductoService {
	public List<Producto> findAll(Pageable page);
	public Producto findById(int id);
	public List<Producto> findByNombre(String nombre, Pageable page);
    public Producto save(Producto producto);
    public Producto update(Producto producto);
    public void delete(int id);
}
