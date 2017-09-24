package com._520it.wms.service.impl;

import java.util.List;

import com._520it.wms.domain.Department;
import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;

import lombok.Setter;

public class DepartmentServiceImpl implements IDepartmentService {

	@Setter
	private DepartmentMapper mapper;

	@Setter
	private EmployeeMapper empMapper;

	@Override
	public void save(Department dept) {
		mapper.save(dept);
	}

	@Override
	public void delete(Long id) {
		//在删除部门之前,将关联了该部门的员工信息的部门编号设置null
		empMapper.deleteRelation(id);
		mapper.delete(id);
	}

	@Override
	public void update(Department dept) {
		mapper.update(dept);
	}

	@Override
	public Department get(Long id) {
		return mapper.get(id);
	}

	@Override
	public List<Department> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = mapper.getTotalCount(qo);
		if (totalCount == 0) {
			return PageResult.emptyResult;
		}
		List<Department> listData = mapper.getListData(qo);

		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount.intValue(), listData);
	}

}
