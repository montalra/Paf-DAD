package com.grupo.productservice.dto;

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
public class ProductoDTO {
	private int id;
	private String nombre;
	private Double precio;
	private String descripcion;
	private int stock;
	private Date created_at	;
	private Boolean activo;
}
