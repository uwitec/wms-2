package com._520it.wms.web.action;

import lombok.Setter;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginAction extends BaseAction {

    @Setter
    private String username;
    @Setter
    private String password;

    @Override
    public String execute()throws Exception{
        HttpServletRequest req = ServletActionContext.getRequest();
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                super.addActionError("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                super.addActionError("用户名/密码错误");
            } else {
                super.addActionError("其他异常信息");//最终在异常处理器生成未知错误
            }
        }

        return "login";
    }
}
