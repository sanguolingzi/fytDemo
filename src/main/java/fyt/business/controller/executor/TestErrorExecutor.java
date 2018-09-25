package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TestErrorExecutor extends BaseExecutor<String> {

    @Override
    public String exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        return "asdasdasdasdasdasdasd";
    }

    public boolean validateParam(HttpServletRequest request, BusinessModel businessModel,Object...obj){

        return true;
    }
}
