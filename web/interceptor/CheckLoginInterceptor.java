package com._520it.wms.web.interceptor;

import java.util.Map;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.ActionContextUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//当没有登录的时候,回到登录页面
		Employee employee = ActionContextUtil.getCurrentUser();
		if (employee == null) {
			return Action.LOGIN;
		}
		//放行
		return invocation.invoke();
	}
}
