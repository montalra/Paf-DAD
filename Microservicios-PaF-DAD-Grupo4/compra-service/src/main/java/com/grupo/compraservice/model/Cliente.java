package com.grupo.compraservice.model;

import lombok.Data;

@Data
public class Cliente {
	
	private int id;
	private String nombre;	
	private String numerodocumento;
	private String telefono;
	private String email;
	private Boolean activo;

}
