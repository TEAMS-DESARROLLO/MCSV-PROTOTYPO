package com.bussinesdomain.maestros.models;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "almacen")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlmacenEntity {
    
    @Id
    @GeneratedValue(generator = "secuenciaAlmacen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "secuenciaAlmacen", sequenceName = "Almacen_seq", allocationSize = 1)

    @Column(name = "id_Almacen")
    private Long idAlmacen;

    @Column(name = "dsc_almacen")
    private String dscAlmacen;

    private String ubicacion;
    private String observacion; 
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
