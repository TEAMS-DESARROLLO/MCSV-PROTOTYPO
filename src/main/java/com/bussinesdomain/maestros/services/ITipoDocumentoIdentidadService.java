package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.TipoDocumentoIdentidadEntity;

public interface ITipoDocumentoIdentidadService extends IBaseInterfaceService<TipoDocumentoIdentidadEntity,String>  {


    TipoDocumentoIdentidadEntity update(TipoDocumentoIdentidadEntity entidad, String id);

   
    
    
}
