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
    public List<Map<String,Object>> menuSelectAll(Map<String,Object> paraMap) {
        List<Map<String,Object>> list = customerBusiMapper.menuSelect(paraMap);
        return list;
    }

    @Override
    public int menuInsert(Map<String,Object> paraMap) {
        int lastid =-1;
        if (paraMap.get("menu_lastname")!=""&&paraMap.get("menu_lastname")!=null){
            String lastname = (String) paraMap.get("menu_lastname");
            lastid = customerBusiMapper.selectId(lastname);
        }
        paraMap.put("menu_lastid",lastid);
        try {
            return customerBusiMapper.menuInsert(paraMap);
        }catch (Exception e){
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int menuUpdata(Map<String,Object> paraMap) {
        int lastid =-1;
        if (paraMap.get("menu_lastname")!=""&&paraMap.get("menu_lastname")!=null){
            String lastname = (String) paraMap.get("menu_lastname");
            lastid = customerBusiMapper.selectId(lastname);
        }
        paraMap.put("menu_lastid",lastid);
        return customerBusiMapper.menuUpdata(paraMap);
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
