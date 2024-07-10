package com.bussinesdomain.maestros.models;


import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_documento_identidad")
public class TipoDocumentoIdentidadEntity {

    @Id
    @Column(name = "cod_tipo_documento_identidad")
    private String codTipoDocumentoIdentidad;
    
    @Column(name = "descripcion")
    private String descripcion;

    private String abreviatura;

    @Column(name = "estado")
    private String estado;    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;  

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;   


   @PrePersist
    public void prePersisten(){
        this.createdAt = new Date();
        this.estado = "A";
    }    

    @PreUpdate
    public void preModify(){
        this.updatedAt = new Date();
    }        
    
    
}
