package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.model.MenuNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("menu")
public class MenuController {
    @RequestMapping(value="menuSelectAll.do")
    public Object selectMenu(HttpServletRequest request, MenuNode menuNode){
        BaseExecutor executor = SpringContextUtil.getBean("menuSelectAllExecutor");
        return executor.execute(request,menuNode);
    }

    @RequestMapping(value="insertMenu.do")
    public Object insertMenu(HttpServletRequest request, MenuNode menuNode){
        BaseExecutor executor = SpringContextUtil.getBean("insertMenuExecutor");
        return executor.execute(request,menuNode);
    }

    @RequestMapping(value="deleteMenu.do")
    public Object deleteMenu(HttpServletRequest request, MenuNode menuNode){
        BaseExecutor executor = SpringContextUtil.getBean("deleteMenuExecutor");
        return executor.execute(request,menuNode);
    }

    @RequestMapping(value="updataMenu.do")
    public Object updataMenu(HttpServletRequest request, MenuNode menuNode){
        BaseExecutor executor = SpringContextUtil.getBean("updataMenuExecutor");
        return executor.execute(request,menuNode);
    }

    @RequestMapping(value="menuName.do")
    public Object menuName(HttpServletRequest request, MenuNode menuNode){
        BaseExecutor executor = SpringContextUtil.getBean("menuNameExecutor");
        return executor.execute(request,menuNode);
    }
}
