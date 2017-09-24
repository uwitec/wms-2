package com._520it.wms.mapper;

import com._520it.wms.domain.Supplier;
import com._520it.wms.query.SupplierQueryObject;
import java.util.List;

public interface SupplierMapper {
	void save(Supplier entity);
	
	void update(Supplier entity);
	
	void delete(Long id);
	
    Supplier get(Long id);
    
	List<Supplier> list();
	
    Long getTotalCount(SupplierQueryObject qo);
    
    List<Supplier> getListData(SupplierQueryObject qo);
}