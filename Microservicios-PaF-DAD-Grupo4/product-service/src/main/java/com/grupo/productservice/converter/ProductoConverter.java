package com.grupo.productservice.converter;

import org.springframework.stereotype.Component;

import com.grupo.productservice.dto.ProductoDTO;
import com.grupo.productservice.entity.Producto;



@Component
public class ProductoConverter extends AbstractConverter<Producto, ProductoDTO> {

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if(entity==null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.precio(entity.getPrecio())
				.descripcion(entity.getDescripcion())
				.stock(entity.getStock())
				.created_at(entity.getCreatedAt())
				.activo(entity.getActivo())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if(dto==null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.precio(dto.getPrecio())
				.descripcion(dto.getDescripcion())
				.stock(dto.getStock())
				.activo(dto.getActivo())
				.build();
	}

}
