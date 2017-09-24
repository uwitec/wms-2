package com._520it.wms.mapper;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

	void save(Role r);

	void delete(Long id);

	void update(Role r);

	Role get(Long id);

	List<Role> list();

	List<Role> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);

	void saveRelation(@Param("roleId") Long id, @Param("permissionId") Long id2);

	void deleteRelation(Long id);
	
	void deleteMenuRelation(Long id);
	
	void savemenuRelation(@Param("roleId") Long roleId,@Param("menuId")Long menuId);

    List<String> queryByEmployeeId(Long id);
}
