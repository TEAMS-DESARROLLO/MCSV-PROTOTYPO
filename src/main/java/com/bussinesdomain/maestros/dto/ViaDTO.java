package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ViaDTO {

    private Long idVia;
    private String descripcion;
    private Long idTipoVia;
    private String tipoViaDescripcion;
    private String estado;

 
    
    
}
