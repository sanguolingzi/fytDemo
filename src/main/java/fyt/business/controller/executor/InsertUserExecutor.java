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

@Component
public class InsertUserExecutor extends BaseExecutor<Object> {


    @Autowired
    private UserService userService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        userPojo.setCurrentPage(null);
        userPojo.setPageSize(null);
        userPojo.setUserDelete(1);
        return userService.add(userPojo);
    }

}
