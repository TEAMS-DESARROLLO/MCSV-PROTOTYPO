package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.PaqueteEntity;

//
public interface IPaqueteService extends IBaseInterfaceService<PaqueteEntity,Long> {

    PaqueteEntity update(PaqueteEntity entidad, Long id);

}
