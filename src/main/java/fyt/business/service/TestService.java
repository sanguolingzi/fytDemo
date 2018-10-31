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

    List<Map<String,Object>> menuSelectAll(Map<String,Object> paraMap);

    int menuInsert(Map<String,Object> paraMap);

    int menuUpdata(Map<String,Object> paraMap);

    int menuDelete(int menu_id);

    List<Map<String,Object>> menuName(Map<String,Object> paraMap);
}
