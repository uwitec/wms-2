package com._520it.wms.web.action;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;

import lombok.Getter;
import lombok.Setter;

public class RoleAction extends BaseAction {

	@Setter
	private IRoleService service;
	@Setter
	private IPermissionService permissionService;
	@Getter
	private Role r = new Role();
	@Getter
	private QueryObject qo = new QueryObject();
	
	@Setter
	private ISystemMenuService menueService;

	@RequiredPermission("角色列表")
	public String execute() throws Exception {
		PageResult result = service.pageQuery(qo);
		put("result", result);
		return LIST;
	}

	@RequiredPermission("删除角色")
	public String delete() throws Exception {
		try {
			service.delete(r.getId());
			putMsg("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败!");
		}
		return NONE;
	}

	@RequiredPermission("编辑角色")
	public String input() throws Exception {
		// 查询所有的权限
		List<Permission> permissions = permissionService.list();
		put("permissions", permissions);
		//查询到所有的菜单
		List<SystemMenu> menus = menueService.list();
		put("menues", menus);
		if (r != null && r.getId() != null) {
			System.out.println(r.getId());
			r = service.get(r.getId());
		}
		return INPUT;
	}

	@RequiredPermission("保存或者更新角色")
	public String saveOrUpdate() throws Exception {
		try {
			if (r != null && r.getId() != null) {
				service.update(r);
				addActionMessage("更新成功!");
			} else {
				service.save(r);
				addActionMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("保存或更新失败!");
		}
		return SUCCESS;
	}
}
