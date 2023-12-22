package com.grupo.proveedorservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo.proveedorservice.entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer > {
	public Proveedor findByNombre(String nombre);
	List<Proveedor> findByNombreContaining(String nombre, Pageable page);
	
}
