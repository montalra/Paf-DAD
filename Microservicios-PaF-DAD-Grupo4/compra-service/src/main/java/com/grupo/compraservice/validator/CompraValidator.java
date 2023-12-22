package com.grupo.compraservice.validator;

import com.grupo.compraservice.entity.Compra;
import com.grupo.compraservice.entity.CompraDetalle;
import com.grupo.compraservice.exception.ValidateServiceException;

public class CompraValidator {
	public static void save(Compra compra) {
		if(compra.getNumero()==null) {
			throw new ValidateServiceException("El numero de comprobante es requerido");
		}
		
		if(compra.getItems() == null) {
			throw new ValidateServiceException("Los detalles son requeridas");
		}
		if(compra.getItems().isEmpty()) {
			throw new ValidateServiceException("Los detalles son requeridas");
		}
		
		for(CompraDetalle detalle: compra.getItems()) {
			
			if(detalle.getCantidad()==null) {
				throw new ValidateServiceException("La cantidad es requerido");
			}
			if(detalle.getCantidad()<0) {
				throw new ValidateServiceException("La cantidad es incorrecta");
			}
			
		}
		
	}

}
