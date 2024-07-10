package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.dto.TipoDocumentoIdentidadDTO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoDocumentoIdentidadPaginationServiceImpl  implements IPaginationCommons<TipoDocumentoIdentidadDTO> {

    
    private final  EntityManager entityManager;

    @Override
    public Page<TipoDocumentoIdentidadDTO> pagination(PaginationModel pagination) {
        String sqlCount  = "SELECT count(a) " + getFrom().toString() + getFilters( pagination.getFilters()  ).toString();
        String sqlSelect = getSelect().toString() + getFrom().toString() +getFilters( pagination.getFilters()).toString();
            
        Query queryCount = entityManager. createQuery(sqlCount);
        Query querySelect = entityManager.createQuery(sqlSelect);

        this.setParams(pagination.getFilters(), queryCount);
        this.setParams(pagination.getFilters(), querySelect);

        Long total = (long) queryCount.getSingleResult();

        querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
        querySelect.setMaxResults(pagination.getRowsPerPage());        

        @SuppressWarnings("unchecked")
        List<TipoDocumentoIdentidadDTO> lista = querySelect.getResultList();

        PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

        Page<TipoDocumentoIdentidadDTO> page = new PageImpl<TipoDocumentoIdentidadDTO>(lista, pageable, total);

        return page;
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.TipoDocumentoIdentidadDTO(a.codTipoDocumentoIdentidad,a.descripcion,a.estado,a.abreviatura) ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM TipoDocumentoIdentidadEntity a  ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("codigo")){
                sql.append(" AND a.codTipoDocumentoIdentidad = :codTipoDocumentoIdentidad ");
            }
            if(filtro.getField().equals("descripcion")){
                sql.append(" AND a.descripcion LIKE :descripcion ");
            }
        }

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("codigo")){
                query.setParameter("codTipoDocumentoIdentidad",filtro.getValue() );
            }
            if(filtro.getField().equals("descripcion")){
                query.setParameter("descripcion","%"+filtro.getValue()+"%");
            }
        }
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel> sorts) {
        StringBuilder sql = new StringBuilder("");
        return sql;
    }

}
