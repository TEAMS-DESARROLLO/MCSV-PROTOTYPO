package com.bussinesdomain.maestros.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;


import com.bussinesdomain.maestros.dto.TipoDocumentoIdentidadDTO;
import com.bussinesdomain.maestros.models.TipoDocumentoIdentidadEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TipoDocumentoIdentidadMapper {
    @Mappings({
        @Mapping(source = "codTipoDocumentoIdentidad", target = "codigo"),
        @Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    TipoDocumentoIdentidadDTO toGetDTO(TipoDocumentoIdentidadEntity tipoDocumentoIdentidadEntity);

    //@InheritInverseConfiguration
    //TipoDocumentoIdentidadEntity toEntity(TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO);

    
}
