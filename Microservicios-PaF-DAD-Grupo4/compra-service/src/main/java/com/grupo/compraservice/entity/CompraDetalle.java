package com.grupo.compraservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "compradetalles")
public class CompraDetalle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "La cantidad de ser mayor que cero")
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(nullable=false)
    private Double  precio;

    @Column(name = "producto_id")
    private int productoId;

    @Transient
    private Double subTotal;


    public Double getSubTotal(){
        if (this.precio >0  && this.cantidad >0 ){
            return this.cantidad * this.precio;
        }else {
            return (double) 0;
        }
    }
    public CompraDetalle(){
        this.cantidad=(double) 0;
        this.precio=(double) 0;

    }
}
