package com._520it.wms.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	public static final String LIST = "list";

	protected void put(String name, Object value) {
		ActionContext.getContext().put(name, value);
	}

	public void putMsg(String msg) throws Exception {
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(msg);
	}

	public void putAjax(Object obj) {
		try {
			ServletActionContext.getResponse().setContentType(
					"text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
