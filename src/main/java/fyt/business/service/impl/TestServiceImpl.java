package fyt.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.mapper.CustomerBusiMapper;
import fyt.business.model.MenuNode;
import fyt.business.model.TestModel;
import fyt.business.model.base.PageData;
import fyt.business.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("testServiceImpl")
public class TestServiceImpl implements TestService{

    @Autowired
    private CustomerBusiMapper customerBusiMapper;


    public int testInsert(String name) {
        Map<String,Object> param = new HashMap<>();
        param.put("name",name);
        return customerBusiMapper.testInsert(param);
    }
    public int testUpdate(String name, int id) {
        Map<String,Object> param = new HashMap<>();
        param.put("name",name);
        param.put("id",id);
        return customerBusiMapper.testUpdate(param);
    }

    public List<Map<String, Object>> testSelect(Map<String, Object> paraMap) {
        return customerBusiMapper.testSelect(paraMap);
    }

    @Override
    public PageData testPageSelect(TestModel testModel) {
        PageHelper.startPage(testModel.getCurrentPage(),testModel.getPageSize());
        Map<String,Object> paraMap = new HashMap();
        List<Map<String,Object>> list = customerBusiMapper.testSelect(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }

    @Override
    public PageData menuSelectAll(MenuNode menuNode) {
        PageHelper.startPage(menuNode.getCurrentPage(),menuNode.getPageSize());
        Map<String,Object> paraMap = new HashMap();
        List<Map<String,Object>> list = customerBusiMapper.menuSelect(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }

    @Override
    public PageData menuSelectBy(String menu_name,String menu_lastid,MenuNode menuNode) {
        String name = "";
        int lastid = 0;
        PageHelper.startPage(menuNode.getCurrentPage(),menuNode.getPageSize());
        PageData<Map<String,Object>> pageData = null;
        Map<String,Object> paraMap = new HashMap();
        if(menu_name!=""&&menu_lastid==""){
            name = "%"+menu_name+"%";
            paraMap.put("menu_name",name);
        }else if(menu_name==""&&menu_lastid!=""){
            lastid = new Integer(menu_lastid);
            paraMap.put("menu_lastid",lastid);
        }else if(menu_name!=""&&menu_lastid!=""){
            name = "%"+menu_name+"%";
            lastid = new Integer(menu_lastid);
            paraMap.put("menu_name",name);
            paraMap.put("menu_lastid",lastid);
            List<Map<String,Object>> list = customerBusiMapper.menuSelectBy(paraMap);
            PageInfo pageInfo = new PageInfo(list);
            pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        }
        return pageData;
    }

    @Override
    public int menuInsert(String menu_name, String menu_lastname, String menu_location, String menu_state) {
        int lastid = customerBusiMapper.selectId(menu_lastname);

        Map<String,Object> param = new HashMap<>();
        param.put("name", menu_name);
        param.put("lastid", lastid);
        param.put("lastname", menu_lastname);
        param.put("location", menu_location);
        param.put("state", menu_state);
        try {
            return customerBusiMapper.menuInsert(param);
        }catch (Exception e){
            e.getMessage();
            return 0;
        }

    }

    @Override
    public int menuUpdata(String menu_name,String menu_lastname,String menu_location,String menu_state,int menu_id) {
        int lastid = customerBusiMapper.selectId(menu_lastname);
        Map<String,Object> param = new HashMap<>();
        param.put("menu_name", menu_name);
        param.put("menu_lastid", lastid);
        param.put("menu_lastname", menu_lastname);
        param.put("menu_location", menu_location);
        param.put("menu_state", menu_state);
        param.put("menu_id", menu_id);
        return customerBusiMapper.menuUpdata(param);
    }

    @Override
    public int menuDelete(int menu_id){
        return customerBusiMapper.menuDelete(menu_id);
    }

    @Override
    public List<Map<String,Object>> menuName(Map<String,Object> paraMap) {
        return customerBusiMapper.selectMenuName(paraMap);
    }
}
