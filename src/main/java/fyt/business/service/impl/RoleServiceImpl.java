package fyt.business.service.impl;

import fyt.business.pojo.RolePojo;
import fyt.business.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import fyt.business.mapper.RoleMapper;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleMapper mapper;

    @Override
    public List<Map<String, Object>> SelectRole(Map<String, Object> paraMap) {
        List<Map<String,Object>> list = mapper.SelectRole(paraMap);
        return list;
    }

    @Override
    public int add(RolePojo rolePojo) {
        return mapper.insertSelective(rolePojo);
    }


    @Override
    public int delete(int i) {
        return mapper.DeleteRole(i);
    }

    @Override
    public int updateRole(Map<String, Object> paraMap) {
        return mapper.UpdateRole(paraMap);
    }

    @Override
    public List<Map<String, Object>> selectName(int i) {
        return mapper.selectRoleName(i);
    }

    @Override
    public List<Map<String, Object>> selectTitle() {
        return mapper.selectTitle();
    }

    @Override
    public List<Map<String, Object>> selectRoleTitle(int roleId) {
        return mapper.selectRoleTitle(roleId);
    }

    @Override
    public int insertRoleTitle(Map<String, Object> paraMap) {
        return mapper.insertRoleTitle(paraMap);
    }
}