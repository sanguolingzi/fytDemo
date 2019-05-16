package fyt.business.service;

import fyt.business.pojo.UserPojo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    List<Map<String,Object>> SelectTree(Map<String,Object> paraMap);
}