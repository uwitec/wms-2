package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	@Override
	public void reload() {
		//获取到所有的权限表达式
		List<Permission> list = mapper.list();
		//定义一个集合存放所有的表达式
		Set<String> expressions = new HashSet<>();
		for (Permission permission : list) {
			String expression = permission.getExpression();
			expressions.add(expression);
		}

		//获取到所有继承自BaseAction的Action对象
		Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
		//取出Map中所有的Action对象
		Collection<BaseAction> actions = actionMap.values();
		Iterator<BaseAction> iterator = actions.iterator();
		while (iterator.hasNext()) {
			BaseAction action = iterator.next();
			//获取Action中的所有的方法
			Method[] methods = action.getClass().getDeclaredMethods();
			for (Method method : methods) {
				//判断方法上是否有对应的注解
				if (method.isAnnotationPresent(RequiredPermission.class)) {
					RequiredPermission requiredPermission = method.getAnnotation(RequiredPermission.class);
					//获取注解中传递的数据:权限的名称
					String permissionName = requiredPermission.value();
					//拼接权限表达式:Action的全限定名:方法名
					String actionName = action.getClass().getName();
					String methodName = method.getName();
					String expression = actionName + ":" + methodName;
					//判断当前的表达式在数据库中是否存在
					//存在:就不再执行保存操作
					//不存在:保存
					if (!expressions.contains(expression)) {
						//将权限相关的数据保存到数据库中
						Permission p = new Permission();
						p.setName(permissionName);
						p.setExpression(expression);
						mapper.save(p);
					}
				}
			}
		}
	}

	@Override
	public List<String> queryByEmployeeId(Long id) {
		return mapper.queryByEmployeeId(id);
	}

	@Setter
	private PermissionMapper mapper;

	@Override
	public void save(Permission p) {
		mapper.save(p);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public List<Permission> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = mapper.getTotalCount(qo);
		if (totalCount == 0) {
			return PageResult.emptyResult;
		}
		List<Permission> listData = mapper.getListData(qo);

		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount.intValue(), listData);
	}

}
