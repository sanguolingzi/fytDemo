package fyt.business.service;

import fyt.business.model.MenuNode;
import fyt.business.model.TestModel;
import fyt.business.model.base.PageData;

import java.util.List;
import java.util.Map;

public interface TestService {

    int testInsert(String name);

    int testUpdate(String name,int id);

    List<Map<String,Object>> testSelect(Map<String,Object> paraMap);

    PageData testPageSelect(TestModel testModel);

    PageData menuSelectAll(String menu_name,String menu_lastid,String menu_id,MenuNode menuNode);


    int menuInsert(String menu_name,String menu_lastname,String menu_location,String menu_state);

    int menuUpdata(String menu_name,String menu_lastname,String menu_location,String menu_state,int menu_id);

    int menuDelete(int menu_id);

    List<Map<String,Object>> menuName(Map<String,Object> paraMap);
}
