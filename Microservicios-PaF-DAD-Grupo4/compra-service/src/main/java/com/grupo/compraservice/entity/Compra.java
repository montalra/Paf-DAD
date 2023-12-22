package com.grupo.compraservice.entity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo.compraservice.model.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Entity
@Table(name = "compras")
public class Compra {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero",nullable=false)
    private String numero;
    
    @Column(name="descripcion", nullable=true,length = 255)
    private String descripcion;

    @Column(name = "cliente_id",nullable=true )
    private int clienteId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private List<CompraDetalle> items;

    private String estado;

    public Compra(){
        items = new ArrayList<>();
    }
     
    @Transient
    private Cliente cliente;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
