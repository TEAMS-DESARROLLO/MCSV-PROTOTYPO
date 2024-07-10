package com.bussinesdomain.maestros.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.TipoDocumentoIdentidadDTO;
import com.bussinesdomain.maestros.exception.ServiceException;
import com.bussinesdomain.maestros.mapper.TipoDocumentoIdentidadMapper;
import com.bussinesdomain.maestros.models.TipoDocumentoIdentidadEntity;
import com.bussinesdomain.maestros.services.ITipoDocumentoIdentidadService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tipodocumentoidentidad")
public class TipoDocumentoIdentidadController {

    private final ITipoDocumentoIdentidadService service;
    private final IPaginationCommons<TipoDocumentoIdentidadDTO> iTipoDocumentoIdentidadPaginationService;
    
    private final TipoDocumentoIdentidadMapper tipoDocumentoIdentidadMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        log.info("PAGINATION ....." + pagination);

        Page<TipoDocumentoIdentidadDTO> lst = iTipoDocumentoIdentidadPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK) ;
    }

    @GetMapping("/{codTipoDocumentoIdentidad}")
    @Operation(summary = "Tipo de documento por id", security = @SecurityRequirement(name = "bearerAuth")  )
    public ResponseEntity<TipoDocumentoIdentidadDTO> findById(@PathVariable("codTipoDocumentoIdentidad") String codTipoDocumentoIdentidad){

        try {
            TipoDocumentoIdentidadEntity obj = service.readById(codTipoDocumentoIdentidad);
            
            TipoDocumentoIdentidadDTO dto = tipoDocumentoIdentidadMapper.toGetDTO(obj);
    
            return new ResponseEntity<>(dto, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new ServiceException("Error " + e.getMessage());
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<TipoDocumentoIdentidadDTO>> getAll(){
        
        List<TipoDocumentoIdentidadEntity> objs = service.getAll();
        List<TipoDocumentoIdentidadDTO> lst = objs.stream().map( o -> {
            return tipoDocumentoIdentidadMapper.toGetDTO(o);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }    

    // @PostMapping("")
    // private ResponseEntity<TipoDocumentoIdentidadDTO> save(@Validated  @RequestBody TipoDocumentoIdentidadDTO dto) {

    //         TipoDocumentoIdentidadEntity entidad = service.create(this.convertToEntity(dto));
    //         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidad.getCodTipoDocumentoIdentidad()).toUri();
    //       return ResponseEntity.created(location).build();
        
    // }

    // @PutMapping("/{codTipoDocumentoIdentidad}")
    // public ResponseEntity<TipoDocumentoIdentidadDTO> update(@Validated @PathVariable("codTipoDocumentoIdentidad") String codTipoDocumentoIdentidad, @RequestBody TipoDocumentoIdentidadDTO dto){
        
    //     TipoDocumentoIdentidadEntity obj = service.update(this.convertToEntity(dto), codTipoDocumentoIdentidad);
    //     return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    // }    


    // private TipoDocumentoIdentidadDTO convertToDto(TipoDocumentoIdentidadEntity obj) {
    //     return modelMapper.map(obj, TipoDocumentoIdentidadDTO.class);
    // }

    // private TipoDocumentoIdentidadEntity convertToEntity(TipoDocumentoIdentidadDTO dto) {
    //     return modelMapper.map(dto, TipoDocumentoIdentidadEntity.class);
    // }

    
}
