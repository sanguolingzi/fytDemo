package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class InsertTitleMenuExecutor extends BaseExecutor<Object> {
    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request,BusinessModel businessModel,Object... obj) {
        Map<String,Object> paraMap = new HashMap();
        String [] str = request.getParameterValues("Arr");
        int end=0;
        for (int i=0;i<str.length;i++){
            int j = new Integer(str[i]);
            paraMap.put("title_id",1);
            paraMap.put("menu_id",j);
            if(i==str.length-1){
                end = titleService.insertTitleMenu(paraMap);
            }else {
                titleService.insertTitleMenu(paraMap);
            }
        }
        return end;
    }
}
