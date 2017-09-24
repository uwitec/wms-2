package com._520it.wms.domain;

import generator.ObjectProp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ObjectProp("采购订单")
@ToString
public class OrderBill extends BaseDomain {
	
	public static final int STATUS_NOMAL=0;//待审核
	public static final int STATUS_AUDIT=1;//已审核
	
	@ObjectProp("订单编号")
	private String sn;
	@ObjectProp("订单业务时间")
	private Date vdate;
	@ObjectProp("采购总数量")
	private BigDecimal totalNumber;
	@ObjectProp("采购总金额")
	private BigDecimal totalAmount;
	@ObjectProp("制单人")
	private Employee inputUser;
	@ObjectProp("制单时间")
	private Date inputTime;
	@ObjectProp("审核人")
	private Employee auditor;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("审核状态")
	private int status=STATUS_NOMAL;
	@ObjectProp("供应商")
	private Supplier supplier;
	
	private List<OrderBillItem> items = new ArrayList<>();

}
