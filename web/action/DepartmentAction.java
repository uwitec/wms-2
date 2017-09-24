package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authz.annotation.RequiresPermissions;

public class DepartmentAction extends BaseAction {

	@Setter
	private IDepartmentService service;
	@Getter
	@Setter
	private Department dept;
	@Getter
	private QueryObject qo = new QueryObject();

	@RequiredPermission("部门列表")
	@RequiresPermissions("DepartmentAction:list")
	public String execute() throws Exception {
		PageResult result = service.pageQuery(qo);
		put("result", result);
		return LIST;
	}

	@RequiredPermission("删除部门")
	@RequiresPermissions("DepartmentAction:delete")
	public String delete() throws Exception {
		try {
			service.delete(dept.getId());
			putMsg("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败!");
		}
		return NONE;
	}

	@RequiredPermission("编辑部门")
	@RequiresPermissions("DepartmentAction:input")
	public String input() throws Exception {
		if (dept != null && dept.getId() != null) {
			dept = service.get(dept.getId());
		}
		return INPUT;
	}

	@RequiredPermission("保存或者更新部门")
	@RequiresPermissions("DepartmentAction:saveOrUpdate")
	public String saveOrUpdate() throws Exception {
		try {
			if (dept != null && dept.getId() != null) {
				service.update(dept);
				addActionMessage("更新成功!");
			} else {
				service.save(dept);
				addActionMessage("保存成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("保存或更新失败!");
		}

		return SUCCESS;
	}
}
