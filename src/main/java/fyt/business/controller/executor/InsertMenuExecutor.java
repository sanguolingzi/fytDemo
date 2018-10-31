package fyt.business.controller.executor;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.MenuNode;
import fyt.business.model.base.BusinessModel;
import fyt.business.service.TestService;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class InsertMenuExecutor extends BaseExecutor<Object> {
    @Autowired
    private TestService testServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        MenuNode menuNode = (MenuNode) businessModel;
        Map<String,Object> paraMap = new HashMap();

        paraMap.put("menu_name",menuNode.getMenuName());
        paraMap.put("menu_lastname",menuNode.getMenuLastname());
        paraMap.put("menu_location",menuNode.getMenuLocation());
        paraMap.put("menu_state",menuNode.getMenuState());
        return testServiceImpl.menuInsert(paraMap);
    }
}
