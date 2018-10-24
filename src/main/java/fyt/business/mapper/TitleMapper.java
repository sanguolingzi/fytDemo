package fyt.business.mapper;

import fyt.business.pojo.TitlePojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TitleMapper extends Mapper<TitlePojo> {
    List<Map<String,Object>> selectTitle(Map<String,Object> paraMap);

    List<Map<String,Object>> selectTitleMenu(Map<String,Object> paraMap);

    List<Map<String,Object>> selectSubmenu(int i);
}