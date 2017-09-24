package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;
import lombok.Setter;

import java.util.List;

public class RoleServiceImpl implements IRoleService {

	@Setter
	private RoleMapper mapper;

	@Override
	public void save(Role r) {
		mapper.save(r);
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			mapper.saveRelation(r.getId(), permission.getId());
		}
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public void update(Role r) {
		mapper.update(r);
		mapper.deleteRelation(r.getId());
		mapper.deleteMenuRelation(r.getId());
		List<Permission> permissions = r.getPermissions();
		for (Permission permission : permissions) {
			mapper.saveRelation(r.getId(), permission.getId());
		}
		List<SystemMenu> menues = r.getMenues();
		for (SystemMenu menu : menues) {
			mapper.savemenuRelation(r.getId(), menu.getId());
		}
	}

	@Override
	public Role get(Long id) {
		return mapper.get(id);
	}

	@Override
	public List<Role> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = mapper.getTotalCount(qo);
		if (totalCount == 0) {
			return PageResult.emptyResult;
		}
		List<Role> listData = mapper.getListData(qo);

		return new PageResult(qo.getCurrentPage(), qo.getPageSize(),
				totalCount.intValue(), listData);
	}

    @Override
    public List<String> queryByEmployeeId(Long id) {

        return mapper.queryByEmployeeId(id);
    }

}
