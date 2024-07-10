package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.dto.PaqueteDTO;

import com.bussinesdomain.maestros.exception.ServiceException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

//
@Service
@RequiredArgsConstructor
public class PaquetePaginationService implements IPaginationCommons<PaqueteDTO> {

       
    private final  EntityManager entityManager;

    @Override
    public Page<PaqueteDTO> pagination(PaginationModel pagination) {

        try {
            
            String sqlCount  = "SELECT count(a) " + getFrom().toString() + getFilters( pagination.getFilters()  ).toString();
            String sqlSelect = getSelect().toString() + getFrom().toString() +getFilters( pagination.getFilters()).toString() + getOrder(pagination.getSorts());
                
            Query queryCount = entityManager. createQuery(sqlCount);
            Query querySelect = entityManager.createQuery(sqlSelect);
    
            this.setParams(pagination.getFilters(), queryCount);
            this.setParams(pagination.getFilters(), querySelect);
    
            Long total = (long) queryCount.getSingleResult();
    
            querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
            querySelect.setMaxResults(pagination.getRowsPerPage());        
    
            @SuppressWarnings("unchecked")
            List<PaqueteDTO> lista = querySelect.getResultList();
    
            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());
    
            Page<PaqueteDTO> page = new PageImpl<PaqueteDTO>(lista, pageable, total);
    
            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error al momento de generar la paginacion " + e.getMessage());
        }
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.PaqueteDTO(a.idPaquete,a.dscPaquete,a.observacion,a.estado) ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM PaqueteEntity a  ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idPaquete")){
                sql.append(" AND a.CODIGO = :codigo");
            }
            if(filtro.getField().equals("observacion")){
                sql.append(" AND a.observacion LIKE :observacion ");
            }
        }

        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idPaquete")){
                query.setParameter("codigo",filtro.getValue() );
            }
            if(filtro.getField().equals("observacion")){
                query.setParameter("observacion","%"+filtro.getValue()+"%");
            }
        }
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel>  sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(sorts.size() > 0){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idPaquete")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " idTipoPaquete " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("dscPaquete")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " dscPaquete " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("observacion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " observacion " + sort.getSort() );
                    flagMore = true;
                }
           }
        }
         return sql;
    }
    
}
