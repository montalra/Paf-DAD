package com.grupo.compraservice.controller;

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

import com.grupo.compraservice.entity.Compra;
import com.grupo.compraservice.service.CompraService;
import com.grupo.compraservice.util.WrapperResponse;



@RestController
@RequestMapping("/compras")
public class CompraController {
	@Autowired
	private CompraService service;
	

	@GetMapping()
	public ResponseEntity<List<Compra>> findAll(
			@RequestParam(value = "numero", required = false, defaultValue = " ") String numero,
			@RequestParam(value = "offset", required = false, defaultValue = " 0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = " 5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Compra> compras;
		if (numero == null) {
			compras = service.findAll(page);

		} else {
			compras = service.findByNumero(numero);
		}
		if (compras.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		return new WrapperResponse(true,"succes",compras).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse< Compra>> findById(@PathVariable("id") long id) {
		Compra compra = service.findById(id);
		if (compra == null) {
			return ResponseEntity.notFound().build();
		}
		return new WrapperResponse<Compra>(true, "succes",compra ).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Compra> create(@RequestBody Compra compra) {
		Compra registro = service.create(compra);
		return new WrapperResponse(true, "succes",registro ).createResponse(HttpStatus.CREATED);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Compra> update(@PathVariable("id") int id, @RequestBody Compra compra) {
		Compra registro = service.update(compra);
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		return new WrapperResponse(true, "succes",registro ).createResponse(HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Compra> delete(@PathVariable("id") long id) {
		service.anular(id);
		return new WrapperResponse(true, "succes",null ).createResponse(HttpStatus.OK);

	}


}