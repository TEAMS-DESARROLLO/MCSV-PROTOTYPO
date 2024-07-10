package com.bussinesdomain.maestros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenDTO {

    @NotEmpty(message = "no puede estar en blanco")
    @NotBlank(message = "no puede estar vacia")
    private Long idAlmacen;
    private String dscAlmacen;
    private String ubicacion;
    private String observacion; 
    private String estado;

}
