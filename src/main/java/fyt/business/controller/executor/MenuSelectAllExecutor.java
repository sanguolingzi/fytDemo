package fyt.business.controller.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.controller.base.BaseExecutor;
import fyt.business.model.MenuNode;
import fyt.business.model.base.BusinessModel;
import fyt.business.model.base.PageData;
import fyt.business.model.base.PageModel;
import fyt.business.pojo.TitlePojo;
import fyt.business.service.TestService;
import fyt.business.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuSelectAllExecutor extends BaseExecutor<Object> {

    @Autowired
    private TestService testServiceImpl;

    @Override
    public Object exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        MenuNode menuNode = (MenuNode)businessModel;
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("menu_name",menuNode.getMenuName());
        paraMap.put("menu_lastid",menuNode.getMenuLastid());
        paraMap.put("menu_id",menuNode.getMenuId());
        PageHelper.startPage(menuNode.getCurrentPage(),menuNode.getPageSize());
        List<Map<String,Object>> list = testServiceImpl.menuSelectAll(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }
}
