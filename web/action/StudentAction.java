package com._520it.wms.web.action;

import com._520it.wms.domain.Student;
import com._520it.wms.query.StudentQueryObject;
import com._520it.wms.service.IStudentService;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class StudentAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IStudentService service;

	@Getter 
	private StudentQueryObject qo=new StudentQueryObject();

	@Getter private Student student=new Student();

	@RequiredPermission("学生列表")
	public String execute(){
		try {
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("学生编辑")
	public String input() {
		try {
			if (student.getId() != null) {
                student = service.get(student.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("学生保存/更新")
	public String saveOrUpdate() {
		try {
			if (student.getId() == null) {
                service.save(student);
				addActionMessage("保存成功");
            } else {
                service.update(student);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("学生删除")
	public String delete()  throws  Exception {
		try {
			if (student.getId() != null) {
                service.delete(student.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
