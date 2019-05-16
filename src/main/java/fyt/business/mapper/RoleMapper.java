package fyt.business.mapper;

import fyt.business.pojo.RolePojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<RolePojo> {
    List<Map<String,Object>> SelectRole (Map<String,Object> paraMap);

    int DeleteRole(int roleId);

    int UpdateRole(Map<String,Object> paraMap);

    List<Map<String,Object>> selectRoleName(int roleId);

    List<Map<String,Object>> selectTitle();

    List<Map<String,Object>> selectRoleTitle(int roleId);

    int insertRoleTitle(Map<String,Object> paraMap);
}