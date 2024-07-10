package com.bussinesdomain.maestros.services.impl;



import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.models.TipoDocumentoIdentidadEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ITipoDocumentoIdentidadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoDocumentoIdentidadServiceImpl extends CRUDImpl<TipoDocumentoIdentidadEntity,String>  implements  ITipoDocumentoIdentidadService {

    private final IGenericRepository<TipoDocumentoIdentidadEntity, String> repository;


    @Override
    public TipoDocumentoIdentidadEntity update(TipoDocumentoIdentidadEntity entidad, String id) {
            TipoDocumentoIdentidadEntity original =  this.readById(id);
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }


    @Override
    protected IGenericRepository<TipoDocumentoIdentidadEntity, String> getRepo() {
       return repository;
    }

    
}
