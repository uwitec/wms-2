package com._520it.wms.service;
import java.util.List;
import com._520it.wms.domain.Student;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StudentQueryObject;

public interface IStudentService {
	void delete(Long id);
	
	void save(Student entity);
	
    Student get(Long id);
    
    List<Student> list();
    
	void update(Student entity);
	
	PageResult pageQuery(StudentQueryObject qo);
}
