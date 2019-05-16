package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.RoleService;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class SelectRoleTitleExecutor extends BaseExecutor<Object> {

    @Autowired
    private RoleService roleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int i = new Integer(request.getParameter("roleId"));
        List<Map<String,Object>> list1 = roleService.selectTitle();
        List<Map<String,Object>> list2 = roleService.selectRoleTitle(i);
        List<Map<String,Object>> list3 = roleService.selectName(i);
        for(Map<String,Object> paraMap:list1){
                for(Map<String,Object> paraMap2:list2){
                    if(paraMap.get("title_id")==paraMap2.get("title_id")){
                        paraMap.put("checked",1);
                        break;
                    }else {
                        paraMap.put("checked",0);
                    }
                }
        }
        for(Map<String,Object> m:list3){
            list1.add(m);
        }
        return list1;
    }
}
