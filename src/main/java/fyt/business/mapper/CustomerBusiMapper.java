package fyt.business.mapper;

import java.util.List;
import java.util.Map;

public interface CustomerBusiMapper{
    int testInsert(Map<String,Object> paraMap);

    int testUpdate(Map<String,Object> paraMap);

    List<Map<String,Object>> testSelect(Map<String,Object> paraMap);
}