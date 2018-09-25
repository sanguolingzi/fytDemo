package fyt.business.controller.base;


import fyt.business.model.base.BusinessModel;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractExecutor<T> implements TemplateExecutor<T>{


    public boolean validateParam(HttpServletRequest request, BusinessModel businessModel,Object...obj){

        return false;
    }
}
