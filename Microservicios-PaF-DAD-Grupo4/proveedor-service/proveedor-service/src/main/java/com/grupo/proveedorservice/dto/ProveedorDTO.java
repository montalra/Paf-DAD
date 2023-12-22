package com.grupo.proveedorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProveedorDTO {
	private int id;
	private String nombre;
	private String ruc;
	private String direccion;
	private String telefono;
	private String correo;
}
