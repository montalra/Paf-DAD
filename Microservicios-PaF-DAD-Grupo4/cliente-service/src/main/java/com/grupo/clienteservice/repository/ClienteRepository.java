package com.grupo.clienteservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo.clienteservice.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public Cliente findByNombre(String nombre);
	public List<Cliente> findByNombreContaining(String nombre,Pageable page);
}