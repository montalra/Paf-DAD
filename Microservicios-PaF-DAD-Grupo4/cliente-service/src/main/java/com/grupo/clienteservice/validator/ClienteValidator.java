package com.grupo.clienteservice.validator;

import com.grupo.clienteservice.entity.Cliente;
import com.grupo.clienteservice.exception.ValidateServiceException;

public class ClienteValidator {
	public static void save(Cliente cliente) {
		if(cliente.getNombre()==null || cliente.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(cliente.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		
		if(cliente.getEmail()==null || cliente.getEmail().trim().isEmpty()) {
			throw new ValidateServiceException("El email es requerido");
		}

		if(cliente.getNumerodocumento()==null || cliente.getNumerodocumento().trim().isEmpty()) {
			throw new ValidateServiceException("El numero de documento es requerido");
		}
		if(cliente.getNumerodocumento().length()>8) {
			throw new ValidateServiceException("El numero docuemnto es muy extenso");
		}
		if(cliente.getNumerodocumento().length()<8) {
			throw new ValidateServiceException("El numero docuemnto deve tener 8 caracteres");
		}
	}
}