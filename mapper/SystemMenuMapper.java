package com._520it.wms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;

public interface SystemMenuMapper {
	void save(SystemMenu entity);
	
	void update(SystemMenu entity);
	
	void delete(Long id);
	
    SystemMenu get(Long id);
    
	List<SystemMenu> list();
	
    Long getTotalCount(SystemMenuQueryObject qo);
    
    List<SystemMenu> getListData(SystemMenuQueryObject qo);

	List<Map<String, Object>> queryMenuByParentSnAndEmployeeId(@Param("parentSn")String parentSn,@Param("empId") Long empId);

	List<Map<String, Object>> queryMenusByParentSn(String parentSn);

}