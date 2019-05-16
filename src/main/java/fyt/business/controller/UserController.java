package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.pojo.UserPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("user")
public class UserController {

    @RequestMapping(value="userSelect.do")
    public Object selectUser(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("userSelectAllExecutor");
        return executor.execute(request,userPojo);
    }

    @RequestMapping(value="userInsert.do")
    public Object insertUser(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("insertUserExecutor");
        return executor.execute(request,userPojo);
    }

    @RequestMapping(value="userDelete.do")
    public Object DeletetUser(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("deleteUserExecutor");
        return executor.execute(request,userPojo);
    }

    @RequestMapping(value="userUpdate.do")
    public Object UpdateUser(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("updateUserExecutor");
        return executor.execute(request,userPojo);
    }

    @RequestMapping(value="selectUserRole.do")
    public Object selectUserRole(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("selectUserRoleExecutor");
        return executor.execute(request,userPojo);
    }

    @RequestMapping(value="insertUserRole.do")
    public Object insertUserRole(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("insertUserRoleExecutor");
        return executor.execute(request,userPojo);
    }
}
