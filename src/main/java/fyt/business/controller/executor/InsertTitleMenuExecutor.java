package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InsertTitleMenuExecutor extends BaseExecutor<Object> {
    @Autowired
    private TitleService titleService;

    @Override
    public Object exectueBusiness(HttpServletRequest request,BusinessModel businessModel,Object... obj) {
        Map<String,Object> paraMap = new HashMap();
        int id = new Integer(request.getParameter("titleId"));
        String [] str = request.getParameterValues("Arr");
        List<Map<String,Object>> list2 = titleService.selectMenuid(id);
        int end=0;
        System.out.println(list2.size());
        if(list2.size()!=0){
            for (int i=0;i<str.length;i++){
                int num = 0;
                for(Map<String,Object> M : list2){
                    int j = new Integer(str[i]);
                    int k = (int)M.get("menu_id");
                    if(j==k){
                        break;
                    }
                    num++;
                    if(num == list2.size()){
                        paraMap.put("title_id",id);
                        paraMap.put("menu_id",str[i]);
                        end = titleService.insertTitleMenu(paraMap);
                    }
                }
            }
        }else{
            for (int i=0;i<str.length;i++){
                    paraMap.put("title_id",id);
                    paraMap.put("menu_id",str[i]);
                    end = titleService.insertTitleMenu(paraMap);
                }
        }

        return end;
    }
}
