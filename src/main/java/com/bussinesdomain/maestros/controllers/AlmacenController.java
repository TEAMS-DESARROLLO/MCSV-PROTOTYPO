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
import com.bussinesdomain.maestros.dto.AlmacenDTO;
import com.bussinesdomain.maestros.dto.TipoViaDTO;
import com.bussinesdomain.maestros.exception.BadRequestException;
import com.bussinesdomain.maestros.mapper.IAlmacenMapper;
import com.bussinesdomain.maestros.models.AlmacenEntity;
import com.bussinesdomain.maestros.services.IAlmacenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/almacen")
public class AlmacenController {

     private final IAlmacenService service;
    private final IPaginationCommons<AlmacenDTO> iPaginationCommons;
    private final IAlmacenMapper iAlmacenMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        log.info("PAGINATION ....." + pagination);

        Page<AlmacenDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

    @GetMapping("/{idAlmacen}")
    public ResponseEntity<AlmacenDTO> findById(@PathVariable("idAlmacen") Long idAlmacen){

        try {
            AlmacenEntity obj = service.readById(idAlmacen);
            AlmacenDTO dto = iAlmacenMapper.toGetDTO(obj);
    
            return new ResponseEntity<>(dto, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new BatchFailedException(e.getMessage());
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<AlmacenDTO>> findByFiltro(){
        
        List<AlmacenEntity> lst = service.getAll();
        List<AlmacenDTO> lstTipoViaDto = this.iAlmacenMapper.listEntityToDto(lst);

        return new ResponseEntity<>(lstTipoViaDto, HttpStatus.OK);
    }    

    @PostMapping("/create")
    private ResponseEntity<AlmacenDTO> save(  @RequestBody  AlmacenDTO dto) {

      

            AlmacenEntity entidad = service.create(this.iAlmacenMapper.toEntity(dto) );
            AlmacenDTO paqueteDTO = iAlmacenMapper.toGetDTO(entidad);
            
          return new ResponseEntity<>(paqueteDTO, HttpStatus.CREATED);
        
    }

    @PutMapping("/{idAlmacen}")
    public ResponseEntity<AlmacenDTO> update( @PathVariable("idAlmacen") Long idAlmacen, @RequestBody @Valid AlmacenDTO dto){

     
        
        AlmacenEntity objEntitySource = this.iAlmacenMapper.toEntity(dto);

        AlmacenEntity obj =  service.update(objEntitySource, idAlmacen);
        
        return new ResponseEntity<>(this.iAlmacenMapper.toGetDTO(obj) , HttpStatus.OK);
    }    

    @DeleteMapping("/{idAlmacen}")
    public ResponseEntity<TipoViaDTO> delete(@PathVariable("idAlmacen") Long idAlmacen){
    
        try {
            service.deleteById(idAlmacen);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

}
