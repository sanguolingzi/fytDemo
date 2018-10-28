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

    @RequestMapping(value="titleSelectName.do")
    public Object selectTitleName(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("selectTitleNameExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="selectMenu.do")
    public Object selectTitleMenu(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("selectTitleMenuExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="selectMenuAll.do")
    public Object selectTitleMenuAll(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("selectTitleMenuAllExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="insertTitle.do")
    public Object insertTitle(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("insertTitleExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="insertTitleMenu.do")
    public Object insertTitleMenu(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("insertTitleMenuExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="updataTitle.do")
    public Object updataTitle(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("updataTitleExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="deleteTitle.do")
    public Object deleteTitle(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("deleteTitleExecutor");
        return executor.execute(request,businessModel);
    }

}
