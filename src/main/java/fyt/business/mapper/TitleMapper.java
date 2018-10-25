package fyt.business.mapper;

import fyt.business.pojo.TitlePojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TitleMapper extends Mapper<TitlePojo> {
    List<Map<String,Object>> selectTitle(Map<String,Object> paraMap);

    List<Map<String,Object>> selectTitleMenu(Map<String,Object> paraMap);

    List<Map<String,Object>> selectSubmenu(int i);

    int insertTitle(Map<String,Object> paraMap);

    int updateTitle(Map<String,Object> paraMap);

    int deleteTitle(int i);

    int deleteTitleMenu(int i);

    int insertTitleMenu(Map<String,Object> paraMap);

    List<Map<String,Object>> selectTitleMenuAll(Map<String,Object> paraMap);

    List<Map<String,Object>> selectSubmenuAll(int i);
}