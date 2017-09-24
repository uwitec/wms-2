package com._520it.wms.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.vo.SystemMenuVo;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService service;

	@Getter
	private SystemMenuQueryObject qo = new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu = new SystemMenu();

	@RequiredPermission("系统菜单列表")
	public String execute() {
		// 把所有的父菜单存起来
		SystemMenu parent = service.get(qo.getParentId());
		List<SystemMenuVo> parents = new ArrayList<>();
		while (parent != null) {
			SystemMenuVo vo = new SystemMenuVo();
			vo.setId(parent.getId());
			vo.setName(parent.getName());
			parents.add(vo);
			// 还有父菜单就再去取
			parent = parent.getParent();
		}
		Collections.reverse(parents);
		put("parents", parents);

		try {
			put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	public String input() {
		// 查询出编号和名称在保存和更新中使用
		try {
			if (qo.getParentId() == null) {
				put("parentName", "根目录");
			} else {
				SystemMenu parents = service.get(qo.getParentId());
				put("parentId", parents.getId());
				put("parentName", parents.getName());
			}
			if (systemMenu.getId() != null) {
				systemMenu = service.get(systemMenu.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if (systemMenu.getId() == null) {
				service.save(systemMenu);
				addActionMessage("保存成功");
			} else {
				service.update(systemMenu);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete() throws Exception {
		try {
			if (systemMenu.getId() != null) {
				service.delete(systemMenu.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String getMenusByParentSn() throws Exception {
		List<Map<String, Object>> menus = service.queryMenusByParentSn(qo.getParentSn());
		putAjax(menus);
		return NONE;
	}
}
