package fyt.business.controller.base;

import fyt.business.model.base.BusinessModel;

import javax.servlet.http.HttpServletRequest;

public interface TemplateExecutor<T>{
    T exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object...obj) throws Exception;
}
