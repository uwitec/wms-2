package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;

import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction {

	@Setter
	private IPermissionService service;
	@Getter
	@Setter
	private Permission p;
	@Getter
	private QueryObject qo = new QueryObject();

	@RequiredPermission("权限列表")
	public String execute() throws Exception {
		PageResult result = service.pageQuery(qo);
		put("result", result);
		return LIST;
	}

	@RequiredPermission("删除权限")
	public String delete() throws Exception {
		try {
			service.delete(p.getId());
			putMsg("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败 !");
		}
		return NONE;
	}

	@RequiredPermission("加载权限")
	public String reload() throws Exception {
		try {
			service.reload();
			putMsg("加载成功!");
		} catch (Exception e) {
			putMsg("加载失败!");
		}
		return NONE;
	}
}
