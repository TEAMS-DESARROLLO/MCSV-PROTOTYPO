package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.AlmacenEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IAlmacenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlmacenImpl extends CRUDImpl <AlmacenEntity,Long> implements IAlmacenService {
    
    private final IGenericRepository<AlmacenEntity, Long> repository;

    @Override
    protected IGenericRepository<AlmacenEntity, Long> getRepo(){ 
        return repository;

    }
    @Override
    public AlmacenEntity update(AlmacenEntity entidad, Long id) {
            AlmacenEntity original =  this.readById(id);
            if(original.equals(null)){
                throw new ModelNotFoundException("No existe el id : " + id);
            }
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }
}
