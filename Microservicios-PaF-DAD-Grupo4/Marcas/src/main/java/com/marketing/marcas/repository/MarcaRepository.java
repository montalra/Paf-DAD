package com.marketing.marcas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketing.marcas.entity.Marcas;

public interface MarcaRepository extends JpaRepository<Marcas,Integer>{

	public Marcas findByNombremarca(String Nombremarca);
	public List<Marcas>findByNombremarcaContaining(String Nombremarca);
}
