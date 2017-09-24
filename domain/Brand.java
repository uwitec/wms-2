package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter
@ToString
@ObjectProp("品牌")
public class Brand extends BaseDomain {
	@ObjectProp("品牌名称")
	private String name;
	@ObjectProp("品牌编码")
	private String sn;

}
