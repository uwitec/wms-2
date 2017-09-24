package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends BaseDomain {

	private String name;
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;//是否是超级管理员
	//多对一
	private Department dept;
	//多对多
	private List<Role> roles = new ArrayList<>();

	
	public String getRoleName(){
		StringBuilder sb=new StringBuilder();
		if(roles.size()==0){
			sb.append("[暂未分配角色]");
		}else{
			sb.append("[");
			for (Role role : roles) {
				sb.append(role.getName()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Employee [ name=" + name + ", password=" + password + ", email=" + email + ", age=" + age + ", admin="
				+ admin + "]";
	}

}
