package fyt.business.controller;

import fyt.business.core.annotation.JSON;
import fyt.business.core.annotation.ResultBeanResponseBody;
import fyt.business.model.TestModel;
import fyt.business.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testServiceImpl;

    @RequestMapping(value="index")
    public ModelAndView test(ModelAndView mv){
        mv.addObject("list",testServiceImpl.testSelect(new HashMap<>()));
        System.out.println("here");
        mv.setViewName("abcd");
        return mv;
    }


    @RequestMapping(value="json")
    @ResultBeanResponseBody
    public Object test(){
        return testServiceImpl.testSelect(new HashMap<>());
    }

    @RequestMapping(value="testPage")
    @ResponseBody
    public Object testPage(TestModel testModel){
        return testServiceImpl.testPageSelect(testModel);
    }


    @RequestMapping(value="testInsert")
    public String testInsert(){
        testServiceImpl.testInsert("李斯");
        System.out.println("testInsert");
        return "testInsert";
    }


    @RequestMapping(value="testUpdate")
    public String testUpdate(){
        testServiceImpl.testUpdate("李三",3);
        System.out.println("testUpdate");
        return "testUpdate";
    }
}
