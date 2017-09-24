package com._520it.wms.mapper;

import com._520it.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {
	
	void save(OrderBillItem billId);

	void deleteByBillId(Long billId);
	
}