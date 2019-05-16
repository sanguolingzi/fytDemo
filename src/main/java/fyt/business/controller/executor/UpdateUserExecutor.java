package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.RolePojo;
import fyt.business.pojo.UserPojo;
import fyt.business.service.RoleService;
import fyt.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class UpdateUserExecutor extends BaseExecutor<Object> {


    @Autowired
    private UserService userService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        System.out.println(userPojo.getUserSex());
        paraMap.put("user_id",userPojo.getUserId());
        paraMap.put("user_name",userPojo.getUserName());
        paraMap.put("user_password",userPojo.getUserPassword());
        paraMap.put("user_phone",userPojo.getUserPhone());
        paraMap.put("user_mail",userPojo.getUserMail());
        paraMap.put("user_sex",userPojo.getUserSex());
        return userService.UpdateUser(paraMap);
    }

}
