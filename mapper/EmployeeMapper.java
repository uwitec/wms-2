package com._520it.wms.mapper;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

	void save(Employee e);

	void delete(Long id);

	void update(Employee e);

	Employee get(Long id);

	List<Employee> list();

	List<Employee> getListData(QueryObject qo);

	long getTotalCount(QueryObject qo);

	void deleteRelation(Long id);

	void saveRelation(@Param("empId") Long id, @Param("roleId") Long id2);

	void deleteRoleRelation(Long id);

	Employee checkLogin(@Param("username") String username, @Param("password") String password);
	
	void batchDelete(Long []ids);

    Employee queryByname(String username);
}
