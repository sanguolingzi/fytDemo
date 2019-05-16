package fyt.business.controller.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.model.base.PageData;
import fyt.business.pojo.RolePojo;
import fyt.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoleSelectAllExecutor extends BaseExecutor<Object> {

    @Autowired
    private RoleService roleServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        RolePojo rolePojo = (RolePojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        PageHelper.startPage(rolePojo.getCurrentPage(),rolePojo.getPageSize());
        List<Map<String,Object>> list = roleServiceImpl.SelectRole(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }
}
