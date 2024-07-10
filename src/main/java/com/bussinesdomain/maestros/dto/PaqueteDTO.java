package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteDTO {

    private Long idPaquete;
    private String dscPaquete;
    private String observacion;
    private String estado;
    

}
