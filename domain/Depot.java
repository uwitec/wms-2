package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ObjectProp("仓库管理")
@ToString
public class Depot extends BaseDomain {

	@ObjectProp("仓库名称")
	private String name;
	@ObjectProp("仓库地址")
	private String location;
	
}
