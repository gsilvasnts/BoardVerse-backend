package br.com.boardverse.boardverse_backend.converter;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.boardverse.boardverse_backend.database.model.CarrinhoEntity;
import br.com.boardverse.boardverse_backend.dto.carrinho.AdicionarItemCarrinhoRequestDto;
import br.com.boardverse.boardverse_backend.dto.carrinho.CarrinhoResponseDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarrinhoMapper {
    
    CarrinhoResponseDto toResponseDto(CarrinhoEntity entity);

}
