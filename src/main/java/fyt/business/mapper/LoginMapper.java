package fyt.business.mapper;

import fyt.business.pojo.UserPojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface LoginMapper extends Mapper<UserPojo> {
    List<Map<String,Object>> SelectLogin(Map<String,Object> paraMap);

    List<Map<String,Object>> selectUser(Map<String,Object> paraMap);
}