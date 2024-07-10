package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.models.ViaEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IViaService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ViaServiceImpl extends CRUDImpl<ViaEntity,Long>  implements IViaService  {

    private final IGenericRepository<ViaEntity,Long> repository;
    
    @Override
    protected IGenericRepository<ViaEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public ViaEntity update(ViaEntity entidad, Long id) {
            ViaEntity original =  this.readById(id);
            String[] ignoreProperties= new String[]{"createdAt","updatedAt"};
            BeanUtils.copyProperties(entidad,original,ignoreProperties);        
            return super.update(original,id);
    }
    
}
