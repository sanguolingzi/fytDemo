package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestExecutor extends BaseExecutor<String> {

    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public String exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) throws Exception {
        //testTkServiceImpl.testTransaction();
        Map<String,Object> map = new HashMap<>();
        map.put("hashId",obj[0].toString());
        testTkServiceImpl.insertTestAdd(map);
        return "success";
    }
}
