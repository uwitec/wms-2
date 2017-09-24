package com._520it.wms.web.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.util.ActionContextUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//判断用户是否有访问当前Action中对应方法的权限
		Set<String> expressions = ActionContextUtil.getUserPermission();
		//放行的情况一:当前用户是超级管理员
		Employee e = ActionContextUtil.getCurrentUser();
		if (e.getAdmin()) {
			return invocation.invoke();
		}
		//放行的情况二:当前的访问的方法上面没有权限相关的注解
		//获取当访问的Action对象
		Object action = invocation.getProxy().getAction();
		//获取到访问的方法的名称
		String methodName = invocation.getProxy().getMethod();
		Method method = action.getClass().getMethod(methodName);
		if (!method.isAnnotationPresent(RequiredPermission.class)) {
			return invocation.invoke();
		}
		//放行的情况二:当前用户有权限的时候
		//获取到当前访问的资源的权限表达式
		String actionName = action.getClass().getName();
		String expression = actionName + ":" + methodName;
 		if (!expressions.contains(expression)) {
			//给用户提示信息:跳转到一个提示页面
			return "nopermission";
		}

		return invocation.invoke();
	}
}
