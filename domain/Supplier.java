package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("供应商管理")
public class Supplier extends BaseDomain {

	@ObjectProp("供应商名称")
	private String name;
	@ObjectProp("联系电话")
	private String phone;
	@ObjectProp("地址")
	private String address;
}
