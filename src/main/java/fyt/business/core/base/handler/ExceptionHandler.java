package fyt.business.core.base.handler;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.exception.BusinessException;

public interface ExceptionHandler {

     ErrorInfo handlerErrorInfo(BusinessException  e);
}
