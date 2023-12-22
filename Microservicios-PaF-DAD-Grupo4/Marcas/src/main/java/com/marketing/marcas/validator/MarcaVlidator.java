package com.marketing.marcas.validator;

import com.marketing.marcas.entity.MarcaDetalle;
import com.marketing.marcas.entity.Marcas;
import com.marketing.marcas.exception.ValidateServiceException;

public class MarcaVlidator {
	public static void save(Marcas marca) {
		if(marca.getNombremarca()==null) {
			throw new ValidateServiceException("El Nombre de marca es requerido");
		}
		if(marca.getItems() == null) {
			throw new ValidateServiceException("Los detalles son requeridas");
		}
		for(MarcaDetalle detalle: marca.getItems()) {
			
			if(detalle.getDescripcion()==null) {
				throw new ValidateServiceException("La cantidad es requerido");
			}
			if(detalle.getDescripcion().length()>200) {
				throw new ValidateServiceException("La cantidad es incorrecta");
			}
			
		}
	}
}
