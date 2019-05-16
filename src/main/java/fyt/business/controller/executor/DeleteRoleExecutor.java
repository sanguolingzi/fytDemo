package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.RolePojo;
import fyt.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteRoleExecutor extends BaseExecutor<Object> {


    @Autowired
    private RoleService roleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        RolePojo rolePojo = (RolePojo)businessModel;
        return roleService.delete(rolePojo.getRoleId());
    }

}
