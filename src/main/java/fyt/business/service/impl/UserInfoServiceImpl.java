package fyt.business.service.impl;

import fyt.business.mapper.UserInfoMapper;
import fyt.business.pojo.UserPojo;
import fyt.business.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService
{
    @Autowired
    private UserInfoMapper mapper;


    @Override
    public List<Map<String, Object>> SelectTree(Map<String, Object> paraMap){
        List<Map<String, Object>> list = mapper.SelectRole(paraMap);
        for(Map<String, Object> m:list){
            List<Map<String, Object>> list2=mapper.SelectTitle(m);
            m.put("titleList",list2);
            for(Map<String, Object> m2:list2){
                List<Map<String, Object>> list3 = mapper.SelectDadMenu(m2);
                m2.put("menuDadList",list3);
                for(Map<String, Object> m3:list3){
                    m3.put("menuList",mapper.SelectMenu(m2));
                }
            }
        }
        return list;
    }
}