package com.bussinesdomain.maestros.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoViaDTO {

    @EqualsAndHashCode.Include
    @NotNull(message = "no puede ser nulo")
    //@NotEmpty(message = "La propiedad codigo tipovia no puede estar en blanco")
    private Long codigo;

    @NotEmpty(message = "np puede estar en blanco")
    @NotBlank(message = "no puede estar vacia")
    private String descripcion;

    @NotEmpty(message = "no puede estar en blanco")
    private String abreviacion;
    private String estado;


    
}
