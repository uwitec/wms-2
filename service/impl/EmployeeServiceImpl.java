package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.ActionContextUtil;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl<mapper> implements IEmployeeService {

	@Setter
	private EmployeeMapper mapper;
	@Setter
	private PermissionMapper permissionMapper;

	@Override
	public void save(Employee e) {
		// 如果没有分配部门,把部门设置为null
		if (e.getDept().getId() == -1) {
			e.setDept(null);
		}
		mapper.save(e);
		// 保存员工和角色的关系
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			mapper.saveRelation(e.getId(), role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public void update(Employee e) {
		// 如果没有分配部门,把部门设置为null
		if (e.getDept().getId() == -1) {
			e.setDept(null);
		}
		mapper.update(e);
		// 删除员工和角色的关系
		mapper.deleteRoleRelation(e.getId());
		// 再将新的角色保存到中间中
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			mapper.saveRelation(e.getId(), role.getId());
		}
	}

	@Override
	public Employee get(Long id) {
		return mapper.get(id);
	}

	@Override
	public List<Employee> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = mapper.getTotalCount(qo);
		if (totalCount == 0) {
			return PageResult.emptyResult;
		}
		List<Employee> listData = mapper.getListData(qo);

		return new PageResult(qo.getCurrentPage(), qo.getPageSize(),
				totalCount.intValue(), listData);
	}

	@Override
	public Employee checkLogin(String username, String password) {
		// 定义一个集合将当前用户的所有的权限表达式存放起来
		Set<String> expressionSet = new HashSet<>();
		Employee e = mapper.checkLogin(username, password);
		if (e != null) {
			// 获取到当前用户的所有的权限
			List<Role> roles = e.getRoles();
			for (Role role : roles) {
				List<Permission> permissions = permissionMapper
						.getByRoleId(role.getId());
				for (Permission permission : permissions) {
					if(permission!=null){
						String expression = permission.getExpression();
						expressionSet.add(expression);
					}
				}
			}
			// 将所有的权限表达式存放到Session中
			ActionContextUtil.setUserPermission(expressionSet);
		}
		return e;
	}

	@Override
	public void batchDelete(Long[] ids) {
		mapper.batchDelete(ids);
	}

    @Override
    public Employee queryByname(String username) {
      return mapper.queryByname(username);
    }
}
