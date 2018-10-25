package fyt.business.service;

import fyt.business.model.base.PageData;
import fyt.business.pojo.TitlePojo;

import java.util.List;
import java.util.Map;

public interface TitleService
{
    PageData selectTitle(TitlePojo titlePojo);

    List<Map<String,Object>> selectTitleMenu(int i);

    int insertTitle(Map<String,Object> paraMap);

    int updataTitle(Map<String,Object> paraMap);

    int deleteTitle(int i);

    int insertTitleMenu(Map<String,Object> paraMap);

    List<Map<String,Object>> selectTitleMenuAll();
}