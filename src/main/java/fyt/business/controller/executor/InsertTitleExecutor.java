package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class InsertTitleExecutor extends BaseExecutor<Object> {
    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        Map<String,Object> paraMap = new HashMap();
        String title_name = request.getParameter("title_name");
        String title_state = request.getParameter("title_state");
        paraMap.put("title_name",title_name);
        paraMap.put("title_state",title_state);
        return titleService.insertTitle(paraMap);
    }
}
