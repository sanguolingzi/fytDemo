package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TestExecutor extends BaseExecutor<String> {

    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public String exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) throws Exception {
        testTkServiceImpl.testTransaction();
        return "success";

    }
}
