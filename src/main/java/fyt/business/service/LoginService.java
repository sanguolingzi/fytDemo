package fyt.business.service;

import fyt.business.pojo.UserPojo;

import java.util.List;
import java.util.Map;

public interface LoginService {
    List<Map<String,Object>> SelectLogin(Map<String, Object> paraMap);

    List<Map<String,Object>> selectUser(Map<String, Object> paraMap);
}