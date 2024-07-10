package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.ViaEntity;

public interface IViaService extends IBaseInterfaceService<ViaEntity,Long> {

       ViaEntity update(ViaEntity entidad, Long id);
    
}
