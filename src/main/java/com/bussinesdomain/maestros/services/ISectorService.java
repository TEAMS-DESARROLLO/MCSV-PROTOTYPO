package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.SectorEntity;


public interface ISectorService extends IBaseInterfaceService<SectorEntity,Long> {

    SectorEntity update(SectorEntity entidad, Long id);

}
