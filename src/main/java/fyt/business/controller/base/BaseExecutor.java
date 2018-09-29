package fyt.business.controller.base;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ErrorMessage;
import fyt.business.core.base.ResponseData;
import fyt.business.core.base.ResultInfo;
import fyt.business.core.constant.BusinessStatus;
import fyt.business.core.exception.BusinessException;
import fyt.business.model.base.BusinessModel;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseExecutor<T> extends AbstractExecutor<T> {

    public ResponseData<T> execute(HttpServletRequest request, BusinessModel businessModel, Object...obj){
        ResponseData<T> responseData = new ResponseData();
        responseData.setStatus(HttpStatus.OK.toString());
        try{
            ErrorMessage errorMessage = validateParam(request,businessModel,obj);
            if(errorMessage == null || !errorMessage.hasError()){
                T t = this.exectueBusiness(request,businessModel,obj);
                responseData.setData(t);
            }else{
                 throw new BusinessException(   errorMessage.getErrorInfo().getErrorMsg());
            }
        }catch(Exception e){
            ErrorInfo errorInfo = new ErrorInfo();
            if(e instanceof BusinessException){
                //errorInfo.setErrorCode("error");
                errorInfo.setErrorMsg(((BusinessException)e).getMsg());
            }else{
                errorInfo.setErrorCode("error");
                errorInfo.setErrorMsg("系统异常!");
            }
            responseData.setErrorInfo(errorInfo);
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(BusinessStatus.BUSINESSERROR.getCode());
            resultInfo.setMsg(BusinessStatus.BUSINESSERROR.getMsg());
            responseData.setResultInfo(resultInfo);
            return responseData;
        }

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(BusinessStatus.BUSINESSSUCCESS.getCode());
        resultInfo.setMsg(BusinessStatus.BUSINESSSUCCESS.getMsg());
        responseData.setResultInfo(resultInfo);
        return responseData;
    }

    @Override
    public T exectueBusiness(HttpServletRequest request, BusinessModel businessModel, Object... obj) throws Exception{
        return null;
    }
}
