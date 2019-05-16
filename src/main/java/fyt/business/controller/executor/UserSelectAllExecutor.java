package fyt.business.controller.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.base.BusinessModel;
import fyt.business.model.base.PageData;
import fyt.business.pojo.UserPojo;
import fyt.business.service.RoleService;
import fyt.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserSelectAllExecutor extends BaseExecutor<Object> {

    @Autowired
    private UserService userService;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        UserPojo userPojo = (UserPojo)businessModel;
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("user_id",userPojo.getUserId());
        PageHelper.startPage(userPojo.getCurrentPage(),userPojo.getPageSize());
        List<Map<String,Object>> list = userService.SelectUser(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }
}
