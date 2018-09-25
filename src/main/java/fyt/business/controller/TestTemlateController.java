package fyt.business.controller;


import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.model.base.BusinessModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("template")
public class TestTemlateController {



    @RequestMapping(value="testTemplate.do")
    public Object testTemplate(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("testExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="addTk.do")
    public Object AddTk(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("addTkExecutor");
        return executor.execute(request,businessModel);
    }


    @RequestMapping(value="updateTk.do")
    public Object updateTk(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("updateTkExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="getTk.do")
    public Object getTk(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("getTkExecutor");
        return executor.execute(request,businessModel);
    }

    @RequestMapping(value="getTkList.do")
    public Object getTkList(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("getTkListExecutor");
        return executor.execute(request,businessModel);
    }

}
