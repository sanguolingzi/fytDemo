package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.RoleService;
import fyt.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class SelectUserRoleExecutor extends BaseExecutor<Object> {

    @Autowired
    private UserService userService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int i = new Integer(request.getParameter("userId"));
        List<Map<String,Object>> list1 = userService.selectRole();
        List<Map<String,Object>> list2 = userService.selectUserRole(i);
        for(Map<String,Object> paraMap:list1){
                for(Map<String,Object> paraMap2:list2){
                    if(paraMap.get("role_id")==paraMap2.get("role_id")){
                        paraMap.put("checked",1);
                        break;
                    }else {
                        paraMap.put("checked",0);
                    }
                }
        }
        return list1;
    }
}
