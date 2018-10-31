package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.model.base.PageModel;
import fyt.business.pojo.TitlePojo;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SelectTitleMenuExecutor extends BaseExecutor<Object> {

    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        int i = new Integer(request.getParameter("titleId"));
        List<Map<String,Object>> list1 = titleService.selectTitleMenuAll();
        List<Map<String,Object>> list2 = titleService.selectTitleMenu(i);
        List<Map<String,Object>> list3 = titleService.selectTitleName(i);
        for(Map<String,Object> paraMap:list1){
            List<Map<String,Object>> map = (List<Map<String,Object>>)paraMap.get("SubmenuList");
            for (Map<String,Object> mapL:map){
                for(Map<String,Object> paraMap2:list2){
                    if(mapL.get("menu_id")==paraMap2.get("menu_id")){
                        mapL.put("checked",1);
                        break;
                    }else {
                        mapL.put("checked",0);
                    }
                }
            }
        }
        for(Map<String,Object> m:list3){
            list1.add(m);
        }
        return list1;
    }
}
