package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@Component
public class UpdateTkExecutor extends BaseExecutor<String> {


    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public String exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        TestTk testTk = new TestTk();
        testTk.setId(1);
        testTk.setCreateTime(new Date());
        testTkServiceImpl.update(testTk);
        return "success";
    }
}
