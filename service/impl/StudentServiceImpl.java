package com._520it.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.page.PageResult;

import com._520it.wms.domain.Student;
import com._520it.wms.mapper.StudentMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StudentQueryObject;
import com._520it.wms.service.IStudentService;
import lombok.Setter;
public class StudentServiceImpl implements IStudentService {
	@Setter
	private StudentMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Student entity) {
		  mapper.save(entity);
	}

	public Student get(Long id) {
		return mapper.get(id);
	}

	public List<Student> list() {
		return mapper.list();
	}

	public void update(Student entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(StudentQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Student> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(qo.getCurrentPage(), qo.getPageSize(), count.intValue(), listData);
		return pageResult;
	}
}
