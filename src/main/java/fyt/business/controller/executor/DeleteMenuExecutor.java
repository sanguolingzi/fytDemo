package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TestService;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteMenuExecutor extends BaseExecutor<Object> {
    @Autowired
    private TestService testServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel,Object ... obj) {
        int id = new Integer(request.getParameter("menuId"));
        return testServiceImpl.menuDelete(id);
    }
}

