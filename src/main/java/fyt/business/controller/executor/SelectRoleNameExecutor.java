package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.RoleService;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SelectRoleNameExecutor extends BaseExecutor<Object> {

    @Autowired
    private RoleService roleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int i = new Integer(request.getParameter("roleId"));
        return roleService.selectName(i);
    }
}
