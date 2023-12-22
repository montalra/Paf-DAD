package com.grupo.productservice.model;

import lombok.Data;

@Data
public class Proveedor {
	
	private int id;
	String nombre;
	String ruc;
	String direccion;
	String telefono;
	String correo;
	
}
