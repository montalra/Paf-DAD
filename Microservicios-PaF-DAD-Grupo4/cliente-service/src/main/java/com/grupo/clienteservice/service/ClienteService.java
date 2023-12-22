package com.grupo.clienteservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.grupo.clienteservice.entity.Cliente;


public interface ClienteService {
	public List<Cliente> findAll(Pageable page);
	public Cliente findById(int id);
	public List<Cliente> findByNombre(String nombre, Pageable page);
    public Cliente save(Cliente cliente);
    public Cliente update(Cliente cliente);
    public void delete(int id);

}
