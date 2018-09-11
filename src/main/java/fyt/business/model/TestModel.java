package fyt.business.model;

import fyt.business.model.base.PageModel;

public class TestModel extends PageModel{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
