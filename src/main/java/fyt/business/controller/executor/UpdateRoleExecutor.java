package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.RolePojo;
import fyt.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class UpdateRoleExecutor extends BaseExecutor<Object> {


    @Autowired
    private RoleService roleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        RolePojo rolePojo = (RolePojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("role_id",rolePojo.getRoleId());
        paraMap.put("role_name",rolePojo.getRoleName());
        paraMap.put("role_state",rolePojo.getRoleState());
        return roleService.updateRole(paraMap);
    }

}
