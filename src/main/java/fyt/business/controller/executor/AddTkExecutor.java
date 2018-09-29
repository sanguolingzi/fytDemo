package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.Date;

@Component
public class AddTkExecutor extends BaseExecutor<String> {


    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public String exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {

        TestTk testTk = new TestTk();
        testTk.setAge(new Random().nextInt(100));
        testTk.setCreateTime(new Date());
        testTk.setDescription(String.valueOf(new Long(System.currentTimeMillis())));
        testTk.setName("asd你好我的天");
        testTkServiceImpl.add(testTk);
        return "success";
    }

}
