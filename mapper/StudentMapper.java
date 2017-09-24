package com._520it.wms.mapper;

import com._520it.wms.domain.Student;
import com._520it.wms.query.StudentQueryObject;
import java.util.List;

public interface StudentMapper {
	void save(Student entity);
	
	void update(Student entity);
	
	void delete(Long id);
	
    Student get(Long id);
    
	List<Student> list();
	
    Long getTotalCount(StudentQueryObject qo);
    
    List<Student> getListData(StudentQueryObject qo);
}