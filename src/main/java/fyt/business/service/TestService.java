package fyt.business.service;

import java.util.List;
import java.util.Map;

public interface TestService {

    int testInsert(String name);

    int testUpdate(String name,int id);

    List<Map<String,Object>> testSelect(Map<String,Object> paraMap);
}
