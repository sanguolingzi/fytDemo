package fyt.business.core.exception;

public class BusinessException extends Exception {

    private String msg;

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
