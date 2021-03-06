package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.MenuNode;
import fyt.business.model.base.BusinessModel;
import fyt.business.pojo.TitlePojo;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SelectTitleExecutor extends BaseExecutor<Object> {

    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int currentPage = new Integer(request.getParameter("currentPage"));
        int pageSize = new Integer(request.getParameter("pageSize"));
        int totalSize = new Integer(request.getParameter("totalSize"));
        TitlePojo titlePojo = new TitlePojo();
        titlePojo.setCurrentPage(currentPage);
        titlePojo.setPageSize(pageSize);
        titlePojo.setIsLimit(totalSize);
        return titleService.selectTitle(titlePojo);
    }
}
