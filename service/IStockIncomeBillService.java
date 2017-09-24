package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockIncomeBillQueryObject;

public interface IStockIncomeBillService {
	void delete(Long id);
	
	void save(StockIncomeBill entity);
	
    StockIncomeBill get(Long id);
    
    List<StockIncomeBill> list();
    
	void update(StockIncomeBill entity);
	
	PageResult pageQuery(StockIncomeBillQueryObject qo);

	void audit(Long StockBillId);
	

}
