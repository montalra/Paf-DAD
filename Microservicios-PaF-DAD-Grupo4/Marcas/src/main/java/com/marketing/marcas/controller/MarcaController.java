package com.marketing.marcas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import org.springframework.data.domain.Pageable;



import com.marketing.marcas.dto.MarcaDTO;
import com.marketing.marcas.entity.Marcas;
import com.marketing.marcas.service.MarcaService;
import com.marketing.marcas.util.WrapperResponse;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
	@Autowired
	private MarcaService service;

	@GetMapping()
	public ResponseEntity<List<Marcas>>findAll(
			@RequestParam(value = "Nombremarca", required = false, defaultValue = " ") String Nombremarca,
			@RequestParam(value = "offset", required = false, defaultValue = " 0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = " 5") int pageSize
			){
		Pageable page =PageRequest.of(pageNumber, pageSize);
		List<Marcas>marcas;
		if (Nombremarca == null) {
			marcas = service.findAll(page);

		} else {
			marcas = service.findByNombremarca(Nombremarca);
		}if (marcas.isEmpty()) {
			return ResponseEntity.noContent().build();

		}
		return new WrapperResponse(true,"success",marcas).createResponse(HttpStatus.OK);	
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse<Marcas>> findById(@PathVariable("id") int id) {
		Marcas marca = service.findById(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		}
		return new WrapperResponse<Marcas>(true, "succes",marca ).createResponse(HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Marcas> create(@RequestBody Marcas marca) {
		Marcas registro = service.create(marca);
		return new WrapperResponse(true, "succes",registro ).createResponse(HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Marcas> update(@PathVariable("id") int id, @RequestBody Marcas marca) {
		Marcas registro = service.update(marca);
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		return new WrapperResponse(true, "succes",registro ).createResponse(HttpStatus.OK);

	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Marcas> delete(@PathVariable("id") int id) {
		service.anular(id);
		return new WrapperResponse(true, "succes",null ).createResponse(HttpStatus.OK);

	}
}