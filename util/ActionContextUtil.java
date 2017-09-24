package com._520it.wms.util;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

public class ActionContextUtil {

	private static final String EXPRESSION_IN_SESSION = "EXPRESSION_IN_SESSION";
	private static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";

	private ActionContextUtil() {
	}

	public static void setCurrentUser(Employee value) {
		if (value == null) {
			ActionContext.getContext().getSession().remove(EMPLOYEE_IN_SESSION);
		} else {
			ActionContext.getContext().getSession()
					.put(EMPLOYEE_IN_SESSION, value);
		}
	}

	public static void setUserPermission(Set<String> value) {

		if (value == null) {
			ActionContext.getContext().getSession()
					.remove(EXPRESSION_IN_SESSION);
		} else {
			ActionContext.getContext().getSession()
					.put(EXPRESSION_IN_SESSION, value);
		}
	}

	public static Employee getCurrentUser() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return (Employee) session.getAttribute(EMPLOYEE_IN_SESSION);

	}

	public static Set<String> getUserPermission() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return (Set<String>) session.getAttribute(EXPRESSION_IN_SESSION);
	}
}
