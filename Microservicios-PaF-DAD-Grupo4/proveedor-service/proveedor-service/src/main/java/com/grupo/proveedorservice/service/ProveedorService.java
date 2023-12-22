package com.grupo.proveedorservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.grupo.proveedorservice.entity.Proveedor;

public interface ProveedorService {
	public List<Proveedor> findAll(Pageable page);
	public List<Proveedor> findByNombre(String nombre, Pageable page);
	public Proveedor findById(int id);
	public Proveedor save(Proveedor proveedor);
	public Proveedor update(Proveedor proveedor);
	public void delete(int id);
}
