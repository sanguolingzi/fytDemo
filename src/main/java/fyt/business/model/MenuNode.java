package fyt.business.model;

import fyt.business.model.base.PageModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "menu")
public class MenuNode extends PageModel {
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_lastid")
    private Integer menuLastid;

    @Column(name = "menu_lastname")
    private String menuLastname;

    @Column(name = "menu_location")
    private String menuLocation;

    @Column(name = "menu_state")
    private String menuState;

    @Column(name = "menu_delete")
    private Integer menuDelete;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuLastid() {
        return menuLastid;
    }

    public void setMenuLastid(Integer menuLastid) {
        this.menuLastid = menuLastid;
    }

    public String getMenuLastname() {
        return menuLastname;
    }

    public void setMenuLastname(String menuLastname) {
        this.menuLastname = menuLastname;
    }

    public String getMenuLocation() {
        return menuLocation;
    }

    public void setMenuLocation(String menuLocation) {
        this.menuLocation = menuLocation;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public Integer getMenuDelete() {
        return menuDelete;
    }

    public void setMenuDelete(Integer menuDelete) {
        this.menuDelete = menuDelete;
    }
}
