package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetTkListExecutor extends BaseExecutor<Object> {

    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        TestTk testTk = new TestTk();
        return testTkServiceImpl.selectAll(testTk);
    }

    public boolean validateParam(HttpServletRequest request, BusinessModel businessModel,Object...obj){

        return false;
    }
}
