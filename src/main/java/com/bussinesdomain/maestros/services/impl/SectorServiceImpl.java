package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.SectorEntity;

import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ISectorService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SectorServiceImpl  extends CRUDImpl<SectorEntity,Long>  implements ISectorService {

    private final IGenericRepository<SectorEntity,Long> repository;

    @Override
    protected IGenericRepository<SectorEntity, Long> getRepo() {

        return repository;
    }

    @Override
    public SectorEntity update(SectorEntity entidad, Long id) {
            SectorEntity original =  this.readById(id);
            if(original.equals(null)){
                throw new ModelNotFoundException("No existe el id : " + id);
            }
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }

}
