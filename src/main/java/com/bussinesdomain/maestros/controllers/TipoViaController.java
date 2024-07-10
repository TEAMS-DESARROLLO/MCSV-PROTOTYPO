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
import com.bussinesdomain.maestros.dto.TipoViaDTO;
import com.bussinesdomain.maestros.dto.TipoViaListResponseDTO;
import com.bussinesdomain.maestros.exception.BadRequestException;
import com.bussinesdomain.maestros.mapper.ITipoViaMapper;
import com.bussinesdomain.maestros.models.TipoViaEntity;
import com.bussinesdomain.maestros.services.ITipoViaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tipovia")
public class TipoViaController {

  
    private final ITipoViaService service;
    private final IPaginationCommons<TipoViaDTO> iPaginationCommons;
    private final ITipoViaMapper iTipoViaMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        log.info("PAGINATION ....." + pagination);

        Page<TipoViaDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK) ;
    }

    @GetMapping("/{idTipoVia}")
    @Operation(summary = "Tipo de documento por id", security = @SecurityRequirement(name = "bearerAuth")  )
    public ResponseEntity<TipoViaDTO> findById(@PathVariable("idTipoVia") Long idTipovia){

        try {
            TipoViaEntity obj = service.readById(idTipovia);
            TipoViaDTO dto = iTipoViaMapper.toGetDTO(obj);
    
            return new ResponseEntity<>(dto, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new BatchFailedException(e.getMessage());
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<TipoViaListResponseDTO>> findByFiltro(){
        
        List<TipoViaEntity> lst = service.getAll();
        List<TipoViaListResponseDTO> lstTipoViaDto = this.iTipoViaMapper.listEntityToDto(lst);

        return new ResponseEntity<>(lstTipoViaDto, HttpStatus.OK);
    }    

    @PostMapping("/create")
    private ResponseEntity<TipoViaDTO> save(  @RequestBody  TipoViaDTO dto) {

      

            TipoViaEntity entidad = service.create(this.iTipoViaMapper.toEntity(dto) );
            TipoViaDTO tipoViaDTO = iTipoViaMapper.toGetDTO(entidad);
            
          return new ResponseEntity<>(tipoViaDTO, HttpStatus.CREATED);
        
    }

    @PutMapping("/{idTipoVia}")
    public ResponseEntity<TipoViaDTO> update( @PathVariable("idTipoVia") Long idTipovia, @RequestBody @Valid TipoViaDTO dto){

     
        
        TipoViaEntity objEntitySource = this.iTipoViaMapper.toEntity(dto);

        TipoViaEntity obj =  service.update(objEntitySource, idTipovia);
        
        return new ResponseEntity<>(this.iTipoViaMapper.toGetDTO(obj) , HttpStatus.OK);
    }    

    @DeleteMapping("/{idTipoVia}")
    public ResponseEntity<TipoViaDTO> delete(@PathVariable("idTipoVia") Long idTipovia){
    
        try {
            service.deleteById(idTipovia);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }

    }



    
}
