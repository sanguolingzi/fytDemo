package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.annotation.ResultBeanResponseBody;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.model.MenuNode;
import fyt.business.model.TestModel;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController()
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testServiceImpl;

    @RequestMapping(value="index.do")
    public ModelAndView test(ModelAndView mv){
        mv.addObject("list",testServiceImpl.testSelect(new HashMap()));
        System.out.println("here");
        mv.setViewName("abcd");
        return mv;
    }


    @RequestMapping(value="json.do")
    @ResultBeanResponseBody
    public Object test(){
        return testServiceImpl.testSelect(new HashMap());
    }

    @RequestMapping(value="testPage")
    @ResponseBody
    public Object testPage(TestModel testModel){
    	  //asd asd as das asdasdasdas d
        return testServiceImpl.testPageSelect(testModel);
    }


    @RequestMapping(value="testInsert.do")
    public String testInsert(){
        testServiceImpl.testInsert("李斯");
        System.out.println("testInsert");
        return "testInsert";
    }


    @RequestMapping(value="testUpdate.do")
    public String testUpdate(){
        testServiceImpl.testUpdate("李三",3);
        System.out.println("testUpdate");
        return "testUpdate";
    }

    /*@RequestMapping(value="menuSelectAll.do")
    public Object selectTitle(HttpServletRequest request, BusinessModel businessModel){
        BaseExecutor executor = SpringContextUtil.getBean("menuSelectAll");
        return executor.execute(request,businessModel);
    }*/

    /*@RequestMapping("Insert.do")
    @ResponseBody
    public Object InsertMenu(String menu_name,String menu_lastname,String menu_location,String menu_state){
        return  testServiceImpl.menuInsert(menu_name,menu_lastname,menu_location,menu_state);
    }*/

    /*@RequestMapping("Delete.do")
    @ResponseBody
    public Object menuDelete(String menu_id){
        int id = new Integer(menu_id);
        return testServiceImpl.menuDelete(id);
    }*/

    /*@RequestMapping("Updata")
    @ResponseBody
    public Object menuUpdata(String menu_name,String menu_lastname,String menu_location,String menu_state,String menu_id){
        int id = new Integer(menu_id);
        return  testServiceImpl.menuUpdata(menu_name,menu_lastname,menu_location,menu_state,id);
    }*/

    /*@RequestMapping("MenuName")
    @ResponseBody
    public Object menuName(){
        return testServiceImpl.menuName(new HashMap());
    }*/
}
