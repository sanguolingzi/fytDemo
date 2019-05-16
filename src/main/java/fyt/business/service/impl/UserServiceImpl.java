package fyt.business.service.impl;

import fyt.business.pojo.UserPojo;
import fyt.business.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import fyt.business.mapper.UserMapper;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper mapper;

    @Override
    public List<Map<String, Object>> SelectUser(Map<String, Object> paraMap) {
        return mapper.SelectUser(paraMap);
    }

    @Override
    public int add(UserPojo userPojo) {
        return mapper.insertSelective(userPojo);
    }

    @Override
    public int DeleteUser(int userId) {
        return mapper.DeleteUser(userId);
    }

    @Override
    public int UpdateUser(Map<String, Object> paraMap) {
        return mapper.UpdateUser(paraMap);
    }

    @Override
    public List<Map<String, Object>> selectRole() {
        return mapper.selectRole();
    }

    @Override
    public List<Map<String, Object>> selectUserRole(int userId) {
        return mapper.selectUserRole(userId);
    }

    @Override
    public int insertUserRole(Map<String, Object> paraMap) {
        return mapper.insertUserRole(paraMap);
    }


}