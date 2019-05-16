package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.UserPojo;
import fyt.business.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SelectLoginExecutor extends BaseExecutor<Object> {

    @Autowired
    private LoginService loginService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("user_phone",userPojo.getUserPhone());
        paraMap.put("user_pwd",userPojo.getUserPassword());
        int re=0;
        //根据用户名查找用户信息
        //查询出用户对象
        List<Map<String,Object>> list = loginService.selectUser(paraMap);
        for(Map<String,Object> map:list) {
            //页面提交的密码 和 查询出的用户对象密码 做比较
            if(map.get("user_password").equals(userPojo.getUserPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("userName",map.get("user_name"));
                session.setAttribute("userId",map.get("user_id"));
                re = 1;
            }else {
                re = 2;
            }
        }
        return re;
    }
}
