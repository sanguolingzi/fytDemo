package fyt.business.core.handler;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.handler.ExceptionHandler;
import fyt.business.core.exception.BusinessException;
import org.springframework.stereotype.Service;


@Service
public class BusinessExceptionHandler implements ExceptionHandler{

    @Override
    public ErrorInfo handlerErrorInfo(BusinessException e) {
        ErrorInfo errorInfo  = new ErrorInfo();
        errorInfo.setErrorCode(null);
        errorInfo.setErrorMsg(e.getMsg());
        return errorInfo;
    }
}
