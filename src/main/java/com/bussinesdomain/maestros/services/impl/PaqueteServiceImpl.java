package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.PaqueteEntity;

import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IPaqueteService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//
@Service
@RequiredArgsConstructor
public class PaqueteServiceImpl  extends CRUDImpl<PaqueteEntity,Long>  implements IPaqueteService {

    private final IGenericRepository<PaqueteEntity,Long> repository;

    @Override
    protected IGenericRepository<PaqueteEntity, Long> getRepo() {

        return repository;
    }

    @Override
    public PaqueteEntity update(PaqueteEntity entidad, Long id) {
            PaqueteEntity original =  this.readById(id);
            if(original.equals(null)){
                throw new ModelNotFoundException("No existe el id : " + id);
            }
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }

}
