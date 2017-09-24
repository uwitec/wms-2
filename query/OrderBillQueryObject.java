package com._520it.wms.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class OrderBillQueryObject extends QueryObject {

	private Long supplierId=-1L;
	private int status=-1;
	private Date beginDate;
	private Date endDate;
}
