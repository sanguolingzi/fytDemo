package fyt.business.pojo;

import fyt.business.model.base.PageModel;

import javax.persistence.*;

@Table(name = "role")
public class RolePojo extends PageModel {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_state")
    private String roleState;

    @Column(name = "role_delete")
    private Integer roleDelete;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return role_state
     */
    public String getRoleState() {
        return roleState;
    }

    /**
     * @param roleState
     */
    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    /**
     * @return role_delete
     */
    public Integer getRoleDelete() {
        return roleDelete;
    }

    /**
     * @param roleDelete
     */
    public void setRoleDelete(Integer roleDelete) {
        this.roleDelete = roleDelete;
    }
}