package fyt.business.mapper;

import fyt.business.pojo.UserPojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RegisterMapper extends Mapper<UserPojo> {
    List<Map<String,Object>> selectUserName(Map<String, Object> paraMap);

    List<Map<String,Object>> selectUserPhone(Map<String, Object> paraMap);

    List<Map<String,Object>> selectUserMail(Map<String, Object> paraMap);

    int insertUserinfo(Map<String,Object> paraMap);
}