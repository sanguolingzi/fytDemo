package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TitlePojo;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class SelectTitleMenuExecutor extends BaseExecutor<Object> {

    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int i = new Integer(request.getParameter("title_id"));
        return titleService.selectTitleMenu(i);
    }
}
