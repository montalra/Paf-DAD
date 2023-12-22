package com.marketing.marcas.model;

import java.util.Date;

import lombok.Data;
@Data
public class Producto {
	private int id;
	private String nombre;
	private Double precio;
	private String descripcion;
	private int stock;
	private Date createdAt;
	private Boolean activo;
}
