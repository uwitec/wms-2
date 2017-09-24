package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



//抽取通用的实体对象
@Getter
@Setter
public class BaseDomain implements Serializable {
	@ObjectProp("编号")
	protected Long id;
}
