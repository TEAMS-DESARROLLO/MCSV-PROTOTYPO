package com.bussinesdomain.maestros.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoDocumentoIdentidadDTO {

    @EqualsAndHashCode.Include
    private String codigo;
 
    private String descripcion;

    private String abreviatura;
    
    // @NotNull
    // @NotEmpty
    // @Size(min = 1, max = 1)        
    private String estado;
    
    private String fechaCreacion;
    
    public  TipoDocumentoIdentidadDTO(String codigo, String descripcion, String estado, String abreviatura ){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.abreviatura = abreviatura;
    }


   


    
}
