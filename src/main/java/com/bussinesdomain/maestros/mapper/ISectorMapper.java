package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.SectorDTO;

import com.bussinesdomain.maestros.models.SectorEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISectorMapper {
    @Mappings({
        //@Mapping(source = "idTipoVia", target = "codigo")
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    SectorDTO toGetDTO (SectorEntity sectorEntity);

    @InheritInverseConfiguration
    SectorEntity toEntity(SectorDTO sectorDTO);


    @Mappings({
          @Mapping(target  = "estado", ignore = true),
    })
    List<SectorDTO> listEntityToDto(List<SectorEntity> tipoVias);
    
}
