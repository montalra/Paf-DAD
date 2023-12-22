package com.grupo.proveedorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo.proveedorservice.entity.Proveedor;
import com.grupo.proveedorservice.service.ProveedorService;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorService service;
	
	@GetMapping()
	public ResponseEntity<List<Proveedor>> findAll(
			@RequestParam(value="nombre", required= false, defaultValue="")String nombre,
			@RequestParam(value="offset", required= false, defaultValue="0")int pageNumber,
			@RequestParam(value="limit", required= false, defaultValue="5")int pageSize
			){
		Pageable page= PageRequest.of(pageNumber, pageSize);
		List<Proveedor> proveedor;
		if(nombre==null) {
			proveedor=service.findAll(page);
		}else {
			proveedor=service.findByNombre(nombre, page);
		}
		if(proveedor.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(proveedor);
	}
	@GetMapping(value= "/{id}")
	public ResponseEntity<Proveedor> findById(@PathVariable("id") int id){
		Proveedor proveedor=service.findById(id);
		if(proveedor==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(proveedor);
	}
	@PostMapping()
	public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor){
		Proveedor registro=service.save(proveedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	@PutMapping(value= "/{id}")
	public ResponseEntity<Proveedor>update(@PathVariable("id")int id, @RequestBody Proveedor proveedor){
		Proveedor registro=service.update(proveedor);
		if(registro==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(registro);
	}
	@DeleteMapping(value="/{id}")
		public ResponseEntity<Proveedor> delete(@PathVariable("id") int id){
			service.delete(id);
			return ResponseEntity.ok(null);
		}
}











