package fyt.business.core.base;

public class ErrorMessage {

    private ErrorInfo  errorInfo;

    public void addError(String code,String msg){
        errorInfo = new ErrorInfo(code,msg);
    }


    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public boolean hasError(){
        return errorInfo!=null;
    }
}
