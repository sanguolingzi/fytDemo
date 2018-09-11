package fyt.business.service;

import fyt.business.model.TestModel;
import fyt.business.model.base.PageData;

import java.util.List;
import java.util.Map;

public interface TestService {

    int testInsert(String name);

    int testUpdate(String name,int id);

    List<Map<String,Object>> testSelect(Map<String,Object> paraMap);

    PageData testPageSelect(TestModel testModel);
}
