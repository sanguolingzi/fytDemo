package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.RolePojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("role")
public class RoleController {

    @RequestMapping(value="roleSelect.do")
    public Object selectRole(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("roleSelectAllExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="roleInsert.do")
    public Object insertRole(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("insertRoleExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="roleDelete.do")
    public Object deletetRole(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("deleteRoleExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="roleUpdate.do")
    public Object updateRole(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("updateRoleExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="roleName.do")
    public Object selectName(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("selectRoleNameExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="roleTitle.do")
    public Object selectRoleTitle(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("selectRoleTitleExecutor");
        return executor.execute(request,rolePojo);
    }

    @RequestMapping(value="insertRoleTitle.do")
    public Object insertRoleTitle(HttpServletRequest request, RolePojo rolePojo){
        BaseExecutor executor = SpringContextUtil.getBean("insertRoleTitleExecutor");
        return executor.execute(request,rolePojo);
    }
}
