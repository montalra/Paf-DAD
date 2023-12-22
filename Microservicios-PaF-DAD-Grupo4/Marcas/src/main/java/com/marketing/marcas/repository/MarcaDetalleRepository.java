package com.marketing.marcas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketing.marcas.entity.MarcaDetalle;

public interface MarcaDetalleRepository extends JpaRepository<MarcaDetalle, Integer> {
	
}
