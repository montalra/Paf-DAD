package com.grupo.compraservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo.compraservice.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
    public Compra findByNumero(String numero);
    public List<Compra> findByNumeroContaining(String numero);
}
