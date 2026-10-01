package br.com.boardverse.boardverse_backend.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.boardverse.boardverse_backend.database.model.ClienteEntity;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteRequestDto;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteResponseDto;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteUpdateRequestDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {

    ClienteResponseDto toResponseDto(ClienteEntity entity);

    ClienteEntity toEntity(ClienteRequestDto dto);

    void updateEntity(
            ClienteUpdateRequestDto request,
            @MappingTarget ClienteEntity entity);
}
