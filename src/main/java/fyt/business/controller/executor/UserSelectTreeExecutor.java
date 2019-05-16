package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.UserPojo;
import fyt.business.service.LoginService;
import fyt.business.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserSelectTreeExecutor extends BaseExecutor<Object> {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        Map<String, Object> paraMap = new HashMap();
        paraMap.put("user_id",userPojo.getUserId());
        return userInfoService.SelectTree(paraMap);
    }
}
