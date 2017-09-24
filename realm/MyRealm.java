package com._520it.wms.realm;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Role;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Setter
    private IEmployeeService employeeService;

    @Setter
    private IRoleService roleService;

    @Setter
    private IPermissionService permissionService;

    /**
     * 登陆认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        Employee employee = employeeService.queryByname(username);

        if (!employee.getName().equals(username)) {

            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes(employee.getName()), this.getAuthorizationCacheName());
        return info;
    }

    /**
     * 授权
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Employee employee = (Employee) principals.getPrimaryPrincipal();

        List<String> roles = null;
        List<String> permissions = null;
        if (employee.getAdmin()) {
            roles=new ArrayList<>();
            permissions=new ArrayList<>();
            for (Role role : roleService.list()) {
                roles.add(role.getSn());
            }
            permissions.add("*:*");
        }else{
            roles=roleService.queryByEmployeeId(employee.getId());
            permissions=permissionService.queryByEmployeeId(employee.getId());
        }
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
}
