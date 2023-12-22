package com.grupo.proveedorservice.validator;

import com.grupo.proveedorservice.entity.Proveedor;
import com.grupo.proveedorservice.exceptions.ValidateServiceException;

public class ProveedorValidator {
	public static void save(Proveedor proveedor) {
		if(proveedor.getNombre()==null||proveedor.getNombre().isEmpty()) {
			throw new ValidateServiceException("Nombre es requerido");
		}
		if(proveedor.getRuc().length()>100) {
			throw new ValidateServiceException("Ruc incompleto");
		}
		if(proveedor.getDireccion()==null) {
			throw new ValidateServiceException("La direccion es requerido");
		}
		if(proveedor.getTelefono()==null) {
			throw new ValidateServiceException("El telefono es requerido");
		}
		if(proveedor.getCorreo().length()<3) {
			throw new ValidateServiceException("El correo es requerido");
		}
	}
}
