package com.marketing.marcas.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.marketing.marcas.entity.Marcas;

public interface MarcaService {
	public List<Marcas>findAll(Pageable page);
	public Marcas findById(Integer id);
	public List<Marcas> findByNombremarca(String Nombremarca);
    public Marcas create(Marcas marca);
    public Marcas update(Marcas marca);
    public int anular(int id);
}
