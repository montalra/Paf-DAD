package com.grupo.proveedorservice.converter;

import org.springframework.stereotype.Component;

import com.grupo.proveedorservice.dto.ProveedorDTO;
import com.grupo.proveedorservice.entity.Proveedor;



@Component
public class ProveedorConverter {
	public ProveedorDTO fromEntity(Proveedor entity) {
		if(entity==null) return null;
		return ProveedorDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.ruc(entity.getRuc())
				.direccion(entity.getDireccion())
				.correo(entity.getCorreo())
				.build();
	}
	public Proveedor fromDTO(ProveedorDTO dto) {
		if(dto==null) return null;
		return Proveedor.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.ruc(dto.getRuc())
				.direccion(dto.getDireccion())
				.correo(dto.getCorreo())
				.build();
	}
}
