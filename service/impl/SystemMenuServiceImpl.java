package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

import java.util.List;
import java.util.Map;

public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper mapper;

	public void delete(Long id) {
		mapper.delete(id);
	}

	public void save(SystemMenu entity) {
		mapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return mapper.get(id);
	}

	public List<SystemMenu> list() {
		return mapper.list();
	}

	public void update(SystemMenu entity) {
		mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<SystemMenu> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(),
				qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}

	@Override
	public List<Map<String, Object>> queryMenusByParentSn(String parentSn) {

        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();

        if (employee.getAdmin()) {
			return mapper.queryMenusByParentSn(parentSn);
		} else {
			return mapper.queryMenuByParentSnAndEmployeeId(parentSn, employee.getId());
		}

	}
}
