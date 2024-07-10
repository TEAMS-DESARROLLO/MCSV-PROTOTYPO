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
import com.bussinesdomain.maestros.dto.PaqueteDTO;
import com.bussinesdomain.maestros.dto.TipoViaDTO;
import com.bussinesdomain.maestros.exception.BadRequestException;
import com.bussinesdomain.maestros.mapper.IPaqueteMapper;
import com.bussinesdomain.maestros.models.PaqueteEntity;
import com.bussinesdomain.maestros.services.IPaqueteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/paquete")
public class PaqueteController {

     private final IPaqueteService service;
    private final IPaginationCommons<PaqueteDTO> iPaginationCommons;
    private final IPaqueteMapper iPaqueteMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        log.info("PAGINATION ....." + pagination);

        Page<PaqueteDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK) ;
    }

    @GetMapping("/{idPaquete}")
    public ResponseEntity<PaqueteDTO> findById(@PathVariable("idPaquete") Long idPaquete){

        try {
            PaqueteEntity obj = service.readById(idPaquete);
            PaqueteDTO dto = iPaqueteMapper.toGetDTO(obj);
    
            return new ResponseEntity<>(dto, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new BatchFailedException(e.getMessage());
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<PaqueteDTO>> findByFiltro(){
        
        List<PaqueteEntity> lst = service.getAll();
        List<PaqueteDTO> lstTipoViaDto = this.iPaqueteMapper.listEntityToDto(lst);

        return new ResponseEntity<>(lstTipoViaDto, HttpStatus.OK);
    }    

    @PostMapping("/create")
    private ResponseEntity<PaqueteDTO> save(  @RequestBody  PaqueteDTO dto) {

      

            PaqueteEntity entidad = service.create(this.iPaqueteMapper.toEntity(dto) );
            PaqueteDTO paqueteDTO = iPaqueteMapper.toGetDTO(entidad);
            
          return new ResponseEntity<>(paqueteDTO, HttpStatus.CREATED);
        
    }

    @PutMapping("/{idPaquete}")
    public ResponseEntity<PaqueteDTO> update( @PathVariable("idPaquete") Long idPaquete, @RequestBody @Valid PaqueteDTO dto){

     
        
        PaqueteEntity objEntitySource = this.iPaqueteMapper.toEntity(dto);

        PaqueteEntity obj =  service.update(objEntitySource, idPaquete);
        
        return new ResponseEntity<>(this.iPaqueteMapper.toGetDTO(obj) , HttpStatus.OK);
    }    

    @DeleteMapping("/{idPaquete}")
    public ResponseEntity<TipoViaDTO> delete(@PathVariable("idPaquete") Long idPaquete){
    
        try {
            service.deleteById(idPaquete);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
