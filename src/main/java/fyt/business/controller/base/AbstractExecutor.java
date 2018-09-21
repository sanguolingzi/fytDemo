package fyt.business.controller.base;


import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ResponseData;
import fyt.business.model.base.BusinessModel;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractExecutor<T> implements TemplateExecutor<T>{


    public ResponseData<T> execute(HttpServletRequest request, BusinessModel businessModel,Object...obj){
        ResponseData<T> responseData = new ResponseData();
        try{
            T t = this.exectueBusiness(request,businessModel,obj);
            responseData.setData(t);
            responseData.setStatus(HttpStatus.OK.toString());

        }catch(Exception e){
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode("发生错误!");
            errorInfo.setErrorMsg(e.getMessage());
            responseData.setErrorInfo(errorInfo);
        }
        return responseData;
    }
}
