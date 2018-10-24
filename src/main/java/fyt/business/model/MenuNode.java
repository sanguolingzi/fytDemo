package fyt.business.model;

import fyt.business.model.base.PageModel;

public class MenuNode extends PageModel {

    private Integer menu_id ;
    private String menu_name;
    private Integer menu_lastid;
    private String menu_lastname;
    private String menu_location;
    private String menu_state;
    private Integer menu_delect;

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Integer getMenu_lastid() {
        return menu_lastid;
    }

    public void setMenu_lastid(Integer menu_lastid) {
        this.menu_lastid = menu_lastid;
    }

    public String getMenu_lastname() {
        return menu_lastname;
    }

    public void setMenu_lastname(String menu_lastname) {
        this.menu_lastname = menu_lastname;
    }

    public String getMenu_location() {
        return menu_location;
    }

    public void setMenu_location(String menu_location) {
        this.menu_location = menu_location;
    }

    public String getMenu_state() {
        return menu_state;
    }

    public void setMenu_state(String menu_state) {
        this.menu_state = menu_state;
    }

    public Integer getMenu_delect() {
        return menu_delect;
    }

    public void setMenu_delect(Integer menu_delect) {
        this.menu_delect = menu_delect;
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_lastid=" + menu_lastid +
                ", menu_lastname='" + menu_lastname + '\'' +
                ", menu_location='" + menu_location + '\'' +
                ", menu_state='" + menu_state + '\'' +
                ", menu_delect=" + menu_delect +
                '}';
    }
}
