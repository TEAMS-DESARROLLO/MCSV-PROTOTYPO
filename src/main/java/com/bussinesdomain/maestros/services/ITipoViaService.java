package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.TipoViaEntity;

public interface ITipoViaService extends IBaseInterfaceService<TipoViaEntity, Long> {


    TipoViaEntity update(TipoViaEntity entidad, Long id);
    
}
