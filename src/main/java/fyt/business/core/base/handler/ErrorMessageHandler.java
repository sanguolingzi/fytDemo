package fyt.business.core.base.handler;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ErrorMessage;

public interface ErrorMessageHandler {

    ErrorInfo handlerErrorMessage(ErrorMessage errorMessage);
}
