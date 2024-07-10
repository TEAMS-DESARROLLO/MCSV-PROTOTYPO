package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.TipoViaEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ITipoViaService;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TipoViaServiceImpl extends CRUDImpl<TipoViaEntity,Long>  implements ITipoViaService  {

    private final IGenericRepository<TipoViaEntity,Long> repository;
    
    @Override
    protected IGenericRepository<TipoViaEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public TipoViaEntity update(TipoViaEntity entidad, Long id) {
            TipoViaEntity original =  this.readById(id);
            if(original.equals(null)){
                throw new ModelNotFoundException("No existe el id : " + id);
            }
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }

    @Override
    public List<TipoViaEntity> getAll() {
        return super.getAll();
    }

    



    





    
}
