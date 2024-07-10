package com.bussinesdomain.maestros.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.ViaDTO;

import com.bussinesdomain.maestros.models.ViaEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IViaMapper {

     

    @Mappings({
        @Mapping(source = "descripcion", target = "descripcion"),
        @Mapping(target  = "idTipoVia", ignore = true),
        @Mapping(target  = "tipoViaDescripcion", ignore = true)
        
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    ViaDTO toGetDTO (ViaEntity ViaEntity);

    @Mappings({
        //@Mapping(source = "idVia", target = "idVia"),
        @Mapping(target  = "createdAt", ignore = true),
        @Mapping(target  = "updatedAt", ignore = true),
        @Mapping(target  = "tipoVia", ignore = true),
        
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    @InheritInverseConfiguration
    ViaEntity toEntity(ViaDTO ViaDTO);
    
}
