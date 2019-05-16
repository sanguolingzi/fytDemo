package fyt.business.mapper;

import fyt.business.pojo.UserPojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends Mapper<UserPojo> {
    List<Map<String,Object>> SelectRole(Map<String, Object> paraMap);

    List<Map<String,Object>> SelectTitle(Map<String, Object> paraMap);

    List<Map<String,Object>> SelectMenu(Map<String, Object> paraMap);

    List<Map<String,Object>> SelectDadMenu(Map<String, Object> paraMap);
}