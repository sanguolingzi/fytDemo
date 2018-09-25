package fyt.business.controller.base;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ResponseData;
import fyt.business.model.base.BusinessModel;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseExecutor<T> extends AbstractExecutor<T> {

    public ResponseData<T> execute(HttpServletRequest request, BusinessModel businessModel, Object...obj){
        ResponseData<T> responseData = new ResponseData();
        try{
            if(!validateParam(request,businessModel,obj)){
                T t = this.exectueBusiness(request,businessModel,obj);
                responseData.setData(t);
            }else{
                throw new Exception("测试报错!");
            }
        }catch(Exception e){
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode("发生错误!");
            errorInfo.setErrorMsg(e.getMessage());
            responseData.setErrorInfo(errorInfo);
        }
        responseData.setStatus(HttpStatus.OK.toString());
        return responseData;
    }

    @Override
    public T exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) {
        return null;
    }
}
