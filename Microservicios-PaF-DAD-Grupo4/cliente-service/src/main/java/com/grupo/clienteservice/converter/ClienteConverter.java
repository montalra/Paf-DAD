package com.grupo.clienteservice.converter;

import org.springframework.stereotype.Component;

import com.grupo.clienteservice.dto.ClienteDTO;
import com.grupo.clienteservice.entity.Cliente;
@Component

public class ClienteConverter extends AbstractConverter<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO fromEntity(Cliente entity) {
		if(entity==null) return null;
		return ClienteDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.email(entity.getEmail())
				.numerodocumento(entity.getNumerodocumento())
				.telefono(entity.getTelefono())
				.created_at(entity.getCreatedAt())
				.activo(entity.getActivo())
				.build();
	}

	@Override
	public Cliente fromDTO(ClienteDTO dto) {
		if(dto==null) return null;
		
		return Cliente.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.email(dto.getEmail())
				.numerodocumento(dto.getNumerodocumento())
				.telefono(dto.getTelefono())
				.build();
	}
}
