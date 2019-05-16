package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.pojo.UserPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("register")
public class RegisterController {

    @RequestMapping(value="register.do")
    public Object selectLgoin(HttpServletRequest request, UserPojo userPojo){
        BaseExecutor executor = SpringContextUtil.getBean("registerExecutor");
        return executor.execute(request,userPojo);
    }
}
