package com.grupo.productservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo.productservice.entity.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	public Producto findByNombre(String nombre);
	public List<Producto> findByNombreContaining(String nombre,Pageable page);
}
