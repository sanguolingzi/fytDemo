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
    public PageData menuSelectAll(String menu_name,String menu_lastid,String menu_id,MenuNode menuNode) {
        PageHelper.startPage(menuNode.getCurrentPage(),menuNode.getPageSize());
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("menu_name",menu_name);
        paraMap.put("menu_lastid",menu_lastid);
        paraMap.put("menu_id",menu_id);
        List<Map<String,Object>> list = customerBusiMapper.menuSelect(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
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
