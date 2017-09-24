package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Permission extends BaseDomain {
	private String name;
	//com._520it.rbac.web.action.EmployeeAction:saveOrUpdate
	private String expression;//保存权限表达式
}
