package com._520it.wms.domain;

import generator.ObjectProp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("系统菜单")
public class SystemMenu extends BaseDomain {

	@ObjectProp("名称")
	private String name;
	@ObjectProp("URL")
	private String url;
	@ObjectProp("编码")
	private String sn;
	// 父菜单 多对一
	private SystemMenu parent;

	private List<SystemMenu> children = new ArrayList<>();

	public String getParentName() {
		if (getParent() == null) {
			return "根目录";
		} else {
			return parent.getName();
		}
	}

	@Override
	public String toString() {
		return "SystemMenu [name=" + name + ", url=" + url + ", sn=" + sn + "]";
	}
}
