package com._520it.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class OrderBillItem extends BaseDomain {
	
		private BigDecimal costPrice;//采购价
		private BigDecimal number;//采购数量
		private BigDecimal amount;//采购小计
		private String remark;//备注
		private Product product;//对应的商品
		private  Long billId;//对应的单据ID
	 
}
