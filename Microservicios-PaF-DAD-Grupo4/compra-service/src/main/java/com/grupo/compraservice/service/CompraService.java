package com.grupo.compraservice.service;
import java.util.List;

import com.grupo.compraservice.entity.Compra;
import org.springframework.data.domain.Pageable;

public interface CompraService {
	public List<Compra> findAll(Pageable page);
	public Compra findById(Long id);
	public List<Compra> findByNumero(String numero);
    public Compra create(Compra compra);
    public Compra update(Compra compra);
    public int anular(Long id);
  

}
