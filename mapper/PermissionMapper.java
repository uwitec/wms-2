package com._520it.wms.mapper;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {

	void save(Permission p);

	void delete(Long id);

	List<Permission> list();

	List<Permission> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);

	List<Permission> getByRoleId(Long roleId);

    List<String> queryByEmployeeId(Long id);
}
