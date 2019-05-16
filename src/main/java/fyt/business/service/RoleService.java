package fyt.business.service;

import fyt.business.model.base.PageData;
import fyt.business.pojo.RolePojo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Map<String,Object>> SelectRole(Map<String,Object> paraMap);

    int add(RolePojo rolePojo);

    int delete(int roleId);

    int updateRole(Map<String,Object> paraMap);

    List<Map<String,Object>> selectName(int roleId);

    List<Map<String,Object>> selectTitle();

    List<Map<String,Object>> selectRoleTitle(int roleId);

    int insertRoleTitle(Map<String,Object> paraMap);
}