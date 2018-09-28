package fyt.business.core.constant;


public enum BusinessStatus {

    BUSINESSSUCCESS("0000","success"),
    BUSINESSERROR("9999","error");


    private String code;
    private String msg;

    BusinessStatus(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return this.code;
    }
    public String getMsg(){
        return this.msg;
    }
}
