package fyt.business.controller.base;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ErrorMessage;
import fyt.business.core.base.ResponseData;
import fyt.business.core.base.ResultInfo;
import fyt.business.core.base.handler.ErrorMessageHandler;
import fyt.business.core.base.handler.ExceptionHandler;
import fyt.business.core.constant.BusinessStatus;
import fyt.business.core.exception.BusinessException;
import fyt.business.model.base.BusinessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseExecutor<T> extends AbstractExecutor<T> {

    @Autowired(required = false)
    private ExceptionHandler exceptionHandler;

    @Autowired(required = false)
    private ErrorMessageHandler errorMessageHandler;


    public ResponseData<T> execute(HttpServletRequest request, BusinessModel businessModel, Object...obj){
        ResponseData<T> responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.toString());
        try{
            ErrorMessage errorMessage = validateParam(request,businessModel,obj);
            if(errorMessage == null || !errorMessage.hasError()){
                T t = this.exectueBusiness(request,businessModel,obj);
                responseData.setData(t);
            }else{
                 if(errorMessageHandler != null){
                     //自定义的业务信息处理
                     ErrorInfo errorInfo = errorMessageHandler.handlerErrorMessage(errorMessage);
                     responseData.setErrorInfo(errorInfo);
                     responseData.setResultInfo(createError());
                     return responseData;
                 }else{
                     throw new BusinessException( errorMessage.getErrorInfo().getErrorMsg());
                 }
            }
        }catch(Exception e){
            ErrorInfo errorInfo = null;
            if(e instanceof BusinessException){
                //errorInfo.setErrorCode("error");
                //自定义的异常信息处理
                if(exceptionHandler != null){
                    errorInfo = exceptionHandler.handlerErrorInfo((BusinessException)e);
                }else{
                    errorInfo = new ErrorInfo();
                    errorInfo.setErrorMsg(((BusinessException)e).getMsg());
                }
            }else{
                errorInfo = new ErrorInfo();
                errorInfo.setErrorCode("error");
                errorInfo.setErrorMsg("系统异常!");
            }
            responseData.setErrorInfo(errorInfo);
            responseData.setResultInfo(createError());
            return responseData;
        }

        responseData.setResultInfo(createSuccess());
        return responseData;
    }

    @Override
    public T exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) throws Exception{
        return null;
    }

    private ResultInfo createSuccess(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(BusinessStatus.BUSINESSSUCCESS.getCode());
        resultInfo.setMsg(BusinessStatus.BUSINESSSUCCESS.getMsg());
        return resultInfo;
    }

    private ResultInfo createError(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(BusinessStatus.BUSINESSERROR.getCode());
        resultInfo.setMsg(BusinessStatus.BUSINESSERROR.getMsg());
        return resultInfo;
    }
}
