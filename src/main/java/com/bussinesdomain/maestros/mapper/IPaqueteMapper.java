package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.PaqueteDTO;

import com.bussinesdomain.maestros.models.PaqueteEntity;

//
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IPaqueteMapper {
    @Mappings({
        //@Mapping(source = "idTipoVia", target = "codigo")
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    PaqueteDTO toGetDTO (PaqueteEntity paqueteEntity);

    @InheritInverseConfiguration
    PaqueteEntity toEntity(PaqueteDTO paqueteDTO);


    @Mappings({
          @Mapping(target  = "estado", ignore = true),
    })
    List<PaqueteDTO> listEntityToDto(List<PaqueteEntity> tipoVias);
    
}
