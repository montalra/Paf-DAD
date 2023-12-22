package com.grupo.clienteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.clienteservice.converter.ClienteConverter;
import com.grupo.clienteservice.dto.ClienteDTO;
import com.grupo.clienteservice.entity.Cliente;
import com.grupo.clienteservice.service.ClienteService;
import com.grupo.clienteservice.util.WrapperResponse;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	@Autowired
	private ClienteConverter converter; 

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll(
			@RequestParam(value = "nombre", required = false, defaultValue = " ") String nombre,
			@RequestParam(value = "offset", required = false, defaultValue = " 0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = " 5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Cliente> clientes;
		if (nombre == null) {
			clientes = service.findAll(page);

		} else {
			clientes = service.findByNombre(nombre, page);
		}
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		List<ClienteDTO> clientesDTO=converter.fromEntity(clientes);
		return new WrapperResponse(true,"succes",clientesDTO).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse< ClienteDTO>> findById(@PathVariable("id") int id) {
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		ClienteDTO clienteDTO=converter.fromEntity(cliente);
		return new WrapperResponse<ClienteDTO>(true, "succes",clienteDTO ).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
		Cliente registro = service.save(converter.fromDTO(clienteDTO));
		ClienteDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.CREATED);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable("id") int id, @RequestBody ClienteDTO clienteDTO) {
		Cliente registro = service.update(converter.fromDTO(clienteDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		ClienteDTO registroDTO =converter.fromEntity(registro);
		return new WrapperResponse(true, "succes",registroDTO ).createResponse(HttpStatus.OK);

	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO>delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);

	}


}
