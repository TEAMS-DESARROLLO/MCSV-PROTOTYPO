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
@Table(name = "sector")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorEntity {

    @Id
    @GeneratedValue(generator = "secuenciaSector", strategy=GenerationType.IDENTITY)
    @SequenceGenerator(name = "secuenciaSector",sequenceName = "sector_seq", allocationSize=1 )
    
    @Column(name="id_sector")
    private Long idSector;
    private String descripcion;
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
