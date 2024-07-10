package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.TipoViaDTO;
import com.bussinesdomain.maestros.dto.TipoViaListResponseDTO;
import com.bussinesdomain.maestros.models.TipoViaEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITipoViaMapper {
    @Mappings({
        @Mapping(source = "idTipoVia", target = "codigo")
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    TipoViaDTO toGetDTO (TipoViaEntity tipoViaEntity);

    @InheritInverseConfiguration
    TipoViaEntity toEntity(TipoViaDTO tipoViaDTO);

    @Mappings({
          @Mapping(target  = "estado", ignore = true),
    })
    List<TipoViaListResponseDTO> listEntityToDto(List<TipoViaEntity> tipoVias);
    
}
