package com.misiontic.webfavorites.converters;

import com.misiontic.webfavorites.dtos.RelProductoFavoritoDTO;
import com.misiontic.webfavorites.entity.RelProductoFavorito;

public class RelProductoFavoritoConv  extends AbstracConverter<RelProductoFavorito, RelProductoFavoritoDTO>{

	@Override
	public RelProductoFavorito toEntity(RelProductoFavoritoDTO dto) {
		return RelProductoFavorito.builder()
				.idRelProducto(dto.getIdRelProducto())
				.idFavorito(dto.getIdFavorito())
				.idProducto(dto.getIdProducto())
				.build();
	}

	@Override
	public RelProductoFavoritoDTO toDTO(RelProductoFavorito entity) {
		return RelProductoFavoritoDTO.builder()
				.idRelProducto(entity.getIdRelProducto())
				.idFavorito(entity.getIdFavorito())
				.idProducto(entity.getIdProducto())
				.build();
	}

}
