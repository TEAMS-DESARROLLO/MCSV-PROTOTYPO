package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoViaRequestDTO {

    @EqualsAndHashCode.Include
    private Long idTipoVia;
    private String descripcion;
    private String abreviacion;
    private String estado;


    
}
