package fyt.business.service.impl;

import fyt.business.mapper.LoginMapper;
import fyt.business.mapper.UserMapper;
import fyt.business.pojo.UserPojo;
import fyt.business.service.LoginService;
import fyt.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private LoginMapper mapper;

    @Override
    public List<Map<String, Object>> SelectLogin(Map<String, Object> paraMap) {
    List<Map<String, Object>> list = mapper.SelectLogin(paraMap);
    System.out.println(list);
    if(list!=null){
        return list;
    }else {
        return null;
    }
}

    @Override
    public List<Map<String, Object>> selectUser(Map<String, Object> paraMap) {
        List<Map<String, Object>> list = mapper.selectUser(paraMap);
        if(list!=null){
            return list;
        }else {
            return null;
        }
    }
}