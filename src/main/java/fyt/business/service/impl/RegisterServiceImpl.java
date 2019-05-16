package fyt.business.service.impl;

import fyt.business.mapper.RegisterMapper;
import fyt.business.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService
{
    @Autowired
    private RegisterMapper mapper;


    @Override
    public int selectRegister(Map<String, Object> paraMap) {
        for(int i=0;i<4;i++){
            if(i==0){
                List<Map<String, Object>> list = mapper.selectUserName(paraMap);
                if(list.size()!=0){
                    return 1;
                }
            }else if(i==1){
                List<Map<String, Object>> list = mapper.selectUserPhone(paraMap);
                if(list.size()!=0){
                    return 2;
                }
            }else if(i==2){
                List<Map<String, Object>> list = mapper.selectUserMail(paraMap);
                if(list.size()!=0){
                    return 3;
                }
            }else if(i==3){
                String code1 = (String)paraMap.get("verify_code");
                String code2 = (String)paraMap.get("verify_code2");
                if(!code1.equals(code2)){
                    return 4;
                }
            }
        }
        mapper.insertUserinfo(paraMap);
        return 0;
    }
}

/*
if(list.size()==0){
    list = mapper.selectUserPhone(paraMap);
        if(list.size()==0){
    list = mapper.selectUserMail(paraMap);
        return list;
    }else {
        return list;
        }
    }else {
        return list;
   }
*/
