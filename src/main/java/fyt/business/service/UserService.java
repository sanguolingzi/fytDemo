package fyt.business.service;

import fyt.business.pojo.UserPojo;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String,Object>> SelectUser(Map<String,Object> paraMap);

    int add(UserPojo userPojo);

    int DeleteUser(int userId);

    int UpdateUser(Map<String,Object> paraMap);

    List<Map<String,Object>> selectRole();

    List<Map<String,Object>> selectUserRole(int userId);

    int insertUserRole(Map<String,Object> paraMap);
}