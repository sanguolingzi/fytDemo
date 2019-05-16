package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.UserPojo;
import fyt.business.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegisterExecutor extends BaseExecutor<Object> {

    @Autowired
    private RegisterService registerService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("user_name",userPojo.getUserName());
        paraMap.put("user_password",userPojo.getUserPassword());
        paraMap.put("user_phone",userPojo.getUserPhone());
        paraMap.put("user_mail",userPojo.getUserMail());
        paraMap.put("user_sex",userPojo.getUserSex());
        paraMap.put("verify_code",request.getSession().getAttribute("validation_code"));
        paraMap.put("verify_code2",request.getParameter("code"));
        return registerService.selectRegister(paraMap);
    }
}
