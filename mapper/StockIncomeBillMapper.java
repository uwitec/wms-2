package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.StockIncomeBillQueryObject;
import java.util.List;

public interface StockIncomeBillMapper {
	void save(StockIncomeBill entity);
	
	void update(StockIncomeBill entity);
	
	void delete(Long id);
	
    StockIncomeBill get(Long id);
    
	List<StockIncomeBill> list();
	
    Long getTotalCount(StockIncomeBillQueryObject qo);
    
    List<StockIncomeBill> getListData(StockIncomeBillQueryObject qo);

	void updateStatus(StockIncomeBill  incomeBill);
}