package fyt.business.mapper;

import java.util.List;
import java.util.Map;

public interface CustomerBusiMapper{
    int testInsert(Map<String,Object> paraMap);

    int testUpdate(Map<String,Object> paraMap);

    List<Map<String,Object>> testSelect(Map<String,Object> paraMap);

    List<Map<String,Object>> menuSelect(Map<String,Object> paraMap);

    int menuInsert(Map<String,Object> paraMap);

    int selectId(String menu_lastname);

    int menuUpdata(Map<String,Object> paraMap);

    int menuDelete(int menu_id);

    List<Map<String,Object>> selectMenuName(Map<String,Object> paraMap);
}