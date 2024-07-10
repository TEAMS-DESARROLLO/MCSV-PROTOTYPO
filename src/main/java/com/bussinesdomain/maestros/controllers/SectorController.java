package com.bussinesdomain.maestros.controllers;

import java.util.List;

import org.hibernate.jdbc.BatchFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.SectorDTO;
import com.bussinesdomain.maestros.dto.TipoViaDTO;
import com.bussinesdomain.maestros.exception.BadRequestException;
import com.bussinesdomain.maestros.mapper.ISectorMapper;
import com.bussinesdomain.maestros.models.SectorEntity;
import com.bussinesdomain.maestros.services.ISectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sector")
public class SectorController {

     private final ISectorService service;
    private final IPaginationCommons<SectorDTO> iPaginationCommons;
    private final ISectorMapper iSectorMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        log.info("PAGINATION ....." + pagination);

        Page<SectorDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK) ;
    }

    @GetMapping("/{idSector}")
    public ResponseEntity<SectorDTO> findById(@PathVariable("idSector") Long idSector){

        try {
            SectorEntity obj = service.readById(idSector);
            SectorDTO dto = iSectorMapper.toGetDTO(obj);
    
            return new ResponseEntity<>(dto, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new BatchFailedException(e.getMessage());
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<SectorDTO>> findByFiltro(){
        
        List<SectorEntity> lst = service.getAll();
        List<SectorDTO> lstTipoViaDto = this.iSectorMapper.listEntityToDto(lst);

        return new ResponseEntity<>(lstTipoViaDto, HttpStatus.OK);
    }    

    @PostMapping("/create")
    private ResponseEntity<SectorDTO> save(  @RequestBody  SectorDTO dto) {

      

            SectorEntity entidad = service.create(this.iSectorMapper.toEntity(dto) );
            SectorDTO sectorDTO = iSectorMapper.toGetDTO(entidad);
            
          return new ResponseEntity<>(sectorDTO, HttpStatus.CREATED);
        
    }

    @PutMapping("/{idSector}")
    public ResponseEntity<SectorDTO> update( @PathVariable("idSector") Long idSector, @RequestBody @Valid SectorDTO dto){

     
        
        SectorEntity objEntitySource = this.iSectorMapper.toEntity(dto);

        SectorEntity obj =  service.update(objEntitySource, idSector);
        
        return new ResponseEntity<>(this.iSectorMapper.toGetDTO(obj) , HttpStatus.OK);
    }    

    @DeleteMapping("/{idSector}")
    public ResponseEntity<TipoViaDTO> delete(@PathVariable("idSector") Long idSector){
    
        try {
            service.deleteById(idSector);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
