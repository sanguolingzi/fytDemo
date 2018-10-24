package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.model.base.BusinessModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("title")
public class TitleController {

    @RequestMapping(value="titleSelect.do")
    public Object selectTitle(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("selectTitleExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="selectMenu.do")
    public Object selectTitleMenu(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("selectTitleMenuExecutor");
        return executor.execute(request,businessModel);
    }
}
