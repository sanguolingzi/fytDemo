package fyt.business.core.handler;

import fyt.business.core.base.ErrorInfo;
import fyt.business.core.base.ErrorMessage;
import fyt.business.core.base.handler.ErrorMessageHandler;
import org.springframework.stereotype.Service;

@Service
public class SimpleErrorMessageHandler implements ErrorMessageHandler {

    @Override
    public ErrorInfo handlerErrorMessage(ErrorMessage errorMessage) {
        return errorMessage.getErrorInfo();
    }
}
