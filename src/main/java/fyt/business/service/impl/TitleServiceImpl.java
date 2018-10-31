package fyt.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fyt.business.model.MenuNode;
import fyt.business.model.base.PageData;
import fyt.business.pojo.TitlePojo;
import fyt.business.service.TitleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import fyt.business.mapper.TitleMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TitleServiceImpl implements TitleService
{
    @Autowired
    private TitleMapper mapper;

    @Override
    public PageData selectTitle(TitlePojo titlePojo) {
        PageHelper.startPage(titlePojo.getCurrentPage(),titlePojo.getPageSize());
        Map<String,Object> paraMap = new HashMap();
        List<Map<String,Object>> list = mapper.selectTitle(paraMap);
        PageInfo pageInfo = new PageInfo(list);
        PageData<Map<String,Object>> pageData = new PageData(pageInfo.getTotal(),pageInfo.getList());
        return pageData;
    }

    @Override
    public List<Map<String, Object>> selectTitleName(int title_id) {
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("title_id",title_id);
        List<Map<String,Object>> list = mapper.selectTitleName(paraMap);
        return list;
    }

    @Override
    public List<Map<String, Object>> selectTitleMenu(int i) {
        Map<String, Object> paraMap = new HashMap();
        paraMap.put("title_id",i);
        List<Map<String,Object>> list = mapper.selectTitleMenu(paraMap);
        for(Map<String, Object> map:list){
            int menu_id = (int)(map.get("menu_id"));
            map.put("SubmenuList",mapper.selectSubmenu(menu_id));
        }
        return list;
    }

    @Override
    public int insertTitle(Map<String, Object> paraMap) {
        try {
            return mapper.insertTitle(paraMap);
        }catch (Exception e){
            e.getMessage();
            return 0;
        }
    }

    @Override
    public int updataTitle(Map<String, Object> paraMap) {
        return mapper.updateTitle(paraMap);
    }

    @Override
    public int deleteTitle(int i) {
        int end = mapper.deleteTitle(i);
        mapper.deleteTitleMenu(i);
        return end;
    }

    @Override
    public int insertTitleMenu(Map<String, Object> paraMap) {
        return mapper.insertTitleMenu(paraMap);
    }

    @Override
    public List<Map<String, Object>> selectTitleMenuAll() {
        Map<String, Object> paraMap = new HashMap();
        List<Map<String,Object>> list = mapper.selectTitleMenuAll(paraMap);
        for(Map<String, Object> map:list){
            int menu_id = (int)(map.get("menu_id"));
            map.put("SubmenuList",mapper.selectSubmenuAll(menu_id));
        }
        return list;

    }

    @Override
    public List<Map<String, Object>> selectMenuid(int i) {
        Map<String, Object> paraMap = new HashMap();
        paraMap.put("title_id",i);
        return mapper.selectTitleMenu(paraMap);
    }
}