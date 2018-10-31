package fyt.business.controller.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.MenuNode;
import fyt.business.model.base.BusinessModel;
import fyt.business.model.base.PageData;
import fyt.business.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuNameExecutor extends BaseExecutor<Object> {

    @Autowired
    private TestService testServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        MenuNode menuNode = (MenuNode)businessModel;
        Map<String,Object> paraMap = new HashMap();
        return testServiceImpl.menuName(paraMap);
    }
}
