package com.grupo.clienteservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO {
	private int id;
	private String nombre;	
	private String numerodocumento;
	private String telefono;
	private String email;
	private Date created_at;
	private Boolean activo;

}
