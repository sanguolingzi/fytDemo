package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.base.ErrorMessage;
import fyt.business.core.exception.BusinessException;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@Component
public class GetTkExecutor extends BaseExecutor<Object> {

    @Autowired
    private TestTkService testTkServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj)  throws Exception{
        TestTk testTk = new TestTk();
        testTk.setId(2);
        if(true){
            throw new BusinessException("错误啦");
        }
        return testTkServiceImpl.selectOne(testTk);
    }

    public ErrorMessage validateParam(HttpServletRequest request, BusinessModel businessModel, Object...obj){
        ErrorMessage errorMessage = new ErrorMessage();

        return errorMessage;
    }
}
