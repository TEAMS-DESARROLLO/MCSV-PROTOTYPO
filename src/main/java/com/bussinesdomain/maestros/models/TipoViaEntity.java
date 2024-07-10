package com.bussinesdomain.maestros.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_via")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoViaEntity {

    @Id
    @GeneratedValue(generator = "secuenciaTipoDocumento", strategy=GenerationType.IDENTITY)
    @SequenceGenerator(name = "secuenciaTipoDocumento",sequenceName = "tipo_via_seq", allocationSize=1 )
    @Column(name = "id_tipo_via")
    private Long idTipoVia;

    @Column(name = "dsc_tipo_via")
    private String descripcion;

    private String abreviacion;

    private String estado;
    
}
