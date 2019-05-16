package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.RolePojo;
import fyt.business.pojo.TestTk;
import fyt.business.service.RoleService;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@Component
public class InsertRoleExecutor extends BaseExecutor<Object> {


    @Autowired
    private RoleService roleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        RolePojo rolePojo = (RolePojo)businessModel;
        rolePojo.setCurrentPage(null);
        rolePojo.setPageSize(null);
        rolePojo.setRoleDelete(1);
        return roleService.add(rolePojo);
    }

}
