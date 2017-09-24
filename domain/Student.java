package com._520it.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@ObjectProp("学生")
public class Student {

	@ObjectProp("编号")
	private Long id;
	@ObjectProp("姓名")
	private String name;
	@ObjectProp("年龄")
	private Integer age;
}
