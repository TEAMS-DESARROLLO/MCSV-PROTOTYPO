package com.bussinesdomain.maestros.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.bussinesdomain.maestros.dto.ViaDTO;
import com.bussinesdomain.maestros.mapper.IViaMapper;
import com.bussinesdomain.maestros.models.TipoViaEntity;
import com.bussinesdomain.maestros.models.ViaEntity;
import com.bussinesdomain.maestros.services.ITipoViaService;
import com.bussinesdomain.maestros.services.IViaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;


//@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/via")
public class ViaController {

    private final IViaService service;
    private final ITipoViaService tipoViaService;
    private final IPaginationCommons<ViaDTO> iPaginationCommons;
    private final IViaMapper iViaMapper;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody  PaginationModel pagination ){
        Page<ViaDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst,HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    @Operation(summary = "via por id", security = @SecurityRequirement(name = "bearerAuth")  )
    public ResponseEntity<ViaDTO> findById(@PathVariable("id") Long idvia){

        ViaEntity obj = service.readById(idvia);
        ViaDTO dto = iViaMapper.toGetDTO(obj);
        dto.setIdTipoVia(obj.getTipoVia().getIdTipoVia());
        dto.setTipoViaDescripcion (obj.getTipoVia().getDescripcion() );

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


    // @GetMapping("/all")
    // public ResponseEntity<List<TipoDocumentoIdentidadDTO>> findByFiltro(){
        
    //     List<TipoDocumentoIdentidadEntity> objs = service.getAll();
    //     List<TipoDocumentoIdentidadDTO> lst = objs.stream().map( this::convertToDto ).collect(Collectors.toList());

    //     return new ResponseEntity<>(lst, HttpStatus.OK);
    // }    

    @PostMapping("/create")
    private ResponseEntity<ViaDTO> save(@Validated  @RequestBody ViaDTO dto) {

        TipoViaEntity tipoViaEntity = tipoViaService.readById(dto.getIdTipoVia());
        ViaEntity entidad = this.iViaMapper.toEntity(dto);
        entidad.setTipoVia(tipoViaEntity);

        ViaEntity entidadSave = service.create( entidad);
        ViaDTO viaDTO = iViaMapper.toGetDTO(entidadSave);
            
        return new ResponseEntity<>(viaDTO, HttpStatus.CREATED);
        
    }

    @PutMapping("/{idVia}")
    public ResponseEntity<ViaDTO> update(@Validated @PathVariable("idVia") Long idvia, @RequestBody ViaDTO dto){

        TipoViaEntity tipoViaEntity = tipoViaService.readById(dto.getIdTipoVia());
    
        
        ViaEntity objEntitySource = this.iViaMapper.toEntity(dto);
        objEntitySource.setTipoVia(tipoViaEntity);

        ViaEntity obj =  service.update(objEntitySource, idvia);
        
        return new ResponseEntity<>(this.iViaMapper.toGetDTO(obj) , HttpStatus.OK);
    }    

    @DeleteMapping("/{idVia}")
    public ResponseEntity<TipoViaDTO> delete(@PathVariable("idVia") Long idvia){
    
        service.deleteById(idvia);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }

    
}
