package com.marketing.marcas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="marcadetalles")
public class MarcaDetalle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="descripcion",nullable = false,length = 200)
	private String descripcion;
	
	@Column(name="producto_id")
	private int productoId;
	
	@Column(name="marca_id")
	private int marcaId;
}
