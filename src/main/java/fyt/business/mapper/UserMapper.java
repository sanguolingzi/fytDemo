package fyt.business.mapper;

import fyt.business.pojo.UserPojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<UserPojo> {
    List<Map<String,Object>> SelectUser (Map<String,Object> paraMap);

    int DeleteUser(int userId);

    int UpdateUser(Map<String,Object> paraMap);

    List<Map<String,Object>> selectRole();

    List<Map<String,Object>> selectUserRole(int userId);

    int insertUserRole (Map<String,Object> paraMap);
}