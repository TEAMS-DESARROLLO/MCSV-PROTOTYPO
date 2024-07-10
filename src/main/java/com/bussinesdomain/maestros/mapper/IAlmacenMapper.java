package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingConstants;
import com.bussinesdomain.maestros.dto.AlmacenDTO;
import com.bussinesdomain.maestros.models.AlmacenEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAlmacenMapper {
    @Mappings({
        //@Mapping(source = "idTipoVia", target = "codigo")
        //@Mapping(source = "createdAt", target = "fechaCreacion", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    AlmacenDTO toGetDTO (AlmacenEntity paqueteEntity);

    @InheritInverseConfiguration
    AlmacenEntity toEntity(AlmacenDTO paqueteDTO);


    @Mappings({
          @Mapping(target  = "estado", ignore = true),
    })
    List<AlmacenDTO> listEntityToDto(List<AlmacenEntity> tipoVias);
    
}
