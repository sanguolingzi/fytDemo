package fyt.business.controller.base;


import fyt.business.core.base.ErrorMessage;
import fyt.business.model.base.BusinessModel;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractExecutor<T> implements TemplateExecutor<T>{


    public ErrorMessage validateParam(HttpServletRequest request, BusinessModel businessModel, Object...obj) throws Exception{

        return null;
    }
}
