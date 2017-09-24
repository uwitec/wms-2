package com._520it.wms.service;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {
	void save(Permission p);

	void delete(Long id);

	List<Permission> list();

	PageResult pageQuery(QueryObject qo);

	void reload();

    List<String> queryByEmployeeId(Long id);
}
